package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.item.Item;

/**
 * @description:
 * @date: 2021/9/2 0002 0:24
 */

public class Hammer extends Item {
    public Hammer() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }
}
