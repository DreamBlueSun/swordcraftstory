package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.event.pojo.MobAttr;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.PlayerDataPack;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

/**
 * 监听事件处理器
 */

public class SaveEventHandler {

    @SubscribeEvent
    public void dropExperience(LivingExperienceDropEvent event) {
        //掉落经验值时，如果是mob，则根据等级增加经验值
        Entity entity = event.getEntity();
        if (!entity.level.isClientSide() && entity instanceof Mob) {
            int mobLv = MobAttributesUtils.getMobLv((ServerLevel) entity.level, entity.getStringUUID());
            if (mobLv > 0) {
                event.setDroppedExperience(event.getDroppedExperience() + mobLv);
            }
        }
    }

    @SubscribeEvent
    public void playerPickupXpEvent(PlayerXpEvent.PickupXp event) {
        //玩家获取经验时，在物语等级系统也增加
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        playerData.addXp(event.getOrb().getValue());
        PlayerAttributesUtils.checkLvUp(player, playerData);
    }

    @SubscribeEvent
    public void SaveToPlayerFile(PlayerEvent.SaveToFile event) {
        //玩家保存数据时，物语的玩家相关数据也保存
        PlayerData.serialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
    }

    @SubscribeEvent
    public void LoadFromPlayerFile(PlayerEvent.LoadFromFile event) {
        //玩家加载数据时，物语的玩家相关数据也加载
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.deserialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
        PlayerAttributesUtils.onClone(player, playerData, false);
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {
        //玩家重生时，加载物语的玩家相关数据
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        PlayerAttributesUtils.onClone(player, playerData, event.isWasDeath());
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        Level level = event.getWorld();
        Entity entity = event.getEntity();
        if (!level.isClientSide() && entity instanceof Mob) {
            //mob实体加入世界时，读取保存的属性
            MobAttrSaveData saveData = MobAttrSaveData.getInstance((ServerLevel) level);
            MobAttr mobAttr = saveData.get(entity.getStringUUID());
            if (mobAttr != null) {
                MobAttributesUtils.loadAttr((Mob) entity, mobAttr);
            } else {
                //未读取到属性，则根据最近玩家(128范围)等级新增属性
                mobAttr = MobAttributesUtils.newAttr((Mob) entity, level.getNearestPlayer(entity, 128));
                //保存Mob属性
                saveData.mark(mobAttr);
            }
        } else if (!level.isClientSide() && entity instanceof ServerPlayer) {
            //同步玩家物语数据到客户端
            PlayerDataPack pack = new PlayerDataPack("story.player.status.async", PlayerDataManager.get(entity.getStringUUID()));
            Networking.PLAYER_DATA.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) entity), pack);
        }
    }

    @SubscribeEvent
    public void entityLeaveWorld(EntityLeaveWorldEvent event) {
        if (!event.getWorld().isClientSide() && event.getEntity() instanceof Mob) {
            //mob实体死亡时，移除保存的属性
            if (((Mob) event.getEntity()).isDeadOrDying()) {
                MobAttrSaveData saveData = MobAttrSaveData.getInstance((ServerLevel) event.getWorld());
                saveData.rem(event.getEntity().getStringUUID());
            }
        }
    }

}
