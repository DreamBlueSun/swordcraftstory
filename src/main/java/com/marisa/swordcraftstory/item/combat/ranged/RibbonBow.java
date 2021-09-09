package com.marisa.swordcraftstory.item.combat.ranged;

import com.marisa.swordcraftstory.item.combat.RangedCombat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 缎带长弓
 * @date: 2021/9/4 0004 6:35
 */

public class RibbonBow extends RangedCombat {

    public RibbonBow() {
        super(3, 37, 11, 0, -5, 72);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipRibbon(tooltip);
    }
}
