package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.smith.IStrengthen;
import com.marisa.swordcraftstory.util.StoryUtils;
import com.marisa.swordcraftstory.smith.util.StrengthenHelper;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 强化锻冶台menu
 */

public class ItemStrengthenMenu extends OneAddThreeGetOneMenu {

    public ItemStrengthenMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ItemStrengthenMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ItemStrengthenMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_ITEM_STRENGTHEN.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return !this.resultSlots.getItem(0).isEmpty();
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(INPUT_SLOT_1);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_1);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_2);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_3);
        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.SMITH_BLOCK_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_STRENGTHEN_BLOCK);
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
        int max = StrengthenHelper.getStrengthenRank(super.player);
        if (count == 0 || count > max) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        int[] ints = StrengthenHelper.getStrengthenIds(stack0);
        int next = (ints == null ? 0 : ints.length) + count;
        if (next > max) {
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
