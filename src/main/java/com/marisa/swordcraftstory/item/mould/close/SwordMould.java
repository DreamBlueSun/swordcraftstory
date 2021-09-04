package com.marisa.swordcraftstory.item.mould.close;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.mould.Mould;

/**
 * @description:
 * @date: 2021/9/1 0001 1:37
 */

public class SwordMould extends Mould {

    public SwordMould() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }
}
