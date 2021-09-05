package com.marisa.swordcraftstory.item.combat;

import net.minecraft.item.ItemStack;

/**
 * @description:
 * @date: 2021/9/4 0004 6:57
 */

public interface Combat {

    /**
     * 武器最大磨合度
     */
    int MAX_TEC = 255;

    int getRank();

    int getAtk(ItemStack stack);

    int getDef(ItemStack stack);

    int getPhy(ItemStack stack);

    int getAgl(ItemStack stack);

    //暂定技能消耗Dur
    int getDur(ItemStack stack);

    /**
     * @param stack ITEM_STACK
     * @return int
     * @description 获取当前物品的磨合度
     * @date 2021/9/5 0005 9:16
     **/
    int getTec(ItemStack stack);

    /**
     * @param stack ITEM_STACK
     * @return int
     * @description 增加当前物品的磨合度
     * @date 2021/9/5 0005 9:16
     **/
    void incrTec(ItemStack stack);
}
