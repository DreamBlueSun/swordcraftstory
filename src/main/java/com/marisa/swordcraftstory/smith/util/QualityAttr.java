package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.util.pojo.AbstractQualityAttr;
import net.minecraft.world.item.Item;

/**
 * 装备品质属性接口
 */

public interface QualityAttr {

    AbstractQualityAttr getAttr(Item item);

}
