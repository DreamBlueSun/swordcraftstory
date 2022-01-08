package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 弓模具
 */

public class BowMould extends WeaponMould {

    private final static int OFFSET = 5;

    public BowMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.BOW_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
