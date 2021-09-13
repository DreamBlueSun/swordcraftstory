package com.marisa.swordcraftstory.gui.container.slot;

import com.marisa.swordcraftstory.item.weapon.Weapon;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * Story武器Slot
 *
 * @date: 2021/9/8 12:27
 */

public class WeaponSlot extends Slot {

    public WeaponSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        //校验物品堆栈不是空气并且属于Story武器才可放入Slot
        return !stack.isEmpty() && stack.getItem() instanceof Weapon;
    }
}
