package com.marisa.swordcraftstory.smith.util;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

public enum EMakeType {

    UNKNOWN,
    SWORD,
    AXE,
    PICKAXE,
    RANGED_WEAPON;

    public static EMakeType getByItem(Item item) {
        if (item instanceof SwordItem) {
            return SWORD;
        } else if (item instanceof AxeItem) {
            return AXE;
        } else if (item instanceof PickaxeItem) {
            return PICKAXE;
        } else if (StoryUtils.isRangedWeapon(item)) {
            return RANGED_WEAPON;
        } else {
            return UNKNOWN;
        }
    }
}
