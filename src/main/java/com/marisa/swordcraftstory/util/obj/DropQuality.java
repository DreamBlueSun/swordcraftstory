package com.marisa.swordcraftstory.util.obj;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 掉落品质
 */

public class DropQuality {

    private static final List<ItemStack> LIST_ORE_RANK_1;
    private static final List<ItemStack> LIST_ORE_RANK_2;
    private static final List<ItemStack> LIST_ORE_RANK_3;
    private static final List<ItemStack> LIST_ORE_RANK_4;
    private static final List<ItemStack> LIST_ORE_RANK_5;
    private static final List<ItemStack> LIST_ORE_RANK_6;
    private static final List<ItemStack> LIST_ORE_RANK_7;
    private static final List<ItemStack> LIST_ORE_RANK_8;
    private static final List<ItemStack> LIST_ORE_RANK_9;

    static {
        LIST_ORE_RANK_1 = new ArrayList<>();
        LIST_ORE_RANK_1.add(ItemRegistry.IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_1.add(ItemRegistry.SLANDER_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_1.add(ItemRegistry.HARD_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2 = new ArrayList<>();
        LIST_ORE_RANK_2.add(ItemRegistry.ARGENIR_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.BIG_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.ELPHUS_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_2.add(ItemRegistry.WINGS_FOSSIL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3 = new ArrayList<>();
        LIST_ORE_RANK_3.add(ItemRegistry.CUT_IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.BIG_IRON_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.HEAVY_GOLD_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.REGIA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.UKA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_3.add(ItemRegistry.PRETTY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_4 = new ArrayList<>();
        LIST_ORE_RANK_4.add(ItemRegistry.CARAPACE_FOSSIL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_4.add(ItemRegistry.FANTASY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_5 = new ArrayList<>();
        LIST_ORE_RANK_5.add(ItemRegistry.GALIA_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_5.add(ItemRegistry.CUISINE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_6 = new ArrayList<>();
        LIST_ORE_RANK_6.add(ItemRegistry.DOUBLE_SNAKE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_6.add(ItemRegistry.SPRINT_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_7 = new ArrayList<>();
        LIST_ORE_RANK_7.add(ItemRegistry.XCEL_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_7.add(ItemRegistry.LEIJI_STOWE_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_8 = new ArrayList<>();
        LIST_ORE_RANK_8.add(ItemRegistry.DEW_FIREFLY_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_8.add(ItemRegistry.STEEL_TEETH_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_9 = new ArrayList<>();
        LIST_ORE_RANK_9.add(ItemRegistry.CATS_PAW_ORE.get().getDefaultInstance());
        LIST_ORE_RANK_9.add(ItemRegistry.LUMINOSITY_ORE.get().getDefaultInstance());
    }

    private static final int RANK_MATERIAL_0 = 30;
    private static final int RANK_MATERIAL_1 = 20;
    private static final int RANK_MATERIAL_2 = 5;
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

    public static ItemStack randomDropQuality(int playerLv) {
        int drop = RANK_RANDOM_MAX;
        int r = new Random().nextInt(RANK_RANDOM_MAX) + 1;
        if (r > (drop -= RANK_ORE_9) && playerLv >= 24) {
            return LIST_ORE_RANK_9.get(new Random().nextInt(LIST_ORE_RANK_9.size())).copy();
        }
        if (r > (drop -= RANK_ORE_8) && playerLv >= 21) {
            return LIST_ORE_RANK_8.get(new Random().nextInt(LIST_ORE_RANK_8.size())).copy();
        }
        if (r > (drop -= RANK_ORE_7) && playerLv >= 18) {
            return LIST_ORE_RANK_7.get(new Random().nextInt(LIST_ORE_RANK_7.size())).copy();
        }
        if (r > (drop -= RANK_ORE_6) && playerLv >= 15) {
            return LIST_ORE_RANK_6.get(new Random().nextInt(LIST_ORE_RANK_6.size())).copy();
        }
        if (r > (drop -= RANK_ORE_5) && playerLv >= 12) {
            return LIST_ORE_RANK_5.get(new Random().nextInt(LIST_ORE_RANK_5.size())).copy();
        }
        if (r > (drop -= RANK_ORE_4) && playerLv >= 9) {
            return LIST_ORE_RANK_4.get(new Random().nextInt(LIST_ORE_RANK_4.size())).copy();
        }
        if (r > (drop -= RANK_ORE_3) && playerLv >= 6) {
            return LIST_ORE_RANK_3.get(new Random().nextInt(LIST_ORE_RANK_3.size())).copy();
        }
        if (r > (drop -= RANK_ORE_2) && playerLv >= 3) {
            return LIST_ORE_RANK_2.get(new Random().nextInt(LIST_ORE_RANK_2.size())).copy();
        }
        if (r > (drop -= RANK_ORE_1)) {
            return LIST_ORE_RANK_1.get(new Random().nextInt(LIST_ORE_RANK_1.size())).copy();
        }
        if (r > (drop -= RANK_MATERIAL_2)) {
            return ItemRegistry.SHARP_STONE.get().getDefaultInstance();
        }
        if (r > (drop - RANK_MATERIAL_1)) {
            return ItemRegistry.DOPATIC_STONE.get().getDefaultInstance();
        }
        //RANK_MATERIAL_0
        return ItemRegistry.OMEGA_STONE.get().getDefaultInstance();
    }

}
