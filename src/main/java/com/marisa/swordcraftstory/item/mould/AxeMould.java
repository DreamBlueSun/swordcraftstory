package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 斧模具
 */

public class AxeMould extends WeaponMould {

    private final static int OFFSET = 6;

    public AxeMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.AXE_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
