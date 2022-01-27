package com.marisa.swordcraftstory.smith;

import com.marisa.swordcraftstory.smith.marquetry.slot.AbstractSlot;
import net.minecraft.world.item.ItemStack;

/**
 * 装饰品容器接口
 */

public interface IMarquetryContainer {

    int getId();

    AbstractSlot[] getSlots(ItemStack stack);

    //getSkills

    //addSkillEffects
}
