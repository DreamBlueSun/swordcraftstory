package com.marisa.swordcraftstory.item.weapon.close.axe;

import com.marisa.swordcraftstory.item.ore.PrettyOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractAxeWeapon;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 缎带斧
 */

public class RibbonAxe extends AbstractAxeWeapon {

    public RibbonAxe() {
        super(new PrettyOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipRibbon(tooltip);
    }
}
