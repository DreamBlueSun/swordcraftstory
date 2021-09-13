package com.marisa.swordcraftstory.item.mould.close.sword;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.combat.CombatType;
import com.marisa.swordcraftstory.item.mould.Mould;
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
 * @description:
 * @date: 2021/9/1 0001 1:37
 */

public class SwordMould extends Mould {

    public SwordMould() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("锻造剑类武器的素材").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public CombatType type() {
        return CombatType.SWORD;
    }
}
