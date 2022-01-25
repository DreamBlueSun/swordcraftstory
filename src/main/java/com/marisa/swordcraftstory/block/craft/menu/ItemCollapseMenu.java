package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.smith.util.EnchantHelper;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 解体锻冶台menu
 */

public class ItemCollapseMenu extends AbstractItemCollapseMenu {

    public ItemCollapseMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ItemCollapseMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ItemCollapseMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_ITEM_COLLAPSE.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return !this.resultSlots.getItem(0).isEmpty();
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(0);
        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.SMITH_BLOCK_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_COLLAPSE_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack = this.inputSlots.getItem(0);
        Item item = stack.getItem();
        if (stack.isEmpty() || !StoryUtils.isModItem(item)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack collapse = Items.AIR.getDefaultInstance();
        if (item instanceof SwordItem) {
            collapse = ((Mould) ItemRegistry.SWORD_MOULD.get()).collapse(stack);
        } else if (item instanceof AxeItem) {
            collapse = ((Mould) ItemRegistry.AXE_MOULD.get()).collapse(stack);
        } else if (item instanceof PickaxeItem) {
            collapse = ((Mould) ItemRegistry.PICKAXE_MOULD.get()).collapse(stack);
        } else if (item instanceof BowItem) {
            collapse = ((Mould) ItemRegistry.BOW_MOULD.get()).collapse(stack);
        } else if (item instanceof CrossbowItem) {
            collapse = ((Mould) ItemRegistry.CROSSBOW_MOULD.get()).collapse(stack);
        } else if (item instanceof ArmorItem armor) {
            switch (armor.getSlot()) {
                case HEAD -> collapse = ((Mould) ItemRegistry.HEAD_MOULD.get()).collapse(stack);
                case CHEST -> collapse = ((Mould) ItemRegistry.CHEST_MOULD.get()).collapse(stack);
                case LEGS -> collapse = ((Mould) ItemRegistry.LEGS_MOULD.get()).collapse(stack);
                case FEET -> collapse = ((Mould) ItemRegistry.FEET_MOULD.get()).collapse(stack);
            }
        }
        EnchantHelper.copyEnchantmentTag(stack, collapse);
        if (!collapse.isEmpty()) {
            this.resultSlots.setItem(0, collapse);
        }
    }
}
