package com.marisa.swordcraftstory.event.util;

import com.marisa.swordcraftstory.event.pojo.Absorb;
import com.marisa.swordcraftstory.event.pojo.Damage;
import com.marisa.swordcraftstory.save.MobAttrSaveData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * 伤害结算
 */

public class LivingHurtUtils {

    public static void hurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        LivingEntity target = event.getEntityLiving();
        //伤害计算
        Damage damage = new Damage(event.getAmount(), source, target);
        float amount = damage.totalAll();
        if (!source.isBypassMagic() && source != DamageSource.OUT_OF_WORLD) {
            Absorb absorb = new Absorb(target, source);
            damage.addP(-absorb.getP()).addM(-absorb.getM()).addR(-absorb.getR());
            float v = damage.totalAll();
            amount = v * Math.max(1.0F - absorb.getEnchantAbsorb() - absorb.getBuffAbsorb(), 0);
            //抗性生效时效果
            float f2 = v * absorb.getBuffAbsorb();
            if (f2 > 0.0F && f2 < 3.4028235E37F) {
                if (target instanceof ServerPlayer) {
                    ((ServerPlayer) target).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_RESISTED), Math.round(f2 * 10.0F));
                } else if (source.getEntity() instanceof ServerPlayer) {
                    ((ServerPlayer) source.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_RESISTED), Math.round(f2 * 10.0F));
                }
            }
        }
        //扣减护盾
        float f2 = Math.max(amount - target.getAbsorptionAmount(), 0.0F);
        target.setAbsorptionAmount(target.getAbsorptionAmount() - (amount - f2));
        float f = amount - f2;
        if (target instanceof Player player) {
            if (f > 0.0F && f < 3.4028235E37F) {
                player.awardStat(Stats.DAMAGE_ABSORBED, Math.round(f * 10.0F));
            }
            //伤害结算
            if (f2 != 0.0F) {
                player.causeFoodExhaustion(source.getFoodExhaustion());
                float f1 = player.getHealth();
                player.getCombatTracker().recordDamage(source, f1, f2);
                player.setHealth(f1 - f2);
                if (f2 < 3.4028235E37F) {
                    player.awardStat(Stats.DAMAGE_TAKEN, Math.round(f2 * 10.0F));
                }
            }
        } else {
            if (f > 0.0F && f < 3.4028235E37F && source.getEntity() instanceof ServerPlayer) {
                ((ServerPlayer) source.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_ABSORBED), Math.round(f * 10.0F));
            }
            //伤害结算
            if (f2 != 0.0F) {
                float f1 = target.getHealth();
                target.getCombatTracker().recordDamage(source, f1, f2);
                target.setHealth(f1 - f2);
                target.setAbsorptionAmount(target.getAbsorptionAmount() - f2);
                target.gameEvent(GameEvent.ENTITY_DAMAGED, source.getEntity());
                //保存Mob属性
                if (!target.level.isClientSide() && target instanceof Mob) {
                    MobAttrSaveData saveData = MobAttrSaveData.getInstance((ServerLevel) target.level);
                    saveData.mark(target.getStringUUID(), f1 - f2);
                }
            }
        }
    }

}
