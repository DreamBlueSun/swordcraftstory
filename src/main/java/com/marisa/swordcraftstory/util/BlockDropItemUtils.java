package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.util.obj.DropQuality;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

/**
 * 掉落物品工具类
 */

public class BlockDropItemUtils {

    /**
     * @param player 玩家
     * @param block  方块
     * @param pos    方块坐标
     * @return void
     * @description 物语素材掉落
     * @date 2021/9/9 0009 22:02
     **/
    public static void dropItem(PlayerEntity player, Block block, BlockPos pos) {
        if (player == null || player.getEntityWorld().isRemote) {
            return;
        }
        if (player.isCreative() || !player.getEntityWorld().getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            return;
        }
        int quality = DropQuality.randomDropQuality();
        if (quality == 0) {
            return;
        }
        Item item;
        if (block instanceof OreBlock || block instanceof RedstoneOreBlock) {
            item = DropQuality.asOre(block, quality);
        } else {
            item = DropQuality.asStone(block, quality);
        }
        if (item != Items.AIR) {
            NonNullList<ItemStack> list = NonNullList.create();
            list.add(item.getDefaultInstance());
            InventoryHelper.dropItems(player.getEntityWorld(), pos, list);
        }
    }

}
