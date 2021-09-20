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
    }

    private static final int RANK_MATERIAL_0 = 300;
    private static final int RANK_MATERIAL_1 = 200;
    private static final int RANK_MATERIAL_2 = 100;
    private static final int RANK_ORE_1 = 100;
    private static final int RANK_ORE_2 = 80;
    private static final int RANK_ORE_3 = 70;
    private static final int RANK_RANDOM_MAX = RANK_MATERIAL_0 + RANK_MATERIAL_1 + RANK_MATERIAL_2 +
            RANK_ORE_1 + RANK_ORE_2 + RANK_ORE_3;

    public static ItemStack randomDropQuality(int playerLv) {
        int drop = RANK_RANDOM_MAX;
        int r = new Random().nextInt(RANK_RANDOM_MAX) + 1;
        if (r > (drop -= RANK_ORE_3) && playerLv >= 10) {
            return LIST_ORE_RANK_3.get(new Random().nextInt(LIST_ORE_RANK_3.size())).copy();
        }
        if (r > (drop -= RANK_ORE_2) && playerLv >= 5) {
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
