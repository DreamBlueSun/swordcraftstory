package com.marisa.swordcraftstory.block.craft;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.helper.ManualLotteryMachineDropQuality;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.special.LuckTicket;
import com.marisa.swordcraftstory.item.special.LuckTicketMax;
import com.marisa.swordcraftstory.save.cache.ManualLotteryMachineDataManager;
import com.marisa.swordcraftstory.save.cache.pojo.ManualLotteryMachineCache;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


/**
 * 手摇抽奖机
 */

public class ManualLotteryMachineBlock extends Block {

    public ManualLotteryMachineBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(128.0F, 128.0F));
        this.setRegistryName(Story.MOD_ID + ":manual_lottery_machine_block");
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        ItemStack stack = player.getMainHandItem();
        if (!stack.isEmpty() && stack.getItem() instanceof LuckTicket) {
            //道具数量--
            stack.shrink(1);
            ManualLotteryMachineDataManager.put(new ManualLotteryMachineCache((ServerLevel) level, pos, stack.getItem() instanceof LuckTicketMax ? 10 : 1, false, 0));
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

    public static void dropResult(TickEvent.WorldTickEvent event) {
        Map<BlockPos, ManualLotteryMachineCache> cacheMap = ManualLotteryMachineDataManager.get(event.world);
        if (cacheMap == null) return;
        for (BlockPos blockPos : new HashMap<>(cacheMap).keySet()) {
            ManualLotteryMachineCache cache = cacheMap.get(blockPos);
            ServerLevel level = cache.getLevel();
            BlockPos pos = cache.getBlockPos();
            if (cache.isRunning()) {
                if (cache.incrRunningTack() == 320) {
                    //result
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ManualLotteryMachineDropQuality.random());
                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundRegistry.DONE_BLOCK_MANUAL_LOTTERY_MACHINE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                } else if (cache.getRunningTick() >= 340) {
                    //next
                    cache.setRunning(false);
                    cache.setRunningTick(0);
                }
            } else if (cache.getRunPower() > 0) {
                //start
                cache.minusRunningTack();
                cache.setRunning(true);
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundRegistry.RUNNING_BLOCK_MANUAL_LOTTERY_MACHINE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            } else {
                //remove
                ManualLotteryMachineDataManager.rem(cache);
            }
        }
    }

}