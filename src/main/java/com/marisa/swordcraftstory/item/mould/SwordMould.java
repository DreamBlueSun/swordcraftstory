package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 剑模具
 */

public class SwordMould extends WeaponMould {

    private final static int OFFSET = 7;

    public SwordMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.SWORD_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
