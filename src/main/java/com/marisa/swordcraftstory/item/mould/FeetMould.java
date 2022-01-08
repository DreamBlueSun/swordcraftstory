package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 鞋子模具
 */

public class FeetMould extends ArmorMould {

    private final static int OFFSET = 4;

    public FeetMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseArmor(ItemRegistry.FEET_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
