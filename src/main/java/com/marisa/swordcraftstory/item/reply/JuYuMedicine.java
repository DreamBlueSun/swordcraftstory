package com.marisa.swordcraftstory.item.reply;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 鸠幽药
 */

public class JuYuMedicine extends ReplyItem {

    private static final float HEAL_NUM = 450.0F;

    public JuYuMedicine() {
        super(HEAL_NUM);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TextComponent("非常美味喝了以后HP回复").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }
}
