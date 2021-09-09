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
                //铁：（1：铁矿石，2：斩铁矿，3：大铁矿石，4：铁刃石，5：钢牙原石）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.IRON_ORE.get();
                }
            case "block.minecraft.gold_ore":
                //金：（1：翅膀化石，2：重金矿，3：幻想矿，4：艾克赛尔矿石，5：光辉之石）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.WINGS_FOSSIL_ORE.get();
                }
            case "block.minecraft.diamond_ore":
                //钻石：（1：尤佳矿石，2：花纹原石，3：诺比尔原石，4：露萤密石，5：被诅咒的矿石）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.UKA_ORE.get();
                }
            case "block.minecraft.coal_ore":
                //煤：（1：大矿石，2：黑石矿石，3：撒修矿石，4：肉球的化石，5：优质古木的化石）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.BIG_ORE.get();
                }
            case "block.minecraft.redstone_ore":
                //红石：（1：艾尔弗伊斯原石，2：斯普林特矿石，3：优美斯矿石，4：忍邪矿，5：机械原石）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.ELPHUS_ORE.get();
                }
            case "block.minecraft.lapis_ore":
                //青金石：（1：斯兰德原石，2：加德英矿石，3：帕尼修矿，4：雷齐斯托原石，5：自然矿）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.SLANDER_ORE.get();
                }
            case "block.minecraft.emerald_ore":
                //绿宝石：（1：阿吉尼尔矿，2：雷吉亚矿，3：甲壳化石，4：双蛇矿石，5：贤刃矿）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.ARGENIR_ORE.get();
                }
            case "block.minecraft.nether_quartz_ore":
                //石英：（1：漂亮原石，2：加利亚矿，3：料理矿，4：狂乱幻舞矿，5：红莲矿）
                switch (quality) {
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        return Items.AIR;
                    case 1:
                        return ItemRegistry.PRETTY_ORE.get();
                }
        }
        return Items.AIR;
    }

}
