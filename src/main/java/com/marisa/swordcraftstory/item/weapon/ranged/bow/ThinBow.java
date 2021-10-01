package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ore.SlanderOre;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 短弓
 */

public class ThinBow extends AbstractBowWeapon {

    public ThinBow() {
        super(new SlanderOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipThin(tooltip);
    }
}
