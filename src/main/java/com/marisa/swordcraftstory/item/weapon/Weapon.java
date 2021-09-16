package com.marisa.swordcraftstory.item.weapon;

import net.minecraft.item.ItemStack;

/**
 * 武器接口
 */

public interface Weapon extends Combat {

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

    /**
     * 获取武器的稀有度
     */
    int getRank();

    /**
     * 获取武器当前的暴击值
     */
    int getCri(ItemStack stack);

    /**
     * 获取武器当前的Dur
     */
    int getDur(ItemStack stack);

    /**
     * 武器解体获取模具方法
     */
    ItemStack collapse(ItemStack stack);

    /**
     * 获取武器当前的磨合度
     */
    int getTec(ItemStack stack);

    /**
     * 增加当前武器的磨合度
     */
    void incrTec(ItemStack stack);

    /**
     * 设置为已损坏
     */
    void setBroken(ItemStack stack);

    /**
     * 判断是否已损坏
     */
    boolean isBroken(ItemStack stack);
}
