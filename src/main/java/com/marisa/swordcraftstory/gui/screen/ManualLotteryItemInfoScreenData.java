package com.marisa.swordcraftstory.gui.screen;

import net.minecraft.item.ItemStack;

/**
 * 手摇抽奖机物品信息数据
 */

public class ManualLotteryItemInfoScreenData {

    private ItemStack itemStack1;
    private ItemStack itemStack2;
    private ItemStack itemStack3;
    private ItemStack itemStack4;
    private ItemStack itemStack5;

    public ManualLotteryItemInfoScreenData(ItemStack itemStack1, ItemStack itemStack2, ItemStack itemStack3, ItemStack itemStack4, ItemStack itemStack5) {
        this.itemStack1 = itemStack1;
        this.itemStack2 = itemStack2;
        this.itemStack3 = itemStack3;
        this.itemStack4 = itemStack4;
        this.itemStack5 = itemStack5;
    }

    public ItemStack getItemStack1() {
        return itemStack1;
    }

    public ItemStack getItemStack2() {
        return itemStack2;
    }

    public ItemStack getItemStack3() {
        return itemStack3;
    }

    public ItemStack getItemStack4() {
        return itemStack4;
    }

    public ItemStack getItemStack5() {
        return itemStack5;
    }
}
