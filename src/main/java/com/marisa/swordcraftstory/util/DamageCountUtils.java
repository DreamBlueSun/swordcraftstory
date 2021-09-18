package com.marisa.swordcraftstory.util;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.item.weapon.ranged.AbstractRangedWeapon;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.obj.Damage;
import com.marisa.swordcraftstory.util.obj.Defense;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.TieredItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;

import java.util.Objects;
import java.util.Random;

/**
 * 伤害计算工具类
 */

public class DamageCountUtils {

    /**
     * @param source 伤害来源
     * @param target 伤害目标
     * @return float
     * @description 计算伤害方法
     * @date 2021/9/4 0004 1:54
     **/
    public static float damageResult(DamageSource source, Entity target, float amount) {
        Damage damage = new Damage();
        switch (source.getDamageType()) {
            case "player":
                playerDamage((PlayerEntity) Objects.requireNonNull(source.getTrueSource()), damage);
                break;
            case "arrow":
                arrowDamage(source.getTrueSource(), damage);
                break;
            case "mob":
                mobDamage((MobEntity) Objects.requireNonNull(source.getTrueSource()), damage);
                break;
            case "sweetBerryBush":
                damage.setP(2.0F);
                break;
            case "inFire":
            case "onFire":
                damage.setM(fixedDamageUp(target, 5.0F, 5));
            case "fireball":
                damage.setM(fixedDamageUp(source.getTrueSource(), 10.0F, 6));
                break;
            case "lightningBolt":
                damage.setM(60.0F);
                break;
            case "magic":
                //无来源魔法
                damage.setM(fixedDamageUp(target, amount, Math.max((int) amount, 1)));
                break;
            case "indirectMagic":
                //间接有来源魔法
            case "dragonBreath":
                //龙息
                damage.setM(fixedDamageUp(source.getTrueSource(), amount, Math.max((int) amount, 1)));
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
        //计算防御
        Defense defense = defense(target);
        //结果
        float count = count(damage, defense);
        //举盾时消耗受到伤害值50%的耐久来抵挡90%的伤害
        if (target instanceof LivingEntity && ((LivingEntity) target).isHandActive()) {
            LivingEntity livingEntity = (LivingEntity) target;
            Hand handIn = livingEntity.getActiveHand();
            ItemStack stack = livingEntity.getHeldItem(handIn);
            if (!stack.isEmpty() && stack.getItem() instanceof ShieldItem) {
                stack.damageItem(Math.max((int) count / 2, 1), livingEntity, (entity) -> entity.sendBreakAnimation(handIn));
                count = count / 10;
                //举盾伤害不足1时不受伤
                count = count > 1.0F ? count : 0.0F;
            }
        }
        //计算暴击
        count = critical(source, count);
        return count;
    }

    /**
     * @param source 伤害来源
     * @param count  未执行暴击的伤害数值
     * @return float
     * @description 伤害数值执行暴击逻辑
     * @date 2021/9/5 0005 11:30
     **/
    private static float critical(DamageSource source, float count) {
        if (count == 0.0F) {
            return count;
        }
        int may = 0;
        boolean critical = false;
        ItemStack stack;
        switch (source.getDamageType()) {
            case "player":
            case "arrow":
                if (source.getTrueSource() instanceof PlayerEntity) {
                    stack = ((PlayerEntity) source.getTrueSource()).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                    if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
                        may = ((Weapon) stack.getItem()).getCri(stack);
                    }
                } else {
                    may = Weapon.CRITICAL_BASE_NUM;
                }
                break;
            case "mob":
                may = Weapon.CRITICAL_BASE_NUM;
                break;
            default:
                break;
        }
        if (may != 0) {
            critical = may > new Random().nextInt(1000);
        }
        if (critical) {
            count = count * 2;
        }
        return count;
    }

