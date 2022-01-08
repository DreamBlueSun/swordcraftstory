package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
 * 抽象模具类
 */

public abstract class Mould extends Item {

    public Mould() {
        super(new Properties().stacksTo(1).tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        toolTip.add(new TextComponent("模具").withStyle(ChatFormatting.LIGHT_PURPLE));
        toolTip.add(new TextComponent(""));
    }

    public abstract ItemStack collapse(ItemStack stack);

    public abstract ItemStack make(ItemStack stack, ItemStack mould, AbstractOre ore);

}
