package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemMakeMenu;
import com.marisa.swordcraftstory.event.pojo.MobAttr;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.PlayerDataPack;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

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
        Player player = event.getPlayer();
        final int value = event.getOrb().getValue();
        //判断经验修补
        ItemStack stack = player.getMainHandItem();
        if (StoryUtils.isWeapon(stack.getItem())) {
            int have = value / 5;
            if (have > 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MENDING, stack) > 0) {
                int need = SmithHelper.getDurMax(stack) - SmithHelper.getDur(stack);
                if (need > 0) {
                    SmithHelper.plusDur(stack, have);
                    event.getOrb().value = Math.max(value - (have - Math.max(have - need, 0)) * 5, 0);
                    if (event.getOrb().value == 0) {
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }
        //玩家获取经验时，在物语等级系统也增加
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        playerData.addXp(event.getOrb().value);
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
                int playerLv = 0;
                //未读取到属性，先根据最近(128的xz半径范围、32的y半径范围)的所有玩家等级新增属性
                Vec3 vec3 = entity.position();
                List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(vec3.x() - 256.0D, vec3.y() - 128.0D, vec3.z() - 256.0D, vec3.x() + 256.0D, vec3.y() + 128.0D, vec3.z() + 256.0D), (p_9062_) -> true);
                int size = players.size();
                if (size > 0) {
                    int countLv = 0, minLv = Story.LV_MAX;
                    for (Player player : players) {
                        int lv = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
                        countLv += lv;
                        if (lv < minLv) {
                            minLv = lv;
                        }
                    }
                    if (minLv <= ItemMakeMenu.RANK_LV_NEED_ONCE) {
                        playerLv = minLv;
                    } else {
                        playerLv = countLv / size;
                    }
                }
                mobAttr = MobAttributesUtils.newAttr((Mob) entity, playerLv);
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
