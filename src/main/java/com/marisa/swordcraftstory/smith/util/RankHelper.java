package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class RankHelper {

    private final static String RANK = "story_combat_rank";
    private final static String RANK_ATTR_WEAPON = "story_combat_rank_attr";
    private final static String RANK_ATTR_ARMOR = "story_combat_rank_attr_armor";

    public static void setRank(ItemStack stack, int rank) {
        stack.getOrCreateTag().put(RANK, IntTag.valueOf(rank));
    }

    public static int getRank(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return Mth.clamp(stack.getTag().getInt(RANK), 0, 9);
    }

    public static void setRankAttr(ItemStack stack, int[] attr) {
        stack.getOrCreateTag().put(RANK_ATTR_WEAPON, new IntArrayTag(attr));
    }

    public static int[] getRankAttr(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] attr = stack.getTag().getIntArray(RANK_ATTR_WEAPON);
        return attr.length > 0 ? attr : null;
    }

    public static void setRankAttrArmor(ItemStack stack, int[] attr) {
        stack.getOrCreateTag().put(RANK_ATTR_ARMOR, new IntArrayTag(attr));
    }

    public static int[] getRankAttrArmor(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] attr = stack.getTag().getIntArray(RANK_ATTR_ARMOR);
        return attr.length > 0 ? attr : null;
    }

}
