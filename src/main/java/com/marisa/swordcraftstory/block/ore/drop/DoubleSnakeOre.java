package com.marisa.swordcraftstory.block.ore.drop;

import com.marisa.swordcraftstory.block.ore.AbstractOre;
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
 * 双蛇矿石
 */

public class DoubleSnakeOre extends AbstractOre {

    public DoubleSnakeOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("矿石中不知为何浮现出蛇的模样").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 6;
    }
}
