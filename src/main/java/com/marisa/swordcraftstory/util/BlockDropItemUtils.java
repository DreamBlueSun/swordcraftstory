package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.util.obj.DropQuality;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

/**
 * @description: 掉落工具类
 * @date: 2021/9/9 0009 20:26
 */

public class BlockDropItemUtils {

    /**
     * @param player 玩家
     * @param block  方块
     * @param pos    方块坐标
     * @return void
     * @description 物语矿石掉落
     * @date 2021/9/9 0009 22:02
     **/
    public static void dropOre(PlayerEntity player, Block block, BlockPos pos) {
        if (player == null || player.getEntityWorld().isRemote) {
            return;
        }
        if (player.isCreative() || !player.getEntityWorld().getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            return;
        }
        if (block instanceof OreBlock) {
            int quality = DropQuality.randomDropQuality();
            if (quality == 0) {
                return;
            }
            NonNullList<ItemStack> list = NonNullList.create();
            list.add(DropQuality.asItem(block, quality).getDefaultInstance());
            InventoryHelper.dropItems(player.getEntityWorld(), pos, list);
        }
    }

}
