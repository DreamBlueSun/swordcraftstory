package com.marisa.swordcraftstory.item.combat;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description:
 * @date: 2021/9/5 0005 6:36
 */

public class Novice extends CloseCombat {
    public Novice(int rank, int atk, int def, int phy, int agl, IItemTier tier) {
        super(rank, atk, def, phy, agl, tier);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("最适合初心者的练习用武器").mergeStyle(TextFormatting.WHITE));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
