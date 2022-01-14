package com.marisa.swordcraftstory.smith.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 *
 */

public class StoryUtils {

    private static final String UNKNOWN = "未知";

    public static String getItemName(Item item) {
        if (item == null) {
            return UNKNOWN;
        }
        ResourceLocation location = item.getRegistryName();
        if (location == null) {
            return UNKNOWN;
        }
        return "item." + location.getNamespace() + "." + location.getPath();
    }
}
