package com.marisa.swordcraftstory.item.material;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.item.Item;

/**
 * @description: 杂草的叶片
 * @date: 2021/9/6 0006 21:28
 */

public class GrassLeaves extends Item {
    public GrassLeaves() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }
}
