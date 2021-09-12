package com.marisa.swordcraftstory.item.combat.ranged.bow;

import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 初心者之弓
 * @date: 2021/9/4 0004 6:35
 */

public class NoviceBow extends BowCombat {

    public NoviceBow() {
        super(new IronOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipNovice(tooltip);
    }
}
