package com.marisa.swordcraftstory.block.ore;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.ore.helper.OreDropQuality;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * 矿石方块
 */

public class StoryOreBlock extends OreBlock {

    private final List<UniformInt> xpDropList = Arrays.asList(
            UniformInt.of(1, 2),
            UniformInt.of(2, 4),
            UniformInt.of(4, 8),
            UniformInt.of(8, 16),
            UniformInt.of(16, 32),
            UniformInt.of(32, 64),
            UniformInt.of(64, 128),
            UniformInt.of(128, 256),
            UniformInt.of(256, 512));


    public StoryOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).lightLevel((state) -> 7));
        this.setRegistryName(Story.MOD_ID + ":story_ore_block");
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack) {
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
            //精准采集掉落方块本身
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.STORY_ORE_BLOCK.get().getDefaultInstance());
        } else {
            //根据工具等阶掉落素材
            ItemStack random = OreDropQuality.random(SmithNbtUtils.getRank(stack));
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), random);
            //根据掉落物品掉落经验
            int rank = random.getItem() instanceof AbstractOre ore ? ore.rank() : 3;
            if (0 < rank && rank <= this.xpDropList.size()) {
                state.getBlock().popExperience(level, pos, this.xpDropList.get(rank - 1).sample(RANDOM));
            }
        }
        super.spawnAfterBreak(state, level, pos, stack);
    }
}
