package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.util.pojo.AbstractQualityAttr;
import net.minecraft.world.item.Item;

/**
 * 装备品质接口
 */

public interface IQuality {

    AbstractQualityAttr getAttr(Item item);

}
