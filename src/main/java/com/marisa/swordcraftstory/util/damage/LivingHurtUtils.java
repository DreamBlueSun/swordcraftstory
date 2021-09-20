package com.marisa.swordcraftstory.util.damage;

import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.item.weapon.ranged.AbstractRangedWeapon;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.MobAttributesUtils;
import com.marisa.swordcraftstory.util.obj.Damage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;
import java.util.Random;

/**
 * 存活实体伤害工具类
 */

public class LivingHurtUtils {

    /**
     * 更改伤害结算
     */
    public static void damageEntity(LivingEntity livingEntity, DamageSource damageSrc, final float damageAmount) {
        //伤害属性分配
        Damage damage = toDamage(livingEntity, damageSrc, damageAmount);
        //计算盔甲
        if (damage.getP() > 0) {
            damage.setP(applyArmorCalculations(livingEntity, damageSrc, damage.getP()));
        }
        //计算盔甲韧性
        if (damage.getM() > 0) {
            damage.setM(applyToughnessCalculations(livingEntity, damageSrc, damage.getM()));
        }
        //伤害总量
        float total = damage.total();
        //计算药水和附魔的减伤
        total = applyPotionDamageCalculations(livingEntity, damageSrc, total);
        //举盾时消耗受到伤害值5%的耐久(最大20)来抵挡90%的伤害
        if (livingEntity.isHandActive()) {
            Hand handIn = livingEntity.getActiveHand();
            ItemStack stack = livingEntity.getHeldItem(handIn);
            if (!stack.isEmpty() && stack.getItem() instanceof ShieldItem) {
                stack.damageItem(MathHelper.clamp((int) total / 20, 1, 20), livingEntity, (entity) -> entity.sendBreakAnimation(handIn));
                total = total / 10;
            }
        }
        //扣除伤害吸收护盾
        float v = Math.max(total - livingEntity.getAbsorptionAmount(), 0.0F);
        livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() - (total - v));
        //更新玩家伤害吸收显示GUI
        float f = total - v;
        if (f > 0.0F && f < 3.4028235E37F && damageSrc.getTrueSource() instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) damageSrc.getTrueSource()).addStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(f * 10.0F));
        }
        //结算伤害与伤害吸收
        if (v != 0.0F) {
            float hp = livingEntity.getHealth();
            livingEntity.getCombatTracker().trackDamage(damageSrc, hp, v);
            livingEntity.setHealth(hp - v);
            livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() - v);
        }
    }

    /**
     * 伤害属性分配
     */
    private static Damage toDamage(LivingEntity target, DamageSource source, float amount) {
        Damage damage = new Damage();
        switch (source.getDamageType()) {
            case "player":
                damage.setP(amount);
                break;
            case "mob":
                mobDamage((MobEntity) Objects.requireNonNull(source.getTrueSource()), amount, damage);
                break;
            case "arrow":
                arrowDamage(source.getTrueSource(), (AbstractArrowEntity) source.getImmediateSource(), amount, damage);
                break;
            case "sweetBerryBush":
                damage.setP(2.0F);
                break;
            case "inFire":
            case "onFire":
                damage.setM(fixedDamageUp(target, 5.0F, 1));
                break;
            case "fireball":
                damage.setM(fixedDamageUp(source.getTrueSource(), 16.0F, 8));
                break;
            case "lightningBolt":
                damage.setM(64.0F);
                break;
            case "magic":
                //无来源魔法
                if (amount > 12.0F) {
                    //限制其他模组过高伤害
                    damage.setM(fixedDamageUp(target, 8.0F, 4));
                } else {
                    damage.setM(fixedDamageUp(target, amount, Math.max((int) amount, 1)));
                }
                break;
            case "indirectMagic":
                //间接有来源魔法
            case "dragonBreath":
                //龙息
                if (amount > 12.0F) {
                    //限制其他模组高的离谱的魔法伤害
                    damage.setM(fixedDamageUp(source.getTrueSource(), 8.0F, 4));
                } else {
                    damage.setM(fixedDamageUp(source.getTrueSource(), amount, Math.max((int) amount, 1)));
                }
                break;
            case "fall":
                //摔落
            case "flyIntoWall":
                //飞翔撞墙
                damage.setR(fixedDamageUp(target, amount, Math.max((int) amount, 1)));
            case "generic":
                //属性？？？
            case "anvil":
                //铁砧
            case "fallingBlock":
                //方块掉落
                damage.setR(amount);
                break;
            case "drown":
                //溺水
            case "dryout":
                //干涸
            case "wither":
                //凋零
            case "explosion":
                //特殊爆炸
            case "hotFloor":
                //熔岩块
            case "inWall":
                //墙内
            case "cramming":
                //拥挤
            case "cactus":
                //仙人掌
                damage.setR(fixedDamageUp(target, 2.0F, 4));
                break;
            case "starve":
                //饥饿
                damage.setR(fixedDamageUp(target, 4.0F, 8));
                break;
            case "lava":
                //岩浆
                damage.setR(fixedDamageUp(target, 5.0F, 10));
                break;
            case "explosion.player":
                //爆炸
                damage.setR(fixedDamageUp(source.getTrueSource(), 50.0F, 20));
                break;
            case "outOfWorld":
                damage.setR(9999.0F);
                break;
            default:
                damage.setR(fixedDamageUp(target, 1.0F, 1));
                break;
        }
        return damage;
    }

    /**
     * 计算玩家是否暴击
     */
    public static boolean isCri(PlayerEntity player) {
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        int cri;
        if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
            cri = ((Weapon) stack.getItem()).getCri(stack);
        } else {
            cri = Weapon.CRITICAL_BASE_NUM;
        }
        return cri > new Random().nextInt(1000);
    }

    /**
     * 计算Mob伤害
     */
    private static void mobDamage(MobEntity mob, float amount, Damage damage) {
        float v = MathHelper.clamp(amount, 2.0F, 20.0F);
        int lv = MobAttributesUtils.getLvByName(mob.getDisplayName().getString());
        damage.setP(v * 1.5F * (1 + lv));
    }

    /**
     * 计算箭矢伤害
     */
    private static void arrowDamage(Entity source, AbstractArrowEntity arrow, float amount, Damage damage) {
        if (source instanceof PlayerEntity) {
            damage.setP(Math.max((float) arrow.getDamage(), 4.0F));
            //增加TEC
            ItemStack stack = ((PlayerEntity) source).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractRangedWeapon) {
                ((AbstractRangedWeapon) stack.getItem()).incrTec(stack);
            }
        } else if (source instanceof MobEntity) {
            float v = MathHelper.clamp(amount, 2.0F, 20.0F);
            int lv = MobAttributesUtils.getLvByName(source.getDisplayName().getString());
            damage.setP(v * 1.5F * (1 + lv));
        } else {
            damage.setP(1.0F);
        }
    }

    /**
     * 固定伤害根据实体等级增幅
     */
    private static float fixedDamageUp(Entity entity, float amount, int offset) {
        int lv = 0;
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            lv = StoryPlayerDataManager.getLv(StoryPlayerDataManager.get(playerEntity.getCachedUniqueIdString()).getXp());
        } else if (entity instanceof MobEntity) {
            MobEntity mobEntity = (MobEntity) entity;
            lv = MobAttributesUtils.getLvByName(mobEntity.getDisplayName().getString());
        }
        if (lv > 0) {
            amount += lv * offset;
        }
        return amount;
    }

    /**
     * 计算盔甲（DEF）
     */
    private static float applyArmorCalculations(LivingEntity livingEntity, DamageSource source, float damage) {
        if (!source.isUnblockable()) {
            //结算盔甲损伤
            if (livingEntity instanceof PlayerEntity) {
                //最低1点,最高2(8/4)点
                ((PlayerEntity) livingEntity).inventory.func_234563_a_(source, Math.min(damage, 8.0F));
            }
            //计算伤害
            damage = getDamageAfterArmorAbsorb(damage, (float) livingEntity.getTotalArmorValue());
        }
        return damage;
    }

    /**
     * 计算盔甲吸收后的伤害
     */
    private static float getDamageAfterArmorAbsorb(float damage, float totalArmor) {
        return Math.max(damage - totalArmor, 0.0F);
    }

    /**
     * 计算盔甲韧性（PHY）
     */
    private static float applyToughnessCalculations(LivingEntity livingEntity, DamageSource source, float damage) {
        if (!source.isUnblockable()) {
            //结算盔甲损伤
            if (livingEntity instanceof PlayerEntity) {
                //最低1点,最高2(8/4)点
                ((PlayerEntity) livingEntity).inventory.func_234563_a_(source, Math.min(damage, 8.0F));
            }
            //计算伤害
            damage = getDamageAfterToughnessAbsorb(damage, (float) livingEntity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        }
        return damage;
    }

    /**
     * 计算盔甲韧性吸收后的伤害
     */
    private static float getDamageAfterToughnessAbsorb(float damage, float toughnessAttribute) {
        return Math.max(damage - toughnessAttribute, 0.0F);
    }

    /**
     * 计算药水和附魔减伤
     */
    private static float applyPotionDamageCalculations(LivingEntity livingEntity, DamageSource source, float damage) {
        if (source.isDamageAbsolute()) {
            return damage;
        } else {
            //计算药水
            if (livingEntity.isPotionActive(Effects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
                EffectInstance potionEffect = livingEntity.getActivePotionEffect(Effects.RESISTANCE);
                if (potionEffect != null) {
                    //每级抗性5%(1/20)免伤、最高4级
                    int i = MathHelper.clamp(potionEffect.getAmplifier() + 1, 1, 4);
                    int j = 20 - i;
                    float f = damage * (float) j;
                    float f1 = damage;
                    damage = Math.max(f / 20.0F, 0.0F);
                    float f2 = f1 - damage;
                    if (f2 > 0.0F && f2 < 3.4028235E37F) {
                        if (livingEntity instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity) livingEntity).addStat(Stats.DAMAGE_RESISTED, Math.round(f2 * 10.0F));
                        } else if (source.getTrueSource() instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity) source.getTrueSource()).addStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(f2 * 10.0F));
                        }
                    }
                }
            }
            //计算附魔
            if (damage <= 0.0F) {
                return 0.0F;
            } else {
                int k = EnchantmentHelper.getEnchantmentModifierDamage(livingEntity.getArmorInventoryList(), source);
                if (k > 0) {
                    damage = getDamageAfterMagicAbsorb(damage, (float) k);
                }
                return damage;
            }
        }
    }

    /**
     * 计算被附魔吸收后的伤害
     */
    private static float getDamageAfterMagicAbsorb(float damage, float enchantModifiers) {
        //最高20级
        float f = MathHelper.clamp(enchantModifiers, 0.0F, 20.0F);
        //每级1%免伤
        return damage * (1.0F - f / 100.0F);
    }

}
