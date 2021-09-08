package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.DamageCountUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
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

}
