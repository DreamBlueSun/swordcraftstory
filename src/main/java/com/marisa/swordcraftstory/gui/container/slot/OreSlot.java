package com.marisa.swordcraftstory.gui.container.slot;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * Story矿石Slot
 *
 * @date: 2021/9/8 12:27
 */

public class OreSlot extends Slot {

    public OreSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        //校验物品堆栈不是空气并且属于Story矿石才可放入Slot
        return !stack.isEmpty() && stack.getItem() instanceof AbstractOre;
    }
}
