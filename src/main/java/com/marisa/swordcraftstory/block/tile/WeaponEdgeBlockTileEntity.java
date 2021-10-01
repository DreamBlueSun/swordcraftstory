package com.marisa.swordcraftstory.block.tile;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.WeaponEdgePointIInt;
import com.marisa.swordcraftstory.gui.container.WeaponEdgeContainer;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
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
 * 强刃锻冶台tile
 */

public class WeaponEdgeBlockTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private Inventory inventory;

    private WeaponEdgePointIInt point;


    public WeaponEdgeBlockTileEntity() {
        super(TileEntityTypeRegistry.WEAPON_EDGE_BLOCK_TILE_ENTITY.get());
        this.inventory = new Inventory(1);
        this.point = new WeaponEdgePointIInt();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Story.MOD_ID + ".one_slot_tile_entity");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new WeaponEdgeContainer(sycID, inventory, this.getPos(), this.point);
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
        if (!world.isRemote) {
            BlockPos pos = this.getPos();
            this.point.set(1, pos.getX());
            this.point.set(2, pos.getY());
            this.point.set(3, pos.getZ());
            ItemStack stack = this.inventory.getStackInSlot(0);
            if (!stack.isEmpty() && stack.getItem() instanceof Weapon && ((Weapon) stack.getItem()).getTec(stack) == Weapon.MAX_TEC) {
                this.point.set(0, 1);
            } else {
                this.point.set(0, 0);
            }
        }
    }
}
