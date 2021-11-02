package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.block.ore.OreGenerate;
import com.marisa.swordcraftstory.item.reply.ReplyItem;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.StoryPlayerPack;
import com.marisa.swordcraftstory.save.*;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkillHelper;
import com.marisa.swordcraftstory.util.MobAttributesUtils;
import com.marisa.swordcraftstory.util.PlayerAttributesUtils;
import com.marisa.swordcraftstory.util.damage.LivingHurtUtils;
import com.marisa.swordcraftstory.util.damage.PlayerAttackEntityUtils;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import com.marisa.swordcraftstory.util.obj.MobAttr;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * 监听事件处理器
 */

public class EventHandler {

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent event) {
        //生物群系加载时加入矿石生成
        OreGenerate.join(event);
    }

    @SubscribeEvent
    public void wakeUpFromBedEvent(PlayerWakeUpEvent event) {
        //睡觉醒来时回满血
        Entity entity = event.getEntity();
        if (!entity.world.isRemote() && entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) entity;
            e.heal((float) e.getAttributeValue(Attributes.MAX_HEALTH));
        }
    }

    @SubscribeEvent
    public void playerAttackEntityEvent(AttackEntityEvent event) {
        //修改玩家近战攻击效果
        if (!event.getPlayer().world.isRemote()) {
            PlayerAttackEntityUtils.attackTargetEntity(event.getPlayer(), event.getTarget());
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void livingHurtEvent(LivingHurtEvent event) {
        //修改伤害结算
        if (!event.getEntity().world.isRemote()) {
            LivingHurtUtils.damageEntity((LivingEntity) event.getEntity(), event.getSource(), event.getAmount());
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void playerPickupXpEvent(PlayerXpEvent.PickupXp event) {
        //玩家获取经验时，在物语等级系统也增加
        PlayerEntity player = event.getPlayer();
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        storyPlayerData.addXp(event.getOrb().getXpValue());
        storyPlayerData.checkLvUp(player);
    }

    @SubscribeEvent
    public void SaveToPlayerFile(PlayerEvent.SaveToFile event) {
        //玩家保存数据时，物语的玩家相关数据也保存
        StoryPlayerData.serialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
    }

    @SubscribeEvent
    public void LoadFromPlayerFile(PlayerEvent.LoadFromFile event) {
        //玩家加载数据时，物语的玩家相关数据也加载
        PlayerEntity player = event.getPlayer();
        StoryPlayerData storyPlayerData = StoryPlayerData.deserialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
        PlayerAttributesUtils.onClone(player, storyPlayerData, false);
        PlayerAttributesUtils.onConfigAttr(player, true, false);
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {
        //玩家重生时，加载物语的玩家相关数据
        PlayerEntity player = event.getPlayer();
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        PlayerAttributesUtils.onClone(player, storyPlayerData, event.isWasDeath());
        PlayerAttributesUtils.onConfigAttr(player, true, event.isWasDeath());
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        World world = event.getWorld();
        Entity entity = event.getEntity();
        if (!world.isRemote() && entity instanceof MobEntity) {
            //mob实体加入世界时，读取保存的属性
            MobAttrSaveData saveData = MobAttrSaveData.getInstance((ServerWorld) world);
            MobAttr mobAttr = saveData.get(entity.getUniqueID().toString());
            if (mobAttr != null) {
                MobAttributesUtils.loadAttr((MobEntity) entity, mobAttr);
            } else {
                //未读取到属性，则根据最近玩家(128范围)等级新增属性
                mobAttr = MobAttributesUtils.newAttr((MobEntity) entity, world.getClosestPlayer(entity, 128));
                //保存Mob属性
                saveData.mark(mobAttr);
            }
        } else if (!world.isRemote() && entity instanceof ServerPlayerEntity) {
            //同步玩家物语数据到客户端
            StoryPlayerPack pack = new StoryPlayerPack("story.player.status.async", StoryPlayerDataManager.get(entity.getCachedUniqueIdString()));
            Networking.STORY_PLAYER_INFO.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity), pack);
        }
    }

    @SubscribeEvent
    public void entityLeaveWorld(EntityLeaveWorldEvent event) {
        if (!event.getWorld().isRemote() && event.getEntity() instanceof MobEntity) {
            //mob实体死亡时，移除保存的属性
            if (((MobEntity) event.getEntity()).getShouldBeDead()) {
                MobAttrSaveData saveData = MobAttrSaveData.getInstance((ServerWorld) event.getWorld());
                saveData.rem(event.getEntity().getUniqueID().toString());
            }
        }
    }

    @SubscribeEvent
    public void dropExperience(LivingExperienceDropEvent event) {
        //掉落经验值时，如果是mob，则根据等级增加经验值
        Entity entity = event.getEntity();
        if (!entity.world.isRemote() && entity instanceof MobEntity) {
            int mobLv = MobAttributesUtils.getMobLv((ServerWorld) entity.world, entity.getUniqueID().toString());
            if (mobLv > 0) {
                event.setDroppedExperience(event.getDroppedExperience() + mobLv);
            }
        }
    }

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        //每天刷新手摇抽奖机奖品
        if (!event.world.isRemote() && event.phase.ordinal() == 0 && event.world.getDimensionKey().compareTo(World.OVERWORLD) == 0) {
            if (DayTimeManager.isNextDayAndToNext(event.world.getWorldInfo().getDayTime())) {
                //天数变更时更新数据
                DropQualityManualLotteryMachine.shuffle();
                //标记保存手摇抽奖机数据
                ManualLotteryMachineSaveData.get((ServerWorld) event.world).mark();
            } else if (DayTimeManager.getWorldDay() == -1) {
                ManualLotteryMachineSaveData saveData = ManualLotteryMachineSaveData.get((ServerWorld) event.world);
                if (saveData.getWorldDay() == -1) {
                    //没有保存的数据时更新数据
                    DropQualityManualLotteryMachine.shuffle();
                    //标记保存手摇抽奖机数据
                    ManualLotteryMachineSaveData.get((ServerWorld) event.world).mark();
                } else {
                    //加载保存的手摇抽奖机数据
                    DayTimeManager.load(saveData);
                    DropQualityManualLotteryMachine.load(saveData);
                }
            }
        }
    }

    @SubscribeEvent
    public void useItemEventStart(LivingEntityUseItemEvent.Start event) {
        //动态设置回复道具使用时长
        Item item = event.getItem().getItem();
        if (item instanceof ReplyItem && event.getEntityLiving() instanceof PlayerEntity) {
            int up = WeaponSkillHelper.fixedUp((PlayerEntity) event.getEntityLiving(), WeaponSkillHelper.LIST_HEAL_USE_FAST_ID);
            event.setDuration(item.getUseDuration(event.getItem()) * 100 / (100 + up));
        }
    }

}
