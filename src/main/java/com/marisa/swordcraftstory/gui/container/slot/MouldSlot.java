package com.marisa.swordcraftstory.gui.container.slot;

import com.marisa.swordcraftstory.item.mould.Mould;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * Story模具Slot
 */

public class MouldSlot extends Slot {

    public MouldSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        //校验物品堆栈不是空气并且属于Story模具才可放入Slot
        return !stack.isEmpty() && stack.getItem() instanceof Mould;
    }
}
