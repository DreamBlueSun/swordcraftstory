package com.marisa.swordcraftstory.item.weapon;

import net.minecraft.item.ItemStack;

/**
 * 战斗接口
 */

public interface Combat {

    WeaponType type();

    int getAtk(ItemStack stack);

    int getDef(ItemStack stack);

    int getPhy(ItemStack stack);

    int getAgl(ItemStack stack);

    int getDurMax(ItemStack stack);

}
