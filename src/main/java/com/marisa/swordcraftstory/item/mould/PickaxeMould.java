package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 镐模具
 */

public class PickaxeMould extends WeaponMould {

    private final static int OFFSET = 5;

    public PickaxeMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseWeapon(ItemRegistry.PICKAXE_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
