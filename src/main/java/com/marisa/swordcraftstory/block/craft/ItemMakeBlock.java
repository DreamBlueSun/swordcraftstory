package com.marisa.swordcraftstory.block.craft;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemMakeMenu;
import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


/**
 * 制作锻冶台
 */

public class ItemMakeBlock extends Block {

    public ItemMakeBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F));
        this.setRegistryName(Story.MOD_ID + ":item_make_block");
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_ANVIL);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((openContainerId, inventory, player) ->
                new ItemMakeMenu(openContainerId, inventory, ContainerLevelAccess.create(level, pos)),
                new TranslatableComponent("制作锻冶台"));
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.ITEM_MAKE_BLOCK.get().getDefaultInstance());
        super.spawnAfterBreak(state, level, pos, stack);
    }
}