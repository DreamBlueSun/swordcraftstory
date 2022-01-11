package com.marisa.swordcraftstory.item.ore.helper;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 掉落品质
 */

public class OreDropQuality {

    private static final List<ItemStack> LIST_ORE_RANK_1;
    private static final List<ItemStack> LIST_ORE_RANK_2;
    private static final List<ItemStack> LIST_ORE_RANK_3;
    private static final List<ItemStack> LIST_ORE_RANK_4;
    private static final List<ItemStack> LIST_ORE_RANK_5;
    private static final List<ItemStack> LIST_ORE_RANK_6;
    private static final List<ItemStack> LIST_ORE_RANK_7;
    private static final List<ItemStack> LIST_ORE_RANK_8;
    private static final List<ItemStack> LIST_ORE_RANK_9;

    private static final List<ItemStack> LIST_ORE_EDGE;

    static {
        LIST_ORE_RANK_1 = new ArrayList<>();
        LIST_ORE_RANK_1.add(ItemRegistry.HARD_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_1.add(ItemRegistry.SLANDER_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_1.add(ItemRegistry.IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2 = new ArrayList<>();
        LIST_ORE_RANK_2.add(ItemRegistry.WINGS_FOSSIL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.ELPHUS_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.BIG_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.ARGENIR_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3 = new ArrayList<>();
        LIST_ORE_RANK_3.add(ItemRegistry.PRETTY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.UKA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.REGIA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.HEAVY_GOLD_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.BIG_IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.CUT_IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_4 = new ArrayList<>();
        LIST_ORE_RANK_4.add(ItemRegistry.FANTASY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_4.add(ItemRegistry.CARAPACE_FOSSIL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_5 = new ArrayList<>();
        LIST_ORE_RANK_5.add(ItemRegistry.CUISINE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_5.add(ItemRegistry.GALIA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_6 = new ArrayList<>();
        LIST_ORE_RANK_6.add(ItemRegistry.SPRINT_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_6.add(ItemRegistry.DOUBLE_SNAKE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_7 = new ArrayList<>();
        LIST_ORE_RANK_7.add(ItemRegistry.LEIJI_STOWE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_7.add(ItemRegistry.XCEL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_8 = new ArrayList<>();
        LIST_ORE_RANK_8.add(ItemRegistry.STEEL_TEETH_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_8.add(ItemRegistry.DEW_FIREFLY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_9 = new ArrayList<>();
        LIST_ORE_RANK_9.add(ItemRegistry.LUMINOSITY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_9.add(ItemRegistry.CATS_PAW_ORE.get().getDefaultInstance());

        LIST_ORE_EDGE = new ArrayList<>();
        LIST_ORE_EDGE.add(ItemRegistry.ORE_EDGE_AGL.get().getDefaultInstance());
        LIST_ORE_EDGE.add(ItemRegistry.ORE_EDGE_PHY.get().getDefaultInstance());
        LIST_ORE_EDGE.add(ItemRegistry.ORE_EDGE_DEF.get().getDefaultInstance());
        LIST_ORE_EDGE.add(ItemRegistry.ORE_EDGE_ATK.get().getDefaultInstance());

    }

    private static final int RANK_MATERIAL_0 = 100;
    private static final int RANK_MATERIAL_1 = 40;
    private static final int RANK_MATERIAL_2 = 10;
    private static final int RANK_ORE_1 = 9;
    private static final int RANK_ORE_2 = 8;
    private static final int RANK_ORE_3 = 7;
    private static final int RANK_ORE_4 = 6;
    private static final int RANK_ORE_5 = 5;
    private static final int RANK_ORE_6 = 4;
    private static final int RANK_ORE_7 = 3;
    private static final int RANK_ORE_8 = 2;
    private static final int RANK_ORE_9 = 1;
    private static final int RANK_RANDOM_MAX = RANK_MATERIAL_0 + RANK_MATERIAL_1 + RANK_MATERIAL_2 +
            RANK_ORE_1 + RANK_ORE_2 + RANK_ORE_3 + RANK_ORE_4 + RANK_ORE_5 + RANK_ORE_6 + RANK_ORE_7 + RANK_ORE_8 + RANK_ORE_9;

    public static ItemStack random(int rank) {
        int drop = RANK_RANDOM_MAX;
        int r = new Random().nextInt(RANK_RANDOM_MAX) + 1;
        if (rank >= 9) {
            //9阶工具随机数+1
            r += 1;
        }
        if (r > (drop -= RANK_ORE_9) && rank >= 8) {
            return LIST_ORE_RANK_9.get(randomIndex(LIST_ORE_RANK_9.size())).copy();
        }
        if (r > (drop -= RANK_ORE_8) && rank >= 7) {
            return LIST_ORE_RANK_8.get(randomIndex(LIST_ORE_RANK_8.size())).copy();
        }
        if (r > (drop -= RANK_ORE_7) && rank >= 6) {
            return LIST_ORE_RANK_7.get(randomIndex(LIST_ORE_RANK_7.size())).copy();
        }
        if (r > (drop -= RANK_ORE_6) && rank >= 5) {
            return LIST_ORE_RANK_6.get(randomIndex(LIST_ORE_RANK_6.size())).copy();
        }
        if (r > (drop -= RANK_ORE_5) && rank >= 4) {
            return LIST_ORE_RANK_5.get(randomIndex(LIST_ORE_RANK_5.size())).copy();
        }
        if (r > (drop -= RANK_ORE_4) && rank >= 3) {
            return LIST_ORE_RANK_4.get(randomIndex(LIST_ORE_RANK_4.size())).copy();
        }
        if (r > (drop -= RANK_ORE_3) && rank >= 2) {
            return LIST_ORE_RANK_3.get(randomIndex(LIST_ORE_RANK_3.size())).copy();
        }
        if (r > (drop -= RANK_ORE_2) && rank >= 1) {
            return LIST_ORE_RANK_2.get(randomIndex(LIST_ORE_RANK_2.size())).copy();
        }
        if (r > (drop -= RANK_ORE_1)) {
            return LIST_ORE_RANK_1.get(randomIndex(LIST_ORE_RANK_1.size())).copy();
        }
        if (r > (drop -= RANK_MATERIAL_2)) {
            return LIST_ORE_EDGE.get(randomIndex(LIST_ORE_EDGE.size())).copy();
        }
        if (r > (drop - RANK_MATERIAL_1)) {
            return Items.RAW_GOLD.getDefaultInstance();
        }
        //RANK_MATERIAL_0
        return Items.RAW_IRON.getDefaultInstance();
    }

    private static int randomIndex(int size) {
        final int lastSize = size;
        int max = 0;
        for (int i = 1; i <= size; i++) {
            max += i;
        }
        int r = new Random().nextInt(max) + 1;
        while (size > 0) {
            if (r > (max -= size--)) {
                return size;
            }
        }
        return lastSize;
    }

}
