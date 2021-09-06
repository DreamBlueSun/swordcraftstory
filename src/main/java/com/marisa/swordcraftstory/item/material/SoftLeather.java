package com.marisa.swordcraftstory.item.material;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.item.Item;

/**
 * @description: 柔软的皮革
 * @date: 2021/9/6 0006 21:30
 */

public class SoftLeather extends Item {
    public SoftLeather() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }
}
