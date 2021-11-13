package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.special.LuckTicket;
import com.marisa.swordcraftstory.item.special.LuckTicketMax;
import com.marisa.swordcraftstory.net.ManualLotteryItemInfoPack;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * 手摇抽奖机方块
 */

public class ManualLotteryMachineBlock extends Block {

    public ManualLotteryMachineBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            NonNullList<ItemStack> list = NonNullList.create();
            list.add(ItemRegistry.MANUAL_LOTTERY_MACHINE_BLOCK.get().getDefaultInstance());
            InventoryHelper.dropItems(worldIn, pos, list);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote() && handIn == Hand.MAIN_HAND) {
            ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if (!stack.isEmpty() && stack.getItem() instanceof LuckTicket) {
                NonNullList<ItemStack> list = NonNullList.create();
                if (stack.getItem() instanceof LuckTicketMax) {
                    for (int i = 0; i < 10; i++) {
                        list.add(DropQualityManualLotteryMachine.drop());
                    }
                } else {
                    list.add(DropQualityManualLotteryMachine.drop());
                }
                InventoryHelper.dropItems(worldIn, pos, list);
                //道具数量--
                stack.shrink(1);
            } else {
                PacketDistributor.PacketTarget target = PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player);
                Networking.MANUAL_LOTTERY_ITEM_INFO.send(target, new ManualLotteryItemInfoPack());
            }
        }
        return ActionResultType.SUCCESS;

    }
}
