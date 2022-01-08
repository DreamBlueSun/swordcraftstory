package com.marisa.swordcraftstory.block.craft.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class AbstractItemCollapseMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;
    protected final ResultContainer resultSlots = new ResultContainer();
    protected final Container inputSlots = new SimpleContainer(1) {
        public void setChanged() {
            super.setChanged();
            AbstractItemCollapseMenu.this.slotsChanged(this);
        }
    };
    protected final ContainerLevelAccess access;
    protected final Player player;

    protected abstract boolean mayPickup(Player player, boolean hasItem);

    protected abstract void onTake(Player player, ItemStack stack);

    protected abstract boolean isValidBlock(BlockState state);

    public AbstractItemCollapseMenu(@Nullable MenuType<?> type, int p_39774_, Inventory inventory, ContainerLevelAccess access) {
        super(type, p_39774_);
        this.access = access;
        this.player = inventory.player;
        this.addSlot(new Slot(this.inputSlots, INPUT_SLOT, 27, 47));
        this.addSlot(new Slot(this.resultSlots, RESULT_SLOT, 134, 47) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public boolean mayPickup(Player player) {
                return AbstractItemCollapseMenu.this.mayPickup(player, this.hasItem());
            }

            public void onTake(Player player, ItemStack stack) {
                AbstractItemCollapseMenu.this.onTake(player, stack);
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

    }

    public abstract void createResult();

    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (container == this.inputSlots) {
            this.createResult();
        }

    }

    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_39796_, p_39797_) -> this.clearContainer(player, this.inputSlots));
    }

    public boolean stillValid(Player player) {
        return this.access.evaluate((p_39785_, p_39786_) -> {
            return this.isValidBlock(p_39785_.getBlockState(p_39786_)) && player.distanceToSqr((double) p_39786_.getX() + 0.5D, (double) p_39786_.getY() + 0.5D, (double) p_39786_.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == RESULT_SLOT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else if (index != INPUT_SLOT) {
                if (index >= INV_SLOT_START && index < USE_ROW_SLOT_END) {
                    if (!this.moveItemStackTo(stack, INPUT_SLOT, RESULT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }
}