package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.item.ore.PrettyOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 缎带军刀
 */

public class RibbonSword extends AbstractSwordWeapon {

    public RibbonSword() {
        super(new PrettyOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipRibbon(tooltip);
    }
}
