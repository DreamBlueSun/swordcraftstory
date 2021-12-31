package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.*;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
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
        public final static String DEF = "story_combat_def";
        public final static String PHY = "story_combat_phy";
        public final static String AGL = "story_combat_agl";
        public final static String DUR = "story_combat_dur";
        public final static String DUR_MAX = "story_combat_dur_max";
        public final static String TEC = "story_combat_tec";
        public final static String RANK = "story_combat_rank";
        public final static String RANK_ATTR = "story_combat_rank_attr";
        public final static String RANK_ATTR_ARMOR = "story_combat_rank_attr_armor";
    }

    public static boolean isMeleeWeapon(Item item) {
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof PickaxeItem;
    }

    public static boolean isRangedWeapon(Item item) {
        return item instanceof BowItem || item instanceof CrossbowItem;
    }

    public static boolean isWeapon(Item item) {
        return isMeleeWeapon(item) || isRangedWeapon(item);
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
        int[] rankAttr = getRankAttr(stack);
        if (rankAttr != null) {
            return stack.getTag().getInt(NBT.ATK) + rankAttr[0];
        }
        return stack.getTag().getInt(NBT.ATK);
    }

    public static void setAtk(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.ATK, IntTag.valueOf(amount));
    }

    public static int getDef(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        int[] rankAttr = getRankAttrArmor(stack);
        if (rankAttr != null) {
            return stack.getTag().getInt(NBT.DEF) + rankAttr[0];
        }
        return stack.getTag().getInt(NBT.DEF);
    }

    public static void setDef(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.DEF, IntTag.valueOf(amount));
    }

    public static int getPhy(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        int[] rankAttr = getRankAttrArmor(stack);
        if (rankAttr != null) {
            return stack.getTag().getInt(NBT.PHY) + rankAttr[1];
        }
        return stack.getTag().getInt(NBT.PHY);
    }

    public static void setPhy(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(NBT.PHY, IntTag.valueOf(amount));
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
        int[] rankAttr = getRankAttr(stack);
        if (rankAttr != null) {
            return stack.getTag().getInt(NBT.AGL) + rankAttr[1];
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

    public static void setRank(ItemStack stack, int rank) {
        stack.getOrCreateTag().put(NBT.RANK, IntTag.valueOf(rank));
    }

    public static int getRank(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        return Mth.clamp(stack.getTag().getInt(NBT.RANK), 0, 9);
    }

    public static void setRankAttr(ItemStack stack, int[] attr) {
        stack.getOrCreateTag().put(NBT.RANK_ATTR, new IntArrayTag(attr));
    }

    public static int[] getRankAttr(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] attr = stack.getTag().getIntArray(NBT.RANK_ATTR);
        return attr.length > 0 ? attr : null;
    }

    public static void setRankAttrArmor(ItemStack stack, int[] attr) {
        stack.getOrCreateTag().put(NBT.RANK_ATTR_ARMOR, new IntArrayTag(attr));
    }

    public static int[] getRankAttrArmor(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] attr = stack.getTag().getIntArray(NBT.RANK_ATTR_ARMOR);
        return attr.length > 0 ? attr : null;
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
