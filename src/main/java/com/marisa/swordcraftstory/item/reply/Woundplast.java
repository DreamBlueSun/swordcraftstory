package com.marisa.swordcraftstory.item.reply;


import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.SoundEvent;

/**
 * @description: 创可贴
 * @date: 2021/9/6 0006 21:26
 */

public class Woundplast extends Reply {

    private static final float HEAL_NUM = 200.0F;

    public Woundplast() {
        super(HEAL_NUM);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public SoundEvent getEatSound() {
        return super.getEatSound();
    }
}
