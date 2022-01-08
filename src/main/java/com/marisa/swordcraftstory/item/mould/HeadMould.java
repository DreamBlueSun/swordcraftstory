package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 头盔模具
 */

public class HeadMould extends ArmorMould {

    private final static int OFFSET = 4;

    public HeadMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseArmor(ItemRegistry.HEAD_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
