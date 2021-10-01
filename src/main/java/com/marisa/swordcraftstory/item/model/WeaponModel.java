package com.marisa.swordcraftstory.item.model;

import net.minecraft.item.ItemStack;

/**
 * 模型接口
 */

public interface WeaponModel {

    /**
     * 组装模型
     */
    boolean build(ItemStack stack);

    /**
     * 获取模型名称
     */
    String getModelName(ItemStack stack);

}
