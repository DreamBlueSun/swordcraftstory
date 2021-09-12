package com.marisa.swordcraftstory.item.repair;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 磨刀石
 * @date: 2021/9/6 0006 21:26
 */

public class knifeStone extends RepairItem {

    private static final int REPAIR_NUM = 150;

    public knifeStone() {
        super(REPAIR_NUM);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("可以对武器精加工恢复中量耐久和DUR").mergeStyle(TextFormatting.WHITE));
    }
}
