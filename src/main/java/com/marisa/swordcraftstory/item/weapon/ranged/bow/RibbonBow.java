package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ore.PrettyOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 缎带长弓
 */

public class RibbonBow extends AbstractBowWeapon {

    public RibbonBow() {
        super(new PrettyOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipRibbon(tooltip);
    }
}
