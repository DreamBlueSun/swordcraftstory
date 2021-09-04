package com.marisa.swordcraftstory.item.mould;

import net.minecraft.item.Item;

/**
 * @description:
 * @date: 2021/9/1 0001 1:38
 */

public abstract class Mould extends Item {

    /**
     * 攻击力
     */
    private int atk;

    /**
     * 物理防御力
     */
    private int def;

    /**
     * 魔法防御力
     */
    private int phy;

    /**
     * 敏捷值
     */
    private int agl;

    /**
     * 耐久度
     */
    private int dur;

    /**
     * 磨合度
     */
    private int tec;

    public Mould(Properties properties) {
        super(properties);
    }
}
