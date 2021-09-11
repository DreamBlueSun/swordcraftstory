package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.BlockDropItemUtils;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.DamageCountUtils;
import com.marisa.swordcraftstory.util.PlayerAttributesUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @description: 非静态事件处理
 * @date: 2021/9/3 0003 20:48
 */

public class EventHandler {

    @SubscribeEvent
    public void healEvent(BlockEvent.BreakEvent event) {
        //当武器有损坏时使用武器，消耗dur回复耐久度
        PlayerEntity player = event.getPlayer();
        if (player != null) {
            ItemStack stack = player.getHeldItem(player.getActiveHand());
            if (!stack.isEmpty() && stack.getItem() instanceof Combat) {
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
    public void blockBreakEvent(BlockEvent.BreakEvent event) {
        //矿石方块、非创造模式：监听方块破坏掉落矿石
        BlockDropItemUtils.dropOre(event.getPlayer(), event.getState().getBlock(), event.getPos());
    }

    @SubscribeEvent
    public void blockBreakEvent(PlayerXpEvent.PickupXp event) {
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

}
