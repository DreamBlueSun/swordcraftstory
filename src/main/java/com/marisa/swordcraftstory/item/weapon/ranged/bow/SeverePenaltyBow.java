package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ore.HeavyGoldOre;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 重罚之弓
 */

public class SeverePenaltyBow extends AbstractBowWeapon {

    public SeverePenaltyBow() {
        super(new HeavyGoldOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipHeavyGold(tooltip);
    }
}
