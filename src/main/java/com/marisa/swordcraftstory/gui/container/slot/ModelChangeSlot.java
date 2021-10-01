package com.marisa.swordcraftstory.gui.container.slot;

import com.marisa.swordcraftstory.item.model.WeaponModel;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * Story幻化材料Slot
 */

public class ModelChangeSlot extends Slot {

    public ModelChangeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        //校验物品堆栈不是空气并且属于Story幻化材料才可放入Slot
        return !stack.isEmpty() && stack.getItem() instanceof WeaponModel;
    }
}
