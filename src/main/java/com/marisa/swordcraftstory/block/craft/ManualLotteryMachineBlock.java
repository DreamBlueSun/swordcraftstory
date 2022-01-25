package com.marisa.swordcraftstory.block.craft;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.special.LuckTicket;
import com.marisa.swordcraftstory.item.special.LuckTicketMax;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


/**
 * 手摇抽奖机
 */

public class ManualLotteryMachineBlock extends CraftBlock {

    private int runPower;
    private boolean isRunning;
    private int runningTick;

    public ManualLotteryMachineBlock() {
        super();
        this.runPower = 0;
        this.isRunning = false;
        this.runningTick = 0;
        this.setRegistryName(Story.MOD_ID + ":manual_lottery_machine_block");
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
        if (this.isRunning) {
            if (++this.runningTick == 340) {
                //result
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.MANUAL_LOTTERY_MACHINE_BLOCK.get().getDefaultInstance());
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundRegistry.DONE_BLOCK_MANUAL_LOTTERY_MACHINE, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else if (this.runningTick >= 360) {
                //next
                this.isRunning = false;
                this.runningTick = 0;
            }
        } else if (this.runPower > 0) {
            //start
            this.runPower--;
            this.isRunning = true;
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundRegistry.RUNNING_BLOCK_MANUAL_LOTTERY_MACHINE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        super.tick(state, level, pos, random);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        ItemStack stack = player.getMainHandItem();
        if (!stack.isEmpty() && stack.getItem() instanceof LuckTicket) {
            //道具数量--
            stack.shrink(1);
            this.runPower = stack.getItem() instanceof LuckTicketMax ? 10 : 1;
        } else {
            //TODO 展示栏板
//            PacketDistributor.PacketTarget target = PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player);
//            Networking.MANUAL_LOTTERY_ITEM_INFO.send(target, new ManualLotteryItemInfoPack());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.MANUAL_LOTTERY_MACHINE_BLOCK.get().getDefaultInstance());
        super.spawnAfterBreak(state, level, pos, stack);
    }

}