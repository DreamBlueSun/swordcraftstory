package com.marisa.swordcraftstory.item.weapon.helper;

import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

/**
 *
 */

public class WeaponUtils {

    public static int getBaseAttackDamage(Item item) {
        if (SmithNbtUtils.isRangedWeapon(item)) {
            return 0;
        } else if (item instanceof SwordItem swordItem) {
            return (int) swordItem.getDamage() + 1;
        } else if (item instanceof DiggerItem diggerItem) {
            return (int) diggerItem.getAttackDamage() + 1;
        } else {
            return 1;
        }
    }

}
