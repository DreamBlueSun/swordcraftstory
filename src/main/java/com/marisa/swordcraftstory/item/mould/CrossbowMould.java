package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 弩模具
 */

public class CrossbowMould extends WeaponMould {

    private final static int OFFSET = 5;

    public CrossbowMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.CROSSBOW_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
