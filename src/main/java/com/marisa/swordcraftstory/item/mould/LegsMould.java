package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 护腿模具
 */

public class LegsMould extends ArmorMould {

    private final static int OFFSET = 4;

    public LegsMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseArmor(ItemRegistry.LEGS_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
