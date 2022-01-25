package com.marisa.swordcraftstory.block.craft.helper;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.helper.OreDropQuality;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 掉落品质
 */

public class ManualLotteryMachineDropQuality {

    private static final List<ItemStack> LIST_RANK_1;
    private static final List<ItemStack> LIST_RANK_2;
    private static final List<ItemStack> LIST_RANK_3;
    private static final List<ItemStack> LIST_RANK_4;
    private static final List<ItemStack> LIST_RANK_5;
    

    static {
        LIST_RANK_1 = new ArrayList<>();
        LIST_RANK_1.add(ItemRegistry.HARD_STONE.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.KNIFE_STONE.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.SHARP_STONE.get().getDefaultInstance());
        LIST_RANK_2 = new ArrayList<>();
        LIST_RANK_2.add(ItemRegistry.JU_YU_MEDICINE.get().getDefaultInstance());
        LIST_RANK_2.add(ItemRegistry.JI_JA_MEDICINE.get().getDefaultInstance());
        LIST_RANK_2.add(ItemRegistry.WOUNDPLAST.get().getDefaultInstance());
        LIST_RANK_3 = new ArrayList<>();
        LIST_RANK_3.add(ItemRegistry.ORE_EDGE_AGL.get().getDefaultInstance());
        LIST_RANK_3.add(ItemRegistry.ORE_EDGE_PHY.get().getDefaultInstance());
        LIST_RANK_3.add(ItemRegistry.ORE_EDGE_DEF.get().getDefaultInstance());
        LIST_RANK_3.add(ItemRegistry.ORE_EDGE_DUR.get().getDefaultInstance());
        LIST_RANK_3.add(ItemRegistry.ORE_EDGE_ATK.get().getDefaultInstance());
        LIST_RANK_4 = new ArrayList<>();
        LIST_RANK_4.add(ItemRegistry.JU_YU_MEDICINE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.HARD_STONE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.JI_JA_MEDICINE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.KNIFE_STONE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.WOUNDPLAST.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.SHARP_STONE.get().getDefaultInstance());
        LIST_RANK_5 = new ArrayList<>();
        LIST_RANK_5.add(ItemRegistry.LUCK_TICKET_MAX.get().getDefaultInstance());
        LIST_RANK_5.add(ItemRegistry.LUCK_TICKET.get().getDefaultInstance());

    }

    private static final int RANK_ORE_1 = 6;
    private static final int RANK_ORE_2 = 5;
    private static final int RANK_ORE_3 = 4;
    private static final int RANK_ORE_4 = 3;
    private static final int RANK_ORE_5 = 2;
    private static final int RANK_RANDOM_MAX = RANK_ORE_1 + RANK_ORE_2 + RANK_ORE_3 + RANK_ORE_4 + RANK_ORE_5;

    public static ItemStack random() {
        int drop = RANK_RANDOM_MAX;
        int r = new Random().nextInt(RANK_RANDOM_MAX) + 1;
        if (r > (drop -= RANK_ORE_5)) {
            return LIST_RANK_5.get(OreDropQuality.randomIndex(LIST_RANK_5.size())).copy();
        }
        if (r > (drop -= RANK_ORE_4)) {
            ItemStack stack = LIST_RANK_4.get(OreDropQuality.randomIndex(LIST_RANK_4.size())).copy();
            stack.setCount(2);
            return stack;
        }
        if (r > (drop -= RANK_ORE_3)) {
            return LIST_RANK_3.get(OreDropQuality.randomIndex(LIST_RANK_3.size())).copy();
        }
        if (r > (drop - RANK_ORE_2)) {
            return LIST_RANK_2.get(OreDropQuality.randomIndex(LIST_RANK_2.size())).copy();
        }
        return LIST_RANK_1.get(OreDropQuality.randomIndex(LIST_RANK_1.size())).copy();
    }

}
