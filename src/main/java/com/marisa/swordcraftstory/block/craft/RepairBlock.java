package com.marisa.swordcraftstory.block.craft;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * 修理锻冶台
 */

public class RepairBlock extends CraftBlock {

    public RepairBlock() {
        super();
        this.setRegistryName(Story.MOD_ID + ":repair_block");
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        Vec3 vec3 = Vec3.atBottomCenterOf(pos);
        List<Monster> list = level.getEntitiesOfClass(Monster.class, new AABB(vec3.x() - 8.0D, vec3.y() - 5.0D, vec3.z() - 8.0D, vec3.x() + 8.0D, vec3.y() + 5.0D, vec3.z() + 8.0D), (p_9062_) -> p_9062_.isPreventingPlayerRest(player));
        if (!list.isEmpty()) {
            player.displayClientMessage(new TranslatableComponent("msg.swordcraftstory.smithery.repairAll.no_safe").withStyle(ChatFormatting.RED), true);
        } else {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.items.size(); i++) {
                ItemStack stack = inv.items.get(i);
                if (!stack.isEmpty() && StoryUtils.isWeapon(stack.getItem())) {
                    SmithHelper.plusDur(stack, SmithHelper.getDurMax(stack));
                }
            }
            player.displayClientMessage(new TranslatableComponent("msg.swordcraftstory.smithery.repairAll.ok").withStyle(ChatFormatting.GREEN), true);
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundRegistry.SMITH_BLOCK_REPAIR, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.REPAIR_BLOCK.get().getDefaultInstance());
        super.spawnAfterBreak(state, level, pos, stack);
    }

}