package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 武器升阶锻冶台menu
 */

public class RankUpMenu extends ItemCombinerMenu {

    private final static int RANK_LV_NEED_ONCE = 4;

    public RankUpMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public RankUpMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public RankUpMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_RANK_UP.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        ItemStack stack = this.resultSlots.getItem(0);
        if (stack.isEmpty()) {
            return false;
        }
        int lv = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
        return SmithNbtUtils.getRank(stack) <= lv / RANK_LV_NEED_ONCE;
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.RANK_UP_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(0);
        if (stack0.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        if (!(stack0.getItem() instanceof TieredItem || stack0.getItem() instanceof ProjectileWeaponItem || stack0.getItem() instanceof ArmorItem)) {
            return;
        }
        ItemStack stack1 = this.inputSlots.getItem(1);
        if (stack1.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        if (stack1.getItem() instanceof AbstractOre ore) {
            if (ore.rank() > PlayerDataManager.getLv(PlayerDataManager.get(this.player.getStringUUID()).getXp()) / RANK_LV_NEED_ONCE) {
                return;
            }
            ItemStack stack = ore.itemRankUp(stack0);
            if (!stack.isEmpty()) {
                this.resultSlots.setItem(0, stack);
            }
        }
    }
}
