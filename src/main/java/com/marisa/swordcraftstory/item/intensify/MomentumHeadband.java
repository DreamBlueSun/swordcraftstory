package com.marisa.swordcraftstory.item.intensify;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.intensify.helper.AbstractIntensify;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("绑在头上气势满满").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.MOMENTUM_HEAD_BAND.getId(), Intensifys.MOMENTUM_HEAD_BAND.getShow(), 2, 0, 0, 0);
    }
}
