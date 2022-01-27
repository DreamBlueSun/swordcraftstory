package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.IMarquetryContainer;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;

/**
 *
 */

public class MarquetryContainerHelper {

    private final static String ID = "story_smith_marquetry_container";

    public static void setMarquetryContainer(ItemStack stack, IMarquetryContainer container) {
        stack.getOrCreateTag().put(ID, IntTag.valueOf(container.getId()));
    }

    public static IMarquetryContainer getMarquetryContainer(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        return null;
    }

}