    /**
     * @param e      伤害来源玩家
     * @param damage 伤害值
     * @return void
     * @description 玩家普攻伤害计算
     * @date 2021/9/4 0004 3:20
     **/
    public static void playerDamage(PlayerEntity e, Damage damage) {
        damage.setP(1.0F);
        ItemStack stack = e.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!stack.isEmpty()) {
            if (stack.getItem() instanceof Weapon) {
                //story武器
                Weapon weapon = (Weapon) stack.getItem();
                //弓平A伤害为攻击力的一半
                if (stack.getItem() instanceof AbstractRangedWeapon) {
                    damage.setP((float) (weapon.getAtk(stack) / 2));
                } else {
                    damage.setP(weapon.getAtk(stack));
                }
                weapon.incrTec(stack);
            } else if (stack.getItem() instanceof TieredItem) {
                //非story武器
                Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
                double amount = ((AttributeModifier) attributeModifiers.get(Attributes.ATTACK_DAMAGE).toArray()[0]).getAmount();
                damage.setP((float) amount + 1.0F);
            }
        }
    }

    /**
     * @param e      伤害来源实体
     * @param damage 伤害值
     * @return void
     * @description 箭伤害计算
     * @date 2021/9/4 0004 2:30
     **/
    private static void arrowDamage(Entity e, Damage damage) {
        damage.setR(2.0F);
        if (e instanceof PlayerEntity) {
            damage.setR(0.0F);
            ItemStack stack = ((PlayerEntity) e).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (stack.isEmpty()) {
                damage.setP(1.0F);
            } else if (stack.getItem() instanceof AbstractRangedWeapon) {
                //story远程武器
                AbstractRangedWeapon weapon = (AbstractRangedWeapon) stack.getItem();
                damage.setP(weapon.getAtk(stack));
                weapon.incrTec(stack);
            } else if (stack.getItem() instanceof BowItem) {
                damage.setP(4.0F);
            }
        } else if (e instanceof MobEntity) {
            //怪物箭矢伤害
            damage.setP(6 + (int) ((MobEntity) e).getAttributeValue(Attributes.ATTACK_DAMAGE));
        } else {
            damage.setP(8.0F);
        }
    }


    /**
     * @param e      怪物实体
     * @param damage 伤害值
     * @return void
     * @description 原版怪物伤害计算
     * @date 2021/9/4 0004 2:31
     **/
    private static void mobDamage(MobEntity e, Damage damage) {
        try {
            AttributeModifierManager manager = e.getAttributeManager();
            if (manager.hasAttributeInstance(Attributes.ATTACK_DAMAGE)) {
                damage.setP((int) manager.getAttributeValue(Attributes.ATTACK_DAMAGE));
            } else {
                int lv = MobAttributesUtils.getLvByName(e.getDisplayName().getString());
                damage.setP(lv * 9);
            }
        } catch (Exception ex) {
            AttributeModifierManager attributeManager = e.getAttributeManager();
            Story.LOG.info("原版怪物伤害计算-异常数据{}", attributeManager.toString());
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
     * @param target 目标实体
     * @return com.marisa.swordcraftstory.util.obj.Defense
     * @description 计算目标实体的防御力
     * @date 2021/9/4 0004 6:55
     **/
    private static Defense defense(Entity target) {
        Defense defense = new Defense();
        if (target instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) target;
            defense.setP(e.getTotalArmorValue());
            defense.setM((int) e.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        }
        return defense;
    }

    /**
     * @param damage  伤害
     * @param defense 防御
     * @return float
     * @description 计算伤害结算值方法
     * @date 2021/9/4 0004 3:41
     **/
    public static float count(Damage damage, Defense defense) {
        float v = 0.0F;
        float v1 = damage.getP() - defense.getP();
        if (v1 > 0) {
            v += v1;
        }
        float v2 = damage.getM() - defense.getM();
        if (v2 > 0) {
            v += v2;
        }
        float v3 = damage.getR();
        if (v3 > 0) {
            v += v3;
        }
        return Math.max(v, 1.0F);
    }

}
