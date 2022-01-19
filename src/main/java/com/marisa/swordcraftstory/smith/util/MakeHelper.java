package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.IMake;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;

public class MakeHelper {

    private final static String FIELD = "story_smith_make";

    public static void setMake(ItemStack stack, int id) {
        stack.getOrCreateTag().put(FIELD, IntTag.valueOf(id));
    }

    public static IMake getMake(ItemStack stack) {
        if (stack.getTag() == null) return null;
        EMake eMake = EMake.getById(stack.getTag().getInt(FIELD));
        return eMake == EMake.UNKNOWN ? null : eMake.getMake();
    }

    public static int getMakeRank(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        EMake eMake = EMake.getById(stack.getTag().getInt(FIELD));
        return eMake == EMake.UNKNOWN ? 0 : eMake.getMake().makeRank();
    }

    public static int getAtk(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        IMake make = getMake(stack);
        if (make == null) return 0;
        return make.makeAtk(stack.getItem());
    }

    public static int getDef(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        IMake make = getMake(stack);
        if (make == null) return 0;
        return make.makeDef(stack.getItem());
    }

    public static int getPhy(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        IMake make = getMake(stack);
        if (make == null) return 0;
        return make.makePhy(stack.getItem());
    }

    public static int getAgl(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        IMake make = getMake(stack);
        if (make == null) return 0;
        return make.makeAgl(stack.getItem());
    }

    public static int getDur(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        IMake make = getMake(stack);
        if (make == null) return 0;
        return make.makeDur(stack.getItem());
    }

}
