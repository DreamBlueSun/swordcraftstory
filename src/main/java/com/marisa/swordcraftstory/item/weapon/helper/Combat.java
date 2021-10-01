package com.marisa.swordcraftstory.item.weapon.helper;

import net.minecraft.item.ItemStack;

/**
 * 战斗接口
 */

public interface Combat {

    WeaponType type();

    int getAtk(ItemStack stack);

    int getDef(ItemStack stack);

    int getAgl(ItemStack stack);

    /**
     * 获取不计入解体的Dur最大值
     */
    int getDurMax(ItemStack stack);

}
