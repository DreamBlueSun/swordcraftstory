package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.block.ore.OreGenerate;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
    public void healEvent(BlockEvent.BreakEvent event) {
        //当武器有损坏时使用武器，消耗dur回复耐久度
        PlayerEntity player = event.getPlayer();
        if (player != null) {
            ItemStack stack = player.getHeldItem(player.getActiveHand());
            if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
                CombatPropertiesUtils.useDur(stack);
            }
        }
    }

    @SubscribeEvent
    public void wakeUpFromBedEvent(PlayerWakeUpEvent event) {
        //睡觉醒来时回满血
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) entity;
            e.heal((float) e.getAttributeValue(Attributes.MAX_HEALTH));
        }
    }

    @SubscribeEvent
    public void damageEvent(LivingDamageEvent event) {
        //修改伤害计算
        float damage = DamageCountUtils.damageResult(event.getSource(), event.getEntity(), event.getAmount());
        event.setAmount(damage);
        if (damage == 0.0F) {
            event.setCanceled(true);
        }
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
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {
        //玩家重生时，加载物语的玩家相关数据
        PlayerEntity player = event.getPlayer();
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        PlayerAttributesUtils.onClone(player, storyPlayerData, event.isWasDeath());
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        //mob实体加入世界时，根据最近玩家(64范围)等级增加属性
        World world = event.getWorld();
        Entity entity = event.getEntity();
        if (!world.isRemote && entity instanceof MobEntity) {
            MobAttributesUtils.onJoinWorld(world.getClosestPlayer(entity, 64), (MobEntity) entity);
        }
    }

    @SubscribeEvent
    public void dropExperience(LivingExperienceDropEvent event) {
        //掉落经验值时，如果是mob，则根据等级增加经验值
        Entity entity = event.getEntity();
        if (entity instanceof MobEntity) {
            int lv = MobAttributesUtils.getLvByName(entity.getDisplayName().getString());
            if (lv > 0) {
                event.setDroppedExperience(event.getDroppedExperience() + lv);
            }
        }
    }

}
