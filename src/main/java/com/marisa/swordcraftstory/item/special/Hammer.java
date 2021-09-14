package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.group.StoryGroup;
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
 * 铁锤
 */

public class Hammer extends SpecialItem {

    public Hammer() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("使用锻冶台的铁锤，是把不错的锤子").mergeStyle(TextFormatting.WHITE));
        tooltip.add(new TranslationTextComponent("作为锻冶师：").mergeStyle(TextFormatting.DARK_PURPLE));
        tooltip.add(new TranslationTextComponent("铁锤，即吾友！").mergeStyle(TextFormatting.DARK_PURPLE));
        tooltip.add(new TranslationTextComponent("铁锤，即吾力！").mergeStyle(TextFormatting.DARK_PURPLE));
        tooltip.add(new TranslationTextComponent("铁锤，即吾命！").mergeStyle(TextFormatting.DARK_PURPLE));
    }
}
