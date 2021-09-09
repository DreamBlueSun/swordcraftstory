package com.marisa.swordcraftstory.util.obj;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Random;

/**
 * @description: 掉落品质
 * @date: 2021/9/9 0009 21:06
 */

public class DropQuality {

    private static final int MIN = 1;
    private static final int MAX = 5;
    private static final int WEIGHT_DENOMINATOR = 1000;

    /**
     * 普通（1）
     */
    private static final int WEIGHT_ORDINARY = 24;

    /**
     * 优良（2）
     */
    private static final int WEIGHT_FINE = 12;

    /**
     * 卓越（3）
     */
    private static final int WEIGHT_OUTSTANDING = 8;

    /**
     * 杰出（4）
     */
    private static final int WEIGHT_EXCELLENT = 4;

    /**
     * 闪耀（5）
     */
    private static final int WEIGHT_BRILLIANT = 2;

    public static int randomDropQuality() {
        int r = new Random().nextInt(WEIGHT_DENOMINATOR);
        int drop = 0;
        if (r < (drop += WEIGHT_BRILLIANT)) {
            return 5;
        } else if (r < (drop += WEIGHT_EXCELLENT)) {
            return 4;
        } else if (r < (drop += WEIGHT_OUTSTANDING)) {
            return 3;
        } else if (r < (drop += WEIGHT_FINE)) {
            return 2;
        } else if (r < (drop + WEIGHT_ORDINARY)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static Item asItem(Block block, int quality) {
        switch (block.getTranslationKey()) {
            case "block.minecraft.iron_ore":
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                    case 1:
                        return ItemRegistry.IRON_ORE.get();
                    default:
                        return Items.AIR;
                }
            case "block.minecraft.gold_ore":
            case "block.minecraft.diamond_ore":
            default:
                return Items.AIR;
        }
    }

}
