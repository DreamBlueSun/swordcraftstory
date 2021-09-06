package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.combat.Combat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.DamageSource;

import java.util.Objects;
import java.util.Random;

/**
 * @description:
 * @date: 2021/9/4 0004 1:49
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
                defaultMobDamage((MobEntity) source.getTrueSource(), damage);
                break;
            case "sweetBerryBush":
                damage.setP(2.0F);
                break;
            case "inFire":
            case "onFire":
                damage.setM(10.0F);
            case "fireball":
                damage.setM(20.0F);
                break;
            case "lightningBolt":
                damage.setM(30.0F);
                break;
            case "magic":
            case "indirectMagic":
            case "dragonBreath":
                damage.setM(amount);
                break;
            case "fall":
            case "flyIntoWall":
            case "generic":
            case "anvil":
            case "fallingBlock":
                damage.setR(amount);
                break;
            case "drown":
            case "dryout":
            case "wither":
            case "explosion":
            case "hotFloor":
            case "inWall":
            case "cramming":
            case "cactus":
                damage.setR(2.0F);
                break;
            case "starve":
                damage.setR(4.0F);
                break;
            case "lava":
                damage.setR(10.0F);
                break;
            case "explosion.player":
                damage.setR(50.0F);
                break;
            case "outOfWorld":
                damage.setR(9999.0F);
                break;
            default:
                damage.setR(1.0F);
                break;
        }
        //计算防御
        Defense defense = defense(target);
        //结果
        float count = count(damage, defense);
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
        int may = 0;
        boolean critical = false;
        ItemStack stack;
        switch (source.getDamageType()) {
            case "player":
            case "arrow":
                may = Combat.CRITICAL_BASE_NUM;
                if (source.getTrueSource() instanceof PlayerEntity) {
                    stack = ((PlayerEntity) source.getTrueSource()).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                    if (!stack.isEmpty() && stack.getItem() instanceof Combat) {
                        Combat item = (Combat) stack.getItem();
                        may = Combat.CRITICAL_BASE_NUM + (item.getTec(stack) / 5);
                    }
                }
                break;
            case "mob":
                may = Combat.CRITICAL_BASE_NUM;
                break;
            default:
                break;
        }
        if (may != 0) {
            critical = Combat.CRITICAL_BASE_NUM > new Random().nextInt(1000);
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
    private static void playerDamage(PlayerEntity e, Damage damage) {
        damage.setP(1.0F);
        ItemStack stack = e.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!stack.isEmpty()) {
            if ((stack.getItem() instanceof Combat)) {
                //story武器
                Combat item = (Combat) stack.getItem();
                damage.setP(item.getAtk(stack));
                item.incrTec(stack);
            } else if (stack.getItem() instanceof TieredItem) {
                //非story武器
                damage.setP(((TieredItem) stack.getItem()).getTier().getAttackDamage() + 1.0F);
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
            } else if (stack.getItem() instanceof Combat) {
                Combat item = (Combat) stack.getItem();
                damage.setP(item.getAtk(stack));
                item.incrTec(stack);
            } else if (stack.getItem() instanceof BowItem) {
                damage.setP(4.0F);
            }
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
    private static void defaultMobDamage(MobEntity e, Damage damage) {
        damage.setP(24.0F);
        //TODO 怪物分别个性化指定值
        if (e instanceof EnderDragonEntity) {
            damage.setP(36.0F);
        }
    }

    /**
     * @param target 目标实体
     * @return com.marisa.swordcraftstory.util.Defense
     * @description 计算目标实体的防御力
     * @date 2021/9/4 0004 6:55
     **/
    private static Defense defense(Entity target) {
        Defense defense = new Defense();
        if (target instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) target;
            defense.setP(e.getTotalArmorValue());
            defense.setM((int) e.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
            //计入武器防御力
            ItemStack stack = e.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof Combat) {
                Combat item = (Combat) stack.getItem();
                defense.addP(item.getDef(stack));
                defense.addM(item.getPhy(stack));
            }
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
    private static float count(Damage damage, Defense defense) {
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
        //TODO 举盾时抵挡90%伤害
        return Math.max(v, 1.0F);
    }

}
