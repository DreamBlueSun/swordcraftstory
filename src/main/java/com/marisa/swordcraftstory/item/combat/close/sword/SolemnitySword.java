package com.marisa.swordcraftstory.item.combat.close.sword;

import com.marisa.swordcraftstory.item.ore.ArgenirOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 庄严之刃
 * @date: 2021/9/1 0001 22:27
 */

public class SolemnitySword extends SwordCombat {

    public SolemnitySword() {
        super(new ArgenirOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipSolemnity(tooltip);
    }
}
