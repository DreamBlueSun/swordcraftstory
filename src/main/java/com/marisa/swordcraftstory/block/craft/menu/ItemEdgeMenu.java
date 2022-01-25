package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.item.edge.*;
import com.marisa.swordcraftstory.smith.util.EdgeHelper;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 强刃锻冶台menu
 */

public class ItemEdgeMenu extends ItemCombinerMenu {

    public ItemEdgeMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ItemEdgeMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ItemEdgeMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_ITEM_EDGE.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return !this.resultSlots.getItem(0).isEmpty();
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.SMITH_BLOCK_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_EDGE_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(0);
        Item item0 = stack0.getItem();
        if (stack0.isEmpty() || !StoryUtils.isModItem(item0)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack stack1 = this.inputSlots.getItem(1);
        Item item1 = stack1.getItem();
        if (stack1.isEmpty() || !(item1 instanceof EdgeItem)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        if (StoryUtils.isWeapon(item0)) {
            if (item1 instanceof OreEdgeDef || item1 instanceof OreEdgePhy) {
                return;
            }
        } else {
            if (item1 instanceof OreEdgeAtk || item1 instanceof OreEdgeAgl) {
                return;
            }
        }
        if (EdgeHelper.getTec(stack0) < 255) {
            return;
        }
        ItemStack edge = ((EdgeItem) item1).edge(stack0);
        this.resultSlots.setItem(0, edge);
    }
}
