package com.marisa.swordcraftstory.block.ore;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.obj.DropQuality;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/**
 * 矿石方块
 */

public class StoryOreBlock extends OreBlock {

    public StoryOreBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(5));
    }

    @Override
    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            if (!stack.isEmpty() && stack.getItem() instanceof PickaxeItem) {
                //判断精准采集
                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
                    //掉落方块本身
                    NonNullList<ItemStack> list = NonNullList.create();
                    list.add(ItemRegistry.STORY_ORE_BLOCK.get().getDefaultInstance());
                    InventoryHelper.dropItems(worldIn, pos, list);
                } else {
                    //掉落素材
                    PlayerEntity player = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 8.0D, false);
                    if (player != null) {
                        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
                        int lv = StoryPlayerDataManager.getLv(storyPlayerData.getXp());
                        NonNullList<ItemStack> list = NonNullList.create();
                        list.add(DropQuality.randomDropQuality(lv));
                        InventoryHelper.dropItems(worldIn, pos, list);
                    }
                }
            }
        }
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    protected int getExperience(Random rand) {
        return MathHelper.nextInt(rand, 2, 6);
    }

}
