package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.IStrengthen;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.item.ItemStack;

/**
 * 强化装备工具类
 */

public class StrengthenHelper {

    private final static String STRENGTHEN = "story_smith_strengthen";

    public static void setStrengthen(ItemStack stack, int[] ints) {
        stack.getOrCreateTag().put(STRENGTHEN, new IntArrayTag(ints));
    }

    public static int[] getStrengthenIds(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] ints = stack.getTag().getIntArray(STRENGTHEN);
        return ints.length > 0 ? ints : null;
    }

    public static IStrengthen[] getStrengthens(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] ints = stack.getTag().getIntArray(STRENGTHEN);
        if (ints.length > 0) {
            IStrengthen[] strengthens = new IStrengthen[ints.length];
            for (int i = 0; i < ints.length; i++) {
                strengthens[i] = EStrengthen.getById(ints[i]).getStrengthen();
            }
            return strengthens;
        }
        return null;
    }

}
