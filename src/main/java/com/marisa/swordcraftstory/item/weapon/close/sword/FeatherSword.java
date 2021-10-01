package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.item.ore.WingsFossilOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 羽毛匕首
 */

public class FeatherSword extends AbstractSwordWeapon {

    public FeatherSword() {
        super(new WingsFossilOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipFeather(tooltip);
    }
}
