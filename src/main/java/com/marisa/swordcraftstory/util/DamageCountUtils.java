package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.item.combat.RangedCombat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;

import java.util.Objects;

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
                damage.setM(6.0F);
            case "fireball":
                damage.setM(16.0F);
                break;
            case "lightningBolt":
                damage.setM(32.0F);
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
                damage.setR(2.0F);
                break;
            case "starve":
                damage.setR(4.0F);
                break;
            case "lava":
                damage.setR(8.0F);
                break;
            case "explosion.player":
                damage.setR(50.0F);
                break;
            case "outOfWorld":
                damage.setR(9999.0F);
                break;
            case "hotFloor":
            case "inWall":
            case "cramming":
            case "cactus":
            default:
                damage.setR(1.0F);
                break;
        }
        //计算防御
        Defense defense = defense(target);
        //结果
        return count(damage, defense);
    }

    /**
     * @param e      伤害来源玩家
     * @param damage 伤害值
     * @return void
     * @description 玩家伤害计算
     * @date 2021/9/4 0004 3:20
     **/
    private static void playerDamage(PlayerEntity e, Damage damage) {
        damage.setP(1.0F);
        ItemStack playerSlot = e.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!playerSlot.isEmpty()) {
            Item item = playerSlot.getItem();
            if (item instanceof SwordItem) {
                damage.setP(((SwordItem) item).getAttackDamage() + 1.0F);
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
            ItemStack arrowSlot = ((PlayerEntity) e).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!arrowSlot.isEmpty()) {
                Item item = arrowSlot.getItem();
                if (item instanceof RangedCombat) {
                    damage.setP(((RangedCombat) item).getAtk());
                }
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
     * @description
     * @date 2021/9/4 0004 6:55
     **/
    private static Defense defense(Entity target) {
        Defense defense = new Defense();
        if (target instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) target;
            defense.setP(e.getTotalArmorValue());
            defense.setM((int) e.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
            //计入武器防御力
            ItemStack slot = e.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!slot.isEmpty()) {
                Item item = slot.getItem();
                if (item instanceof Combat) {
                    Combat c = (Combat) item;
                    defense.addP(c.getDef());
                    defense.addM(c.getPhy());
                }
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
        //TODO 举盾时抵挡80%物理伤害
        return Math.max(v, 1.0F);
    }

}
