package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 铁锤
 */

public class Hammer extends SpecialItem {

    public Hammer() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("使用锻冶台的铁锤，是把不错的锤子").withStyle(ChatFormatting.WHITE));
        tooltip.add(new TranslatableComponent("作为锻冶师：").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(new TranslatableComponent("铁锤，即吾友！").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(new TranslatableComponent("铁锤，即吾力！").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(new TranslatableComponent("铁锤，即吾命！").withStyle(ChatFormatting.DARK_PURPLE));

        tooltip.add(new TranslatableComponent("对锻冶台右键使用").withStyle(ChatFormatting.GREEN));
    }
}
