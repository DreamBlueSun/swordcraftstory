package com.marisa.swordcraftstory.block.tile;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IntensifyEdgeContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

/**
 * @description:
 * @date: 2021/9/7 0007 22:09
 */

public class SmithingBlockTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private Inventory inventory;

    public SmithingBlockTileEntity() {
        super(TileEntityTypeRegistry.SMITHING_BLOCK_TILE_ENTITY.get());
        this.inventory = new Inventory(1);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Story.MOD_ID + ".one_slot_tile_entity");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new IntensifyEdgeContainer(sycID, inventory, this.getPos(), this.world);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.inventory.addItem(ItemStack.read(nbt.getCompound("item")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack = this.inventory.getStackInSlot(0).copy();
        compound.put("item", itemStack.serializeNBT());
        return super.write(compound);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void tick() {
        //計算物品能否强刃
    }
}