package com.marisa.swordcraftstory.item.mould.close.axe;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
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
 * 斧模具
 */

public class AxeMould extends Mould {

    public AxeMould() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("锻造斧类武器的素材").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public WeaponType type() {
        return WeaponType.AXE;
    }
}
