package com.marisa.swordcraftstory.item.intensify;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.intensify.helper.AbstractIntensify;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
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
 * 气势头巾
 */

public class MomentumHeadband extends AbstractIntensify {

    public MomentumHeadband() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("绑在头上气势满满").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.MOMENTUM_HEAD_BAND.getId(), Intensifys.MOMENTUM_HEAD_BAND.getShow(), 2, 0, 0, 0);
    }
}
