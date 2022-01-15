package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * 武器锻造NBT工具类
 */

public class SmithNbtUtils {

    public static class NBT {
        public final static String DUR = "story_combat_dur";
        public final static String DUR_MAX = "story_combat_dur_max";
    }

    public static int getDur(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(NBT.DUR);
    }

    public static void setDur(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.DUR, IntTag.valueOf(amount));
    }

    public static int getDurMax(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(NBT.DUR_MAX);
    }

    public static int getDurMaxAfterEffect(ItemStack stack) {
        int v = getDurMax(stack);
        int lvlUnBreaking = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlUnBreaking > 0) {
            v += lvlUnBreaking * 15;
        }
        return v;
    }

    public static void setDurMax(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.DUR_MAX, IntTag.valueOf(amount));
    }

}
