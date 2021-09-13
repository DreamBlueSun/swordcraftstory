package com.marisa.swordcraftstory.item.weapon;

import net.minecraft.item.ItemStack;

/**
 * 战斗接口
 */

public interface Combat {

    /**
     * 武器最大磨合度
     */
    int MAX_TEC = 255;

    /**
     * 武器基础暴击率
     */
    int CRITICAL_BASE_NUM = 50;

    /**
     * 武器敏捷加成速度倍率
     */
    double AGL_SPEED_BASE_NUM = 0.01D;

    /**
     * 武器强刃一次ATK提升量
     */
    int INTENSIFY_EDGE_ONCE_NUM_ATK = 2;

    /**
     * 武器强刃一次DEF提升量
     */
    int INTENSIFY_EDGE_ONCE_NUM_DEF = 3;

    /**
     * 武器强刃一次AGL提升量
     */
    int INTENSIFY_EDGE_ONCE_NUM_AGL = 1;

    /**
     * 武器强刃一次DUR提升量
     */
    int INTENSIFY_EDGE_ONCE_NUM_DUR = 5;

    boolean isMould();

    WeaponType type();

    int getRank();

    int getAtk(ItemStack stack);

    int getDef(ItemStack stack);

    int getPhy(ItemStack stack);

    int getAgl(ItemStack stack);

    int getDur(ItemStack stack);

    int getDurMax(ItemStack stack);

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

    /**
     * 武器解体获取模具方法
     */
    ItemStack collapse(ItemStack stack);

}
