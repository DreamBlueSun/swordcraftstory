package com.marisa.swordcraftstory.block.ore;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

/**
 * 矿石方块
 */

public class StoryOreBlock extends OreBlock {

    public StoryOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(0.8F), UniformInt.of(3, 7));
        this.setRegistryName(Story.MOD_ID + ":story_ore_block");
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState blockState, ServerLevel server, @NotNull BlockPos pos, @NotNull ItemStack stack) {
        if (!server.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            return;
        }
        if (stack.isEmpty() || !(stack.getItem() instanceof PickaxeItem)) {
            return;
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
            //精准采集掉落方块本身
            Containers.dropItemStack(server, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.STORY_ORE_BLOCK.get().getDefaultInstance());
        } else {
            //掉落素材
            Player player = server.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 8.0D, false);
            if (player == null || player.isCreative()) {
                return;
            }
            Containers.dropItemStack(server, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.STORY_ORE_BLOCK.get().getDefaultInstance());
        }
    }
}
