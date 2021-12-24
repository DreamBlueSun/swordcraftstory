package com.marisa.swordcraftstory.event.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 *
 */

public class LivingHurtUtils {
    
    public static float getDamageAfterArmorAbsorb(LivingEntity livingEntity, DamageSource source, float damage) {
      if (!source.isBypassArmor()) {
         damage = CombatRules.getDamageAfterAbsorb(damage, (float)livingEntity.getArmorValue(), (float)livingEntity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
      }
      return damage;
   }

   public static float getDamageAfterMagicAbsorb(LivingEntity livingEntity, DamageSource source, float damage) {
      if (source.isBypassMagic()) {
         return damage;
      } else {
         if (livingEntity.hasEffect(MobEffects.DAMAGE_RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
            int i = (livingEntity.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 5;
            int j = 25 - i;
            float f = damage * (float)j;
            float f1 = damage;
            damage = Math.max(f / 25.0F, 0.0F);
            float f2 = f1 - damage;
            if (f2 > 0.0F && f2 < 3.4028235E37F) {
               if (livingEntity instanceof ServerPlayer) {
                  ((ServerPlayer)livingEntity).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
               } else if (source.getEntity() instanceof ServerPlayer) {
                  ((ServerPlayer)source.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
               }
            }
         }
         if (damage <= 0.0F) {
            return 0.0F;
         } else {
            int k = EnchantmentHelper.getDamageProtection(livingEntity.getArmorSlots(), source);
            if (k > 0) {
               damage = CombatRules.getDamageAfterMagicAbsorb(damage, (float)k);
            }
            return damage;
         }
      }
   }
    
}
