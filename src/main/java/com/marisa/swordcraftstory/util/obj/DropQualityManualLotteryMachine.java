package com.marisa.swordcraftstory.util.obj;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.save.ManualLotteryMachineSaveData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 掉落品质（手摇抽奖机）
 */

public class DropQualityManualLotteryMachine {

    private static final List<ItemStack> LIST_RANK_1;
    private static final List<ItemStack> LIST_RANK_2;
    private static final List<ItemStack> LIST_RANK_3;
    private static final List<ItemStack> LIST_RANK_4;
    private static final List<ItemStack> LIST_RANK_5;

    private static ItemStack RANK_1;
    private static ItemStack RANK_2;
    private static ItemStack RANK_3;
    private static ItemStack RANK_4;
    private static ItemStack RANK_5;

    static {
        //强化
        LIST_RANK_1 = new ArrayList<>();
        LIST_RANK_1.add(ItemRegistry.LEGEND_IRON_BUNCH.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.HEART_GLASSES.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.MOMENTUM_HEAD_BAND.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.POWER_CERTIFICATE.get().getDefaultInstance());
        LIST_RANK_1.add(ItemRegistry.TALENT_CERTIFICATE.get().getDefaultInstance());

        //幻化
        LIST_RANK_2 = new ArrayList<>();
        //--剑
        LIST_RANK_2.add(ItemRegistry.DRAGON_SLAVE_SWORD.get().getDefaultInstance());
        LIST_RANK_2.add(ItemRegistry.UNLIMITED_EARTH_SWORD.get().getDefaultInstance());
        LIST_RANK_2.add(ItemRegistry.ELEMENTS_MANA_SWORD.get().getDefaultInstance());
        LIST_RANK_2.add(ItemRegistry.TIME_UMBRELLA_SWORD.get().getDefaultInstance());
        //--弓
        LIST_RANK_2.add(ItemRegistry.CONSTANT_BLOWERS_SNOW_BOW.get().getDefaultInstance());
        //--斧
        LIST_RANK_2.add(ItemRegistry.ELEMENTS_MANA_AXE.get().getDefaultInstance());

        //头
        LIST_RANK_3 = new ArrayList<>();
        LIST_RANK_3.add(Items.SKELETON_SKULL.getDefaultInstance());
        LIST_RANK_3.add(Items.WITHER_SKELETON_SKULL.getDefaultInstance());
        LIST_RANK_3.add(Items.ZOMBIE_HEAD.getDefaultInstance());
        LIST_RANK_3.add(Items.CREEPER_HEAD.getDefaultInstance());
        LIST_RANK_3.add(Items.DRAGON_HEAD.getDefaultInstance());

        //修理道具
        LIST_RANK_4 = new ArrayList<>();
        LIST_RANK_4.add(ItemRegistry.SHARP_STONE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.KNIFE_STONE.get().getDefaultInstance());
        LIST_RANK_4.add(ItemRegistry.HARD_STONE.get().getDefaultInstance());

        //回复道具
        LIST_RANK_5 = new ArrayList<>();
        ItemStack stack_5_1 = ItemRegistry.WOUNDPLAST.get().getDefaultInstance();
        stack_5_1.setCount(16);
        LIST_RANK_5.add(stack_5_1);
        ItemStack stack_5_2 = ItemRegistry.JI_JA_MEDICINE.get().getDefaultInstance();
        stack_5_2.setCount(8);
        LIST_RANK_5.add(stack_5_2);
        ItemStack stack_5_3 = ItemRegistry.JU_YU_MEDICINE.get().getDefaultInstance();
        stack_5_3.setCount(4);
        LIST_RANK_5.add(stack_5_3);

        shuffle();
    }

    private static final int DROP_RANK_1 = 2;
    private static final int DROP_RANK_2 = 3;
    private static final int DROP_RANK_3 = 4;
    private static final int DROP_RANK_4 = 5;
    private static final int DROP_RANK_5 = 6;
    private static final int RANK_RANDOM_MAX = DROP_RANK_1 + DROP_RANK_2 + DROP_RANK_3 + DROP_RANK_4 + DROP_RANK_5;

    public static ItemStack getRank1() {
        return RANK_1.copy();
    }

    public static ItemStack getRank2() {
        return RANK_2.copy();
    }

    public static ItemStack getRank3() {
        return RANK_3.copy();
    }

    public static ItemStack getRank4() {
        return RANK_4.copy();
    }

    public static ItemStack getRank5() {
        return RANK_5.copy();
    }

    public static void load(ManualLotteryMachineSaveData saveData) {
        RANK_1 = saveData.getRANK_1().copy();
        RANK_2 = saveData.getRANK_2().copy();
        RANK_3 = saveData.getRANK_3().copy();
        RANK_4 = saveData.getRANK_4().copy();
        RANK_5 = saveData.getRANK_5().copy();
    }

    public static void shuffle() {
        RANK_1 = LIST_RANK_1.get(new Random().nextInt(LIST_RANK_1.size())).copy();
        RANK_2 = LIST_RANK_2.get(new Random().nextInt(LIST_RANK_2.size())).copy();
        RANK_3 = LIST_RANK_3.get(new Random().nextInt(LIST_RANK_3.size())).copy();
        RANK_4 = LIST_RANK_4.get(new Random().nextInt(LIST_RANK_4.size())).copy();
        RANK_5 = LIST_RANK_5.get(new Random().nextInt(LIST_RANK_5.size())).copy();
    }

    public static ItemStack drop() {
        int drop = RANK_RANDOM_MAX;
        int r = new Random().nextInt(RANK_RANDOM_MAX) + 1;
        if (r > (drop -= DROP_RANK_1)) {
            return RANK_1.copy();
        }
        if (r > (drop -= DROP_RANK_2)) {
            return RANK_2.copy();
        }
        if (r > (drop -= DROP_RANK_3)) {
            return RANK_3.copy();
        }
        if (r > (drop - DROP_RANK_4)) {
            return RANK_4.copy();
        }
        return RANK_5.copy();
    }

}
