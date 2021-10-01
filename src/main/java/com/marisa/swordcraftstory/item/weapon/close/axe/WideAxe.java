package com.marisa.swordcraftstory.item.weapon.close.axe;

import com.marisa.swordcraftstory.item.ore.BigIronOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractAxeWeapon;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 阔斧
 */

public class WideAxe extends AbstractAxeWeapon {

    public WideAxe() {
        super(new BigIronOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipBigIron(tooltip);
    }
}
