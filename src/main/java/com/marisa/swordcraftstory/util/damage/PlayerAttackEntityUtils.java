package com.marisa.swordcraftstory.util.damage;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.item.weapon.ranged.AbstractRangedWeapon;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.Collection;

/**
 * 玩家攻击实体工具类
 */

public class PlayerAttackEntityUtils {

    /**
     * 攻击目标实体
     */
    public static void attackTargetEntity(PlayerEntity player, Entity targetEntity) {
        if (targetEntity.canBeAttackedWithItem()) {
            if (!targetEntity.hitByEntity(player)) {
                //基础攻击伤害
                float f = damageByStory(player);
                //计算额外附魔攻击伤害
                int lvl;
                float f1 = 0.0F, fOffset = 1.0F;
                if ((lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, player.getHeldItemMainhand())) > 0) {
                    //锋利
                    if (lvl > 4) {
                        fOffset = 1.1F;
                    }
                } else if ((lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, player.getHeldItemMainhand())) > 0) {
                    //亡灵
                    if (((LivingEntity) targetEntity).getCreatureAttribute() != CreatureAttribute.UNDEAD) {
                        lvl = 0;
                    }
                    if (lvl > 4) {
                        fOffset = 1.2F;
                    }
                } else if ((lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, player.getHeldItemMainhand())) > 0) {
                    //节肢
                    if (((LivingEntity) targetEntity).getCreatureAttribute() != CreatureAttribute.ARTHROPOD) {
                        lvl = 0;
                    }
                    if (lvl > 4) {
                        fOffset = 1.2F;
                    }
                } else if ((lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.IMPALING, player.getHeldItemMainhand())) > 0) {
                    //穿刺
                    if (((LivingEntity) targetEntity).getCreatureAttribute() != CreatureAttribute.WATER) {
                        lvl = 0;
                    }
                    if (lvl > 4) {
                        fOffset = 1.2F;
                    }
                }
                for (int i = 0; i < lvl + 1; i++) {
                    f1 += i;

                }
                //攻击速度伤害偏移
                float f2 = player.getCooledAttackStrength(0.5F);
                f = f * (0.2F + f2 * f2 * 0.8F);
                f1 = f1 * f2;
                player.resetCooldown();
                //伤害逻辑
                if (f > 0.0F || f1 > 0.0F) {
                    //flag：攻速恢复达到90%
                    boolean flag = f2 > 0.9F;
                    //flag1：是否击退，玩家默认击退值0
                    boolean flag1 = false;
                    float i = 0.0F;
                    i = i + EnchantmentHelper.getKnockbackModifier(player);
                    if (player.isSprinting() && flag) {
                        player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++i;
                        flag1 = true;
                    }
                    //flag2：是否暴击
                    boolean flag2 = LivingHurtUtils.isCri(player);
                    if (flag2) {
                        f *= 2.0F;
                    }
                    //f：伤害总量
                    f = (f + f1) * fOffset;
                    //flag3：是否横扫攻击
                    boolean flag3 = false;
                    double d0 = player.distanceWalkedModified - player.prevDistanceWalkedModified;
                    if (flag && !flag1 && player.isOnGround() && d0 < (double) player.getAIMoveSpeed()) {
                        ItemStack itemstack = player.getHeldItem(Hand.MAIN_HAND);
                        if (itemstack.getItem() instanceof SwordItem) {
                            flag3 = true;
                        }
                    }
                    //flag4：是否火焰附加
                    float f4 = 0.0F;
                    boolean flag4 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(player);
                    if (targetEntity instanceof LivingEntity) {
                        f4 = ((LivingEntity) targetEntity).getHealth();
                        if (j > 0 && !targetEntity.isBurning()) {
                            flag4 = true;
                            targetEntity.setFire(1);
                        }
                    }
                    //执行伤害
                    Vector3d vector3d = targetEntity.getMotion();
                    boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
                    //执行特效
                    if (flag5) {
                        //击退伤害特效
                        if (i > 0) {
                            if (targetEntity instanceof LivingEntity) {
                                ((LivingEntity) targetEntity).applyKnockback(i * 0.5F, MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F)), -MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F)));
                            } else {
                                targetEntity.addVelocity(-MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F)) * (float) i * 0.5F, 0.1D, MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F)) * i * 0.5F);
                            }

                            player.setMotion(player.getMotion().mul(0.6D, 1.0D, 0.6D));
                            player.setSprinting(false);
                        }
                        //横扫伤害特效
                        if (flag3) {
                            float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

                            for (LivingEntity livingentity : player.world.getEntitiesWithinAABB(LivingEntity.class, targetEntity.getBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                                if (livingentity != player && livingentity != targetEntity && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && player.getDistanceSq(livingentity) < 9.0D) {
                                    livingentity.applyKnockback(0.4F, MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F)), -MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F)));
                                    livingentity.attackEntityFrom(DamageSource.causePlayerDamage(player), f3);
                                }
                            }

                            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            player.spawnSweepParticles();
                        }

                        if (targetEntity instanceof ServerPlayerEntity && targetEntity.velocityChanged) {
                            ((ServerPlayerEntity) targetEntity).connection.sendPacket(new SEntityVelocityPacket(targetEntity));
                            targetEntity.velocityChanged = false;
                            targetEntity.setMotion(vector3d);
                        }
                        //暴击伤害特效
                        if (flag2) {
                            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                            player.onCriticalHit(targetEntity);
                        }
                        //攻速阈值伤害特效
                        if (!flag2 && !flag3) {
                            if (flag) {
                                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            } else {
                                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }
                        //附魔伤害特效
                        if (f1 > 0.0F) {
                            player.onEnchantmentCritical(targetEntity);
                        }
                        //???
                        player.setLastAttackedEntity(targetEntity);
                        if (targetEntity instanceof LivingEntity) {
                            EnchantmentHelper.applyThornEnchantments((LivingEntity) targetEntity, player);
                        }
                        //???
                        EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
                        ItemStack itemstack1 = player.getHeldItemMainhand();
                        Entity entity = targetEntity;
                        if (targetEntity instanceof net.minecraftforge.entity.PartEntity) {
                            entity = ((net.minecraftforge.entity.PartEntity<?>) targetEntity).getParent();
                        }
                        //武器损伤计算
                        if (!player.world.isRemote && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
                            ItemStack copy = itemstack1.copy();
                            itemstack1.hitEntity((LivingEntity) entity, player);
                            if (itemstack1.isEmpty()) {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.MAIN_HAND);
                                player.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }
                        //扣血心数量特效
                        if (targetEntity instanceof LivingEntity) {
                            float f5 = f4 - ((LivingEntity) targetEntity).getHealth();
                            player.addStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                            if (j > 0) {
                                //火焰附加
                                targetEntity.setFire(j * 4);
                            }
                            if (player.world instanceof ServerWorld && f5 > 2.0F) {
                                ((ServerWorld) player.world).spawnParticle(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getPosX(), targetEntity.getPosYHeight(0.5D), targetEntity.getPosZ(), 1, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }
                        //???
                        player.addExhaustion(0.1F);
                    } else {
                        player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
                        //火焰附加结束
                        if (flag4) {
                            targetEntity.extinguish();
                        }
                    }
                }
            }
        }
    }

    /**
     * 模组玩家伤害计算方法
     */
    private static float damageByStory(PlayerEntity player) {
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (stack.isEmpty()) {
            return 1.0F;
        }
        if (stack.getItem() instanceof Weapon) {
            //story武器
            Weapon weapon = (Weapon) stack.getItem();
            if (stack.getItem() instanceof AbstractRangedWeapon) {
                //远程武器平A伤害为攻击力的一半
                return (float) (weapon.getAtk(stack) / 2);
            } else {
                weapon.incrTec(stack);
                return weapon.getAtk(stack);
            }
        } else {
            //非story武器
            Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
            Collection<AttributeModifier> modifiers = attributeModifiers.get(Attributes.ATTACK_DAMAGE);
            if (modifiers != null && modifiers.size() > 0) {
                return (float) ((AttributeModifier) modifiers.toArray()[0]).getAmount() + 1.0F;
            } else {
                return 1.0F;
            }
        }
    }


}
