package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;

/**
 * 强刃装备工具类
 */

public class EdgeHelper {

    private final static String TEC = "story_smith_edge_tec";

    public static void incrTec(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt(TEC);
        if (v < 255) {
            tag.put(TEC, IntTag.valueOf(v + 1));
        }
    }

    public static int getTec(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(TEC);
    }

    public static void clearTec(ItemStack stack) {
        stack.getOrCreateTag().put(TEC, IntTag.valueOf(0));
    }

    private final static String ATK = "story_smith_edge_atk";

    public static void setAtk(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(ATK, IntTag.valueOf(amount));
    }

    public static int getAtk(ItemStack stack) {
        return stack.getOrCreateTag().getInt(ATK);
    }

    private final static String DEF = "story_smith_edge_def";

    public static void setDef(ItemStack stack, int value) {
        stack.getOrCreateTag().put(DEF, IntTag.valueOf(value));
    }

    public static int getDef(ItemStack stack) {
        return stack.getOrCreateTag().getInt(DEF);
    }

    private final static String PHY = "story_smith_edge_phy";

    public static void setPhy(ItemStack stack, int value) {
        stack.getOrCreateTag().put(PHY, IntTag.valueOf(value));
    }

    public static int getPhy(ItemStack stack) {
        return stack.getOrCreateTag().getInt(PHY);
    }

    private final static String AGL = "story_smith_edge_agl";

    public static void setAgl(ItemStack stack, int value) {
        stack.getOrCreateTag().put(AGL, IntTag.valueOf(value));
    }

    public static int getAgl(ItemStack stack) {
        return stack.getOrCreateTag().getInt(AGL);
    }

}
