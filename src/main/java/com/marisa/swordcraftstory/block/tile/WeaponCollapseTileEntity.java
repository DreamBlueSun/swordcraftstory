package com.marisa.swordcraftstory.block.tile;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.BlockPosIInt;
import com.marisa.swordcraftstory.gui.container.WeaponCollapseContainer;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

/**
 * 解体锻冶台tile
 */

public class WeaponCollapseTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private Inventory inventory;

    private BlockPosIInt posIInt;

    public WeaponCollapseTileEntity() {
        super(TileEntityTypeRegistry.WEAPON_COLLAPSE_TILE_ENTITY.get());
        this.inventory = new Inventory(2);
        this.posIInt = new BlockPosIInt();
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Story.MOD_ID + ".weapon_collapse_tile_entity");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new WeaponCollapseContainer(sycID, inventory, this.getPos(), this.posIInt);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.inventory.addItem(ItemStack.read(nbt.getCompound("item")));
        this.inventory.addItem(ItemStack.read(nbt.getCompound("item1")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack = this.inventory.getStackInSlot(0).copy();
        ItemStack itemStack1 = this.inventory.getStackInSlot(1).copy();
        compound.put("item", itemStack.serializeNBT());
        compound.put("item1", itemStack1.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            BlockPos pos = this.getPos();
            this.posIInt.set(0, pos.getX());
            this.posIInt.set(1, pos.getY());
            this.posIInt.set(2, pos.getZ());
        }
    }
}
