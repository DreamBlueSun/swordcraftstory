package com.marisa.swordcraftstory.item.combat.close.sword;

import com.marisa.swordcraftstory.item.ore.BigOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 铁剑
 */

public class IronSword extends AbstractSwordWeapon {

    public IronSword() {
        super(new BigOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipIron(tooltip);
    }
}
