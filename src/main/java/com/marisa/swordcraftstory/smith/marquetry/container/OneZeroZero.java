package com.marisa.swordcraftstory.smith.marquetry.container;

import com.marisa.swordcraftstory.smith.marquetry.slot.AbstractSlot;
import com.marisa.swordcraftstory.smith.marquetry.slot.Rank0;
import com.marisa.swordcraftstory.smith.marquetry.slot.Rank1;
import net.minecraft.world.item.ItemStack;

/**
 *
 */

public class OneZeroZero extends AbstractMarquetryContainer {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public AbstractSlot[] getSlots(ItemStack stack) {
        return new AbstractSlot[]{new Rank1(), new Rank0(), new Rank0()};
    }

}
