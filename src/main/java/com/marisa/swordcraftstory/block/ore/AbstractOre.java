package com.marisa.swordcraftstory.block.ore;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 抽象矿石类
 */

public abstract class AbstractOre extends Item {

    public AbstractOre(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("用途：升阶").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(rank())).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    public abstract int rank();
}
