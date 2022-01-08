package com.marisa.swordcraftstory.item.edge;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
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
 * 韧性强刃石
 */

public class OreEdgePhy extends EdgeItem {

    public OreEdgePhy() {
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
        SmithNbtUtils.setPhy(copy, SmithNbtUtils.getPhyBase(stack) + 1);
        SmithNbtUtils.clearTec(copy);
        return copy;
    }
}
