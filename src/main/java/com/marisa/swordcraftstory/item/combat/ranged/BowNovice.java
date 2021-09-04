package com.marisa.swordcraftstory.item.combat.ranged;

import com.marisa.swordcraftstory.item.combat.RangedCombat;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 初心者之弓
 * @date: 2021/9/4 0004 6:35
 */

public class BowNovice extends RangedCombat {

    public BowNovice() {
        super(1, 4, 1, 0, -5, 50);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("最适合初心者的练习用武器").mergeStyle(TextFormatting.WHITE));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
