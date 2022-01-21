package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
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
        this.shrinkStackInSlot(INPUT_SLOT_1, 1);
        int index = this.inputSlots.getItem(ADDITIONAL_SLOT_1).getCount();
        this.shrinkStackInSlot(ADDITIONAL_SLOT_1, index);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_2, index);
        this.shrinkStackInSlot(ADDITIONAL_SLOT_3, index);
    }

    private void shrinkStackInSlot(int slot, int count) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(count);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_IMBUE_MAGIC_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(INPUT_SLOT_1);
        if (stack0.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack stack1 = this.inputSlots.getItem(ADDITIONAL_SLOT_1);
        if (stack1.isEmpty() || !isCopperBlock(stack1.getItem())) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack stack2 = this.inputSlots.getItem(ADDITIONAL_SLOT_2);
        if (stack2.isEmpty() || !(stack2.getItem() == Items.AMETHYST_SHARD)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack stack3 = this.inputSlots.getItem(ADDITIONAL_SLOT_3);
        if (stack3.isEmpty() || !(stack3.getItem() == Items.GOLD_BLOCK)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        final int index = stack1.getCount();
        if (index <= 0) return;
        ItemStack copy = stack0.copy();
        ListTag listtag = copy.getEnchantmentTags();
        if (listtag.size() < index) return;
        CompoundTag compoundtag = listtag.getCompound(index - 1);
        int lv = EnchantmentHelper.getEnchantmentLevel(compoundtag);
        if (lv >= 10) return;
        if (stack2.getCount() < lv || stack3.getCount() < lv) return;
        Enchantment enchantment = Enchantment.byId(compoundtag.getInt("id"));
        if (enchantment == null) return;
        int max = enchantment.getMaxLevel();
        if (max == 1) return;
        if ((max == 2 || max == 3) && lv >= 5) return;
        this.access.execute((level, blockPos) -> {
            int power = 0;
            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    if ((k != 0 || l != 0) && level.isEmptyBlock(blockPos.offset(l, 0, k)) && level.isEmptyBlock(blockPos.offset(l, 1, k))) {
                        power += getPower(level, blockPos.offset(l * 2, 0, k * 2));
                        power += getPower(level, blockPos.offset(l * 2, 1, k * 2));
                        if (l != 0 && k != 0) {
                            power += getPower(level, blockPos.offset(l * 2, 0, k));
                            power += getPower(level, blockPos.offset(l * 2, 1, k));
                            power += getPower(level, blockPos.offset(l, 0, k * 2));
                            power += getPower(level, blockPos.offset(l, 1, k * 2));
                        }
                    }
                }
            }
            if (power < index) return;
            EnchantmentHelper.setEnchantmentLevel(compoundtag, lv + 1);
            this.resultSlots.setItem(0, copy);
        });
    }

    private boolean isCopperBlock(Item item) {
        return item == Items.COPPER_BLOCK || item == Items.EXPOSED_COPPER || item == Items.WEATHERED_COPPER || item == Items.OXIDIZED_COPPER;
    }

    private float getPower(Level world, BlockPos pos) {
        return world.getBlockState(pos).getEnchantPowerBonus(world, pos);
    }
}
