package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.GroupRegistry;
import net.minecraft.item.Item;

/**
 * @description: 铁矿石
 * @date: 2021/9/4 0004 13:13
 */

public class IronOre extends Item {

    public IronOre() {
        super(new Properties().group(GroupRegistry.COMBAT_GROUP));
    }
}
