package com.marisa.swordcraftstory.save.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.event.pojo.MobAttr;
import com.marisa.swordcraftstory.save.MobAttrSaveData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Random;

/**
 * Mob属性工具类
 */

public class MobAttributesUtils {

    /**
     * 设置mob属性值
     */
    private static void modifyMobAttr(Mob Mob, MobAttr mobAttr) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", mobAttr.getMaxHpAdd(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR, new AttributeModifier("armor modifier", mobAttr.getArmorAdd(), AttributeModifier.Operation.ADDITION));
        Mob.getAttributes().addTransientAttributeModifiers(builder.build());
    }

    /**
     * 加载保存的属性
     */
    public static void loadAttr(Mob Mob, MobAttr mobAttr) {
        //设置mob属性值
        modifyMobAttr(Mob, mobAttr);
        //回复生命值
        Mob.heal(mobAttr.getHp() - Mob.getHealth());
    }

    /**
     * 新增属性
     */
    public static MobAttr newAttr(Mob Mob, int playerLv) {
        //mob实体加入世界时，根据最近玩家等级增加属性
        int lv = mobSpanLv(playerLv);
        int maxHealthAdd = lv * (int) Mth.clamp(Mob.getMaxHealth(), 2.0F, 60.0F);
        int armorAdd = lv * 3;
        //如果mob原血量大于等于100(判定为BOSS生物)，则血量额外增加、防御额外增加
        boolean isBoos = Mob.getMaxHealth() >= 100;
        if (isBoos) {
            maxHealthAdd = lv * (int) Mth.clamp(Mob.getMaxHealth(), 100.0F, 400.0F);
            armorAdd *= 2;
        }
        MobAttr mobAttr = new MobAttr(Mob.getStringUUID(), lv, maxHealthAdd, armorAdd);
        //设置mob属性值
        modifyMobAttr(Mob, mobAttr);
        //回复生命值
        Mob.heal(mobAttr.getMaxHpAdd());
        return mobAttr;
    }

    /**
     * Mob出生等级
     */
    private static int mobSpanLv(int lv) {
        if (lv < 2) {
            return 0;
        }
        //随机+-0~5LV
        int offset = new Random().nextInt(6);
        if (offset == 0) {
            return lv;
        }
        if (new Random().nextInt(2) > 0) {
            lv += offset;
        } else {
            lv = Math.max(lv - offset, 0);
        }
        return lv;
    }

    /**
     * 获取Mob等级
     */
    public static int getMobLv(ServerLevel world, String uuid) {
        MobAttr mobAttr = MobAttrSaveData.getInstance(world).get(uuid);
        return mobAttr != null ? mobAttr.getLv() : 0;
    }

}