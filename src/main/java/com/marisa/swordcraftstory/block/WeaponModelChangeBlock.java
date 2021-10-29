package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.block.tile.WeaponModelChangeTileEntity;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.special.Hammer;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.SendPack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * 幻化锻冶台方块
 */

public class WeaponModelChangeBlock extends Block {

    public WeaponModelChangeBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WeaponModelChangeTileEntity();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            NonNullList<ItemStack> list = NonNullList.create();
            list.add(ItemRegistry.WEAPON_MODEL_CHANGE_BLOCK.get().getDefaultInstance());
            InventoryHelper.dropItems(worldIn, pos, list);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote || handIn != Hand.MAIN_HAND) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof Hammer) {
                Networking.INSTANCE.sendToServer(new SendPack("smithery.weaponModelChange", pos));
            }
        }
        return ActionResultType.SUCCESS;

    }
}