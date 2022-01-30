package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 三叉戟模具
 */

public class TridentMould extends WeaponMould {

    private final static int OFFSET = 6;

    public TridentMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.TRIDENT_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
