package com.marisa.swordcraftstory.smith;

import com.marisa.swordcraftstory.util.StoryUtils;
import net.minecraft.world.item.*;

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
        } else if (item instanceof PickaxeItem || item instanceof TridentItem) {
            return PICKAXE;
        } else if (StoryUtils.isRangedWeapon(item)) {
            return RANGED_WEAPON;
        } else {
            return UNKNOWN;
        }
    }
}
