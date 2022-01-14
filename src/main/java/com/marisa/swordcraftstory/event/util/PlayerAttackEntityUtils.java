package com.marisa.swordcraftstory.event.util;

import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;

/**
 *
 */

public class PlayerAttackEntityUtils {

    /**
     * 玩家原攻击方法
     * <p>
     * 重写其中的基础伤害
     * 移除其中的附魔伤害和特效
     */
    public static void attack(Player player, Entity target) {
        if (!target.isAttackable()) {
            return;
        }
        if (target.skipAttackInteraction(player)) {
            return;
        }
        //基础伤害
        float f = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        //锻造伤害
        ItemStack stack = player.getMainHandItem();
        if (SmithNbtUtils.isRangedWeapon(stack.getItem())) {
            f = SmithHelper.getDamageAtk(stack) * 0.5F;
        } else {
            f += SmithHelper.getSmithAtk(stack);
        }
        //附魔伤害
        float f1;
        if (target instanceof LivingEntity) {
            f1 = EnchantmentHelper.getDamageBonus(stack, ((LivingEntity) target).getMobType());
        } else {
            f1 = EnchantmentHelper.getDamageBonus(stack, MobType.UNDEFINED);
        }
        //攻击速度伤害偏移
        float f2 = player.getAttackStrengthScale(0.5F);
        f *= 0.2F + f2 * f2 * 0.8F;
        f1 *= f2;
        player.resetAttackStrengthTicker();
        if (f <= 0.0F && f1 <= 0.0F) {
            return;
        }
        //判断攻速恢复达到90%
        boolean flag = f2 > 0.9F;
        //击退，玩家默认击退值0
        boolean flag1 = false;
        float i = (float) player.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize player value to the attack knockback attribute of the player, which is by default 0
        i += EnchantmentHelper.getKnockbackBonus(player);
        if (player.isSprinting() && flag) {
            player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, player.getSoundSource(), 1.0F, 1.0F);
            ++i;
            flag1 = true;
        }
        //伤害总量
        f += f1;
        //额外计算近战伤害附魔：额外再+(4%*lv)，最高20%
        if (f1 > 0) {
            f *= (1.0F + (Math.min((int) f1, 5) * 0.04F));
        }
        //暴击
        boolean flag2 = SmithNbtUtils.isCri(stack);
        if (flag2) {
            f *= 1.25F;
        }
        //横扫攻击
        boolean flag3 = false;
        double d0 = (double) (player.walkDist - player.walkDistO);
        if (flag && !flag2 && !flag1 && player.isOnGround() && d0 < (double) player.getSpeed()) {
            ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
            flag3 = itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SWORD_SWEEP);
        }
        //火焰附加
        float f4 = 0.0F;
        boolean flag4 = false;
        int j = EnchantmentHelper.getFireAspect(player);
        if (target instanceof LivingEntity) {
            f4 = ((LivingEntity) target).getHealth();
            if (j > 0 && !target.isOnFire()) {
                flag4 = true;
                target.setSecondsOnFire(1);
            }
        }
        //执行伤害
        Vec3 vec3 = target.getDeltaMovement();
        boolean flag5 = target.hurt(DamageSource.playerAttack(player), f);
        //执行特效
        if (flag5) {
            //击退伤害特效
            if (i > 0) {
                if (target instanceof LivingEntity) {
                    ((LivingEntity) target).knockback((double) ((float) i * 0.5F), (double) Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(player.getYRot() * ((float) Math.PI / 180F))));
                } else {
                    target.push((double) (-Mth.sin(player.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F), 0.1D, (double) (Mth.cos(player.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F));
                }

                player.setDeltaMovement(player.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
                player.setSprinting(false);
            }
            //横扫伤害特效
            if (flag3) {
                float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

                for (LivingEntity livingentity : player.level.getEntitiesOfClass(LivingEntity.class, player.getItemInHand(InteractionHand.MAIN_HAND).getSweepHitBox(player, target))) {
                    if (livingentity != player && livingentity != target && !player.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand) livingentity).isMarker()) && player.distanceToSqr(livingentity) < 9.0D) {
                        livingentity.knockback((double) 0.4F, (double) Mth.sin(player.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(player.getYRot() * ((float) Math.PI / 180F))));
                        livingentity.hurt(DamageSource.playerAttack(player), f3);
                    }
                }

                player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
                player.sweepAttack();
            }

            if (target instanceof ServerPlayer && target.hurtMarked) {
                ((ServerPlayer) target).connection.send(new ClientboundSetEntityMotionPacket(target));
                target.hurtMarked = false;
                target.setDeltaMovement(vec3);
            }
            //暴击伤害特效
            if (flag2) {
                player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                player.crit(target);
            }
            //攻速阈值伤害特效
            if (!flag2 && !flag3) {
                if (flag) {
                    player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, player.getSoundSource(), 1.0F, 1.0F);
                } else {
                    player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, player.getSoundSource(), 1.0F, 1.0F);
                }
            }
            //附魔伤害特效
            if (f1 > 0.0F) {
                player.magicCrit(target);
            }
            //???
            player.setLastHurtMob(target);
            if (target instanceof LivingEntity) {
                EnchantmentHelper.doPostHurtEffects((LivingEntity) target, player);
            }
            //???
            EnchantmentHelper.doPostDamageEffects(player, target);
            Entity entity = target;
            if (target instanceof net.minecraftforge.entity.PartEntity) {
                entity = ((net.minecraftforge.entity.PartEntity<?>) target).getParent();
            }
            //武器损伤计算
            if (!player.level.isClientSide && !stack.isEmpty() && entity instanceof LivingEntity) {
                ItemStack copy = stack.copy();
                stack.hurtEnemy((LivingEntity) entity, player);
                if (stack.isEmpty()) {
                    net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copy, InteractionHand.MAIN_HAND);
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            //扣血心数量特效
            if (target instanceof LivingEntity) {
                float f5 = f4 - ((LivingEntity) target).getHealth();
                player.awardStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                if (j > 0) {
                    //火焰附加
                    target.setSecondsOnFire(j * 4);
                }

                if (player.level instanceof ServerLevel && f5 > 2.0F) {
                    int k = (int) ((double) f5 * 0.5D);
                    ((ServerLevel) player.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY(0.5D), target.getZ(), Math.min(k / 20, 3), 0.1D, 0.0D, 0.1D, 0.2D);
                }
            }
            //???
            player.causeFoodExhaustion(0.1F);
        } else {
            player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, player.getSoundSource(), 1.0F, 1.0F);
            //火焰附加结束
            if (flag4) {
                target.clearFire();
            }
        }
    }

}
