package com.marisa.swordcraftstory.item.repair;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 磨刀石
 */

public class knifeStone extends RepairItem {

    private static final int REPAIR_NUM = 150;

    public knifeStone() {
        super(REPAIR_NUM);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("可以对武器精加工恢复中量耐久和DUR").withStyle(ChatFormatting.WHITE));
    }
}
