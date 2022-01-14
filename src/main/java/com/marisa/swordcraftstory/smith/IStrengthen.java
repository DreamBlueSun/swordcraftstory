package com.marisa.swordcraftstory.smith;

import net.minecraft.world.item.ItemStack;

/**
 * 装备强化接口
 */

public interface IStrengthen {

    int MAX_SIZE = 3;

    /**
     * 强化
     */
    ItemStack doStrengthen(ItemStack stack);

    /**
     * 不同材料对应的强化ID
     */
    int strengthenId();

    /**
     * 不同材料对应强化ATK的值
     */
    int strengthenAtk();

    /**
     * 不同材料对应强化DEF的值
     */
    int strengthenDef();

    /**
     * 不同材料对应强化PHY的值
     */
    int strengthenPhy();

    /**
     * 不同材料对应强化AGL的值
     */
    int strengthenAgl();
}
