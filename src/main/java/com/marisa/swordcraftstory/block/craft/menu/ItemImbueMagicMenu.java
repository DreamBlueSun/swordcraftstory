package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.smith.IStrengthen;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import com.marisa.swordcraftstory.smith.util.StrengthenHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 注魔锻冶台menu
 */

public class ItemImbueMagicMenu extends OneAddThreeGetOneMenu {

    public ItemImbueMagicMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ItemImbueMagicMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ItemImbueMagicMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_ITEM_IMBUE_MAGIC.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return !this.resultSlots.getItem(0).isEmpty();
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(INPUT_SLOT_1);
        this.removeStackInSlot(ADDITIONAL_SLOT_1);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_2);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_3);
    }

    private void removeStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(itemstack.getCount());
        this.inputSlots.setItem(slot, itemstack);
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_IMBUE_MAGIC_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(INPUT_SLOT_1);
        Item item0 = stack0.getItem();
        if (stack0.isEmpty() || !StoryUtils.isModItem(item0)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        int count = 0;
        for (int i = ADDITIONAL_SLOT_1; i <= ADDITIONAL_SLOT_3; i++) {
            ItemStack stack = this.inputSlots.getItem(i);
            Item item = stack.getItem();
            if (!stack.isEmpty() && item instanceof IStrengthen) {
                count++;
            }
        }
        if (count == 0) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        int max = StrengthenHelper.getStrengthenRank(super.player);
        int[] ints = StrengthenHelper.getStrengthenIds(stack0);
        if (max == 0 || (ints != null && ints.length + count > max)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack strengthen = stack0.copy();
        for (int i = ADDITIONAL_SLOT_1; i <= ADDITIONAL_SLOT_3; i++) {
            ItemStack stack = this.inputSlots.getItem(i);
            Item item = stack.getItem();
            if (!stack.isEmpty() && item instanceof IStrengthen) {
                strengthen = ((IStrengthen) this.inputSlots.getItem(i).getItem()).doStrengthen(strengthen);
            }
        }
        this.resultSlots.setItem(0, strengthen);
    }
}