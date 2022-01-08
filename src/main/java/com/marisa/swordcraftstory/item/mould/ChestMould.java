package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;

/**
 * 胸甲模具
 */

public class ChestMould extends ArmorMould {

    private final static int OFFSET = 4;

    public ChestMould() {
        super();
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        return collapseArmor(ItemRegistry.CHEST_MOULD.get().getDefaultInstance(), stack, OFFSET);
    }

}
