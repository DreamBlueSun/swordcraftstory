package com.marisa.swordcraftstory.block.craft.menu;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import com.marisa.swordcraftstory.item.mould.*;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.IMake;
import com.marisa.swordcraftstory.smith.util.MakeHelper;
import com.marisa.swordcraftstory.util.StoryUtils;
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
 * 制作锻冶台menu
 */

public class ItemMakeMenu extends AbstractItemMakeMenu {

    public final static int RANK_LV_NEED_ONCE = 4;

    public ItemMakeMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ItemMakeMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ItemMakeMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.TYPE_ITEM_MAKE.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        ItemStack stack = this.resultSlots.getItem(0);
        if (stack.isEmpty()) {
            return false;
        }
        int lv = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
        return MakeHelper.getMakeRank(stack) <= (lv / RANK_LV_NEED_ONCE) + 1;
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.shrinkStackInSlot(2);
        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.SMITH_BLOCK_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ITEM_MAKE_BLOCK);
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(0);
        Item item0 = stack0.getItem();
        if (stack0.isEmpty() || !StoryUtils.isModItem(item0) || MakeHelper.getMakeRank(stack0) != 0) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack stack1 = this.inputSlots.getItem(1);
        Item item1 = stack1.getItem();
        if (stack1.isEmpty() || !(item1 instanceof Mould)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        boolean f = false;
        if (item0 instanceof SwordItem && item1 instanceof SwordMould) {
            f = true;
        } else if (item0 instanceof AxeItem && item1 instanceof AxeMould) {
            f = true;
        } else if (item0 instanceof PickaxeItem && item1 instanceof PickaxeMould) {
            f = true;
        } else if (item0 instanceof BowItem && item1 instanceof BowMould) {
            f = true;
        } else if (item0 instanceof CrossbowItem && item1 instanceof CrossbowMould) {
            f = true;
        } else if (item0 instanceof TridentItem && item1 instanceof TridentMould) {
            f = true;
        } else if (item0 instanceof ArmorItem armor) {
            switch (armor.getSlot()) {
                case HEAD -> f = item1 instanceof HeadMould;
                case CHEST -> f = item1 instanceof ChestMould;
                case LEGS -> f = item1 instanceof LegsMould;
                case FEET -> f = item1 instanceof FeetMould;
            }
        }
        if (!f) {
            return;
        }
        ItemStack stack2 = this.inputSlots.getItem(2);
        if (stack2.isEmpty() || !(stack2.getItem() instanceof IMake)) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        ItemStack make = ((Mould) item1).make(stack0, stack1, (IMake) stack2.getItem());
        if (make.isEmpty()) {
            return;
        }
        this.resultSlots.setItem(0, make);
    }
}
