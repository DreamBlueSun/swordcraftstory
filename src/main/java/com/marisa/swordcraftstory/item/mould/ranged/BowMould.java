package com.marisa.swordcraftstory.item.mould.ranged;

import com.marisa.swordcraftstory.group.GroupRegistry;
import com.marisa.swordcraftstory.item.mould.Mould;

/**
 * @description:
 * @date: 2021/9/1 0001 1:37
 */

public class BowMould extends Mould {

    public BowMould() {
        super(new Properties().group(GroupRegistry.COMBAT_GROUP));
    }
}
