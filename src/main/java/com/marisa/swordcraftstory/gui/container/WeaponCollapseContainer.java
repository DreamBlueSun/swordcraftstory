package com.marisa.swordcraftstory.gui.container;

import com.marisa.swordcraftstory.block.tile.WeaponCollapseTileEntity;
import com.marisa.swordcraftstory.gui.container.IInt.BlockPosIInt;
import com.marisa.swordcraftstory.gui.container.slot.WeaponSlot;
import com.marisa.swordcraftstory.gui.container.slot.MouldSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

/**
 * 解体武器Container
 */

public class WeaponCollapseContainer extends Container {

    private BlockPosIInt posIInt;

    public WeaponCollapseContainer(int id, PlayerInventory inv, BlockPos pos, BlockPosIInt posIInt) {
        super(ContainerTypeRegistry.WEAPON_COLLAPSE_CONTAINER.get(), id);
        this.posIInt = posIInt;
        trackIntArray(this.posIInt);
        //添加槽位
        WeaponCollapseTileEntity weaponCollapseTile = (WeaponCollapseTileEntity) inv.player.world.getTileEntity(pos);
        this.addSlot(new WeaponSlot(weaponCollapseTile.getInventory(), 0, 36, 20));
        this.addSlot(new MouldSlot(weaponCollapseTile.getInventory(), 1, 36 + 94, 20));
        layoutPlayerInventorySlots(inv, 40, 147);
    }

    public BlockPos getBlockPos() {
        return new BlockPos(this.posIInt.get(0), this.posIInt.get(1), this.posIInt.get(2));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        //判断玩家能否打开GUI，一般情况下这里都应该做一次玩家和方块的距离计算，用来防止玩家在过远的地方打开GUI
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        //控制玩家按住Shift键以后点击物品的行为。默认情况下需要实现按住Shift讲物品放入指定空的Slot行为
        return ItemStack.EMPTY;
    }


    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

}
