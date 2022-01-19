package com.marisa.swordcraftstory.smith;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 装备制作接口
 */

public interface IMake {

    void doMake(ItemStack stack);

    int makeId();

    int makeRank();

    int makeAtk(Item item);

    int makeDef(Item item);

    int makePhy(Item item);

    int makeAgl(Item item);

    int makeDur(Item item);
}
