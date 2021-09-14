package com.marisa.swordcraftstory.item.reply;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 创可贴
 */

public class Woundplast extends ReplyItem {

    private static final float HEAL_NUM = 200.0F;

    public Woundplast() {
        super(HEAL_NUM);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("贴在伤口上可以回复少量HP").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public SoundEvent getEatSound() {
        return super.getEatSound();
    }
}
