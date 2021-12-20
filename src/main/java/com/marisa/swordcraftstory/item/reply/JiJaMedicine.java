package com.marisa.swordcraftstory.item.reply;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 基迦药
 */

public class JiJaMedicine extends ReplyItem {

    private static final float HEAL_NUM = 350.0F;

    public JiJaMedicine() {
        super(HEAL_NUM);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("喝下去可以回复HP").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }
}
