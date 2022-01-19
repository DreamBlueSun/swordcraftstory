package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.util.pojo.AbstractQualityAttr;
import com.marisa.swordcraftstory.smith.util.pojo.ArmorQualityAttr;
import com.marisa.swordcraftstory.smith.util.pojo.WeaponQualityAttr;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;

/**
 * 鉴定装备工具类
 */

public class QualityHelper {

    private final static String ID = "story_smith_quality";

    public static void setQuality(ItemStack stack, EQuality quality) {
        stack.getOrCreateTag().put(ID, IntTag.valueOf(quality.getId()));
        quality.getAttr(stack.getItem()).smithModify(stack);
    }

    public static EQuality getQuality(ItemStack stack) {
        if (stack.getTag() == null) {
            return EQuality.UNKNOWN;
        }
        return EQuality.getById(stack.getTag().getInt(ID));
    }

    public static int getAtk(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof WeaponQualityAttr weaponQualityAttr) {
            return weaponQualityAttr.getAtk();
        }
        return 0;
    }

    public static int getDef(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof ArmorQualityAttr armorQualityAttr) {
            return armorQualityAttr.getDef();
        }
        return 0;
    }

    public static int getPhy(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof ArmorQualityAttr armorQualityAttr) {
            return armorQualityAttr.getPhy();
        }
        return 0;
    }

    public static int getAgl(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof WeaponQualityAttr weaponQualityAttr) {
            return weaponQualityAttr.getAgl();
        }
        return 0;
    }

    public static int getDur(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof WeaponQualityAttr weaponQualityAttr) {
            return weaponQualityAttr.getDur();
        }
        return 0;
    }

    public static int getCri(ItemStack stack) {
        AbstractQualityAttr attr = QualityHelper.getQuality(stack).getAttr(stack.getItem());
        if (attr instanceof WeaponQualityAttr weaponQualityAttr) {
            return weaponQualityAttr.getCri();
        }
        return 0;
    }


}
