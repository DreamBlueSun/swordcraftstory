package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.util.DamageCountUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @description:
 * @date: 2021/9/3 0003 20:48
 */

public class EventHandler {

    @SubscribeEvent
    public void healEvent(LivingHealEvent event) {
        //TODO 禁止消耗饱食度回血
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
        event.setAmount(DamageCountUtils.damageResult(event.getSource(), event.getEntity(), event.getAmount()));
    }

    @SubscribeEvent
    public void playerAttackEvent(AttackEntityEvent event) {
        ItemStack stack = event.getPlayer().getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (stack.getItem() instanceof Combat) {
            ((Combat) stack.getItem()).incrTec(stack);
        }
    }

}
