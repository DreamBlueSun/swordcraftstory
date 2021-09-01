package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.group.GroupRegistry;
import net.minecraft.item.Item;

/**
 * @description:
 * @date: 2021/9/2 0002 0:24
 */

public class Hammer extends Item {
    public Hammer() {
        super(new Properties().group(GroupRegistry.COMBAT_GROUP));
    }
}
