package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * 武器锻造NBT工具类
 */

public class SmithNbtUtils {

    public static class NBT {
        public final static String QUALITY = "story_combat_quality";
        public final static String ATK = "story_combat_atk";
        public final static String ATK_S = "story_combat_atk_s";
        public final static String AGL = "story_combat_agl";
        public final static String DUR = "story_combat_dur";
        public final static String DUR_MAX = "story_combat_dur_max";
        public final static String TEC = "story_combat_tec";
        public final static String RANK = "story_combat_rank";
    }

    public static Quality getQuality(ItemStack stack) {
        if (stack.getTag() == null) {
            return Quality.UNKNOWN;
        }
        return Quality.getById(stack.getTag().getInt(NBT.QUALITY));
    }

    public static void setQuality(ItemStack stack, Quality quality) {
        stack.getOrCreateTag().put(NBT.QUALITY, IntTag.valueOf(quality.getId()));
        quality.getAttr(stack.getItem()).modifyBase(stack);
    }

    public static int getAtk(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(NBT.ATK);
    }

    public static void setAtk(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.ATK, IntTag.valueOf(amount));
    }

    public static double getAtkS(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getDouble(NBT.ATK_S);
    }

    public static void setAtkS(ItemStack stack, double amount) {
        stack.getOrCreateTag().put(NBT.ATK_S, DoubleTag.valueOf(amount));
    }

    public static int getAgl(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(NBT.AGL);
    }

    public static void setAgl(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.AGL, IntTag.valueOf(amount));
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

    public static int getTec(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return stack.getTag().getInt(NBT.TEC);
    }

    public static void incrTec(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt(NBT.TEC);
        if (v < 255) {
            tag.put(NBT.TEC, IntTag.valueOf(v + 1));
        }
    }

    public static void clearTec(ItemStack stack) {
        stack.getOrCreateTag().put(NBT.TEC, IntTag.valueOf(0));
    }

    public static int getRank(ItemStack stack) {
        if (stack.getTag() == null) {
            return 1;
        }
        return stack.getTag().getInt(NBT.RANK);
    }

    /**
     * 复制物品的附魔
     */
    public static void copyEnchantmentTag(ItemStack source, ItemStack target) {
        ListTag listEnchant = source.getEnchantmentTags();
        if (listEnchant.size() > 0) {
            CompoundTag tag = target.getOrCreateTag();
            if (!tag.contains("Enchantments", 9)) {
                tag.put("Enchantments", new ListTag());
            }
            for (Tag e : listEnchant) {
                tag.getList("Enchantments", 10).add(e);
            }
        }
    }

}
