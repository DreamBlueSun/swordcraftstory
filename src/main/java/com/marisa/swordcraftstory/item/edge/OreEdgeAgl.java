package com.marisa.swordcraftstory.item.edge;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.smith.util.EdgeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 敏捷强刃石
 */

public class OreEdgeAgl extends EdgeItem {

    public OreEdgeAgl() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TextComponent("比较常见的强刃石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack edge(ItemStack stack) {
        ItemStack copy = stack.copy();
        EdgeHelper.setAgl(copy, EdgeHelper.getAgl(stack) + 1);
        EdgeHelper.clearTec(copy);
        return copy;
    }
}
