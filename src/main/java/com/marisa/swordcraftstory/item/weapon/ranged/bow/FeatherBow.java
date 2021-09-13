package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ore.WingsFossilOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 羽弓
 */

public class FeatherBow extends AbstractBowWeapon {

    public FeatherBow() {
        super(new WingsFossilOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipFeather(tooltip);
    }
}
