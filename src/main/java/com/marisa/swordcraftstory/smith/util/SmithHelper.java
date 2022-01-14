package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.IStrengthen;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

public class SmithHelper {

    /**
     * 物品原伤害
     */
    public static int getItemAtk(Item item) {
        if (SmithNbtUtils.isRangedWeapon(item)) {
            return 0;
        } else if (item instanceof SwordItem swordItem) {
            return (int) swordItem.getDamage() + 1;
        } else if (item instanceof DiggerItem diggerItem) {
            return (int) diggerItem.getAttackDamage() + 1;
        } else {
            return 1;
        }
    }

    /**
     * 锻冶增加的伤害
     */
    public static int getSmithAtk(ItemStack stack) {
        if (stack.getTag() == null) {
            return 0;
        }
        int i = 0;
        //鉴定属性
        int[] qualityAttr = SmithNbtUtils.QualityUtils.getQualityAttr(stack);
        if (qualityAttr != null) {
            i += qualityAttr[0];
        }
        //制作属性
        int[] rankAttr = SmithNbtUtils.getRankAttr(stack);
        if (rankAttr != null) {
            i += rankAttr[0];
        }
        //强化属性
        IStrengthen[] strengthens = StrengthenHelper.getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenAtk();
            }
        }
        //强刃属性
        i += Edge.getAtk(stack);
        return i;
    }

    /**
     * 左键攻击伤害
     */
    public static int getDamageAtk(ItemStack stack) {
        return getItemAtk(stack.getItem()) + getSmithAtk(stack);
    }

    public static class Edge {

        public final static String EDGE_ATK = "story_combat_atk";

        public static void setAtk(ItemStack stack, int amount) {
            stack.getOrCreateTag().put(EDGE_ATK, IntTag.valueOf(amount));
        }

        public static int getAtk(ItemStack stack) {
            return stack.getOrCreateTag().getInt(EDGE_ATK);
        }

    }

    public static class Collapse {

        private final static String COLLAPSE_ATK = "story_collapse_atk";

        public static void setAtk(ItemStack stack, int value) {
            stack.getOrCreateTag().put(COLLAPSE_ATK, IntTag.valueOf(value));
        }

        public static int getAtk(ItemStack stack) {
            return stack.getOrCreateTag().getInt(COLLAPSE_ATK);
        }

        private final static String COLLAPSE_DEF = "story_collapse_def";

        public static void setDef(ItemStack stack, int value) {
            stack.getOrCreateTag().put(COLLAPSE_DEF, IntTag.valueOf(value));
        }

        public static int getDef(ItemStack stack) {
            return stack.getOrCreateTag().getInt(COLLAPSE_DEF);
        }

        private final static String COLLAPSE_PHY = "story_collapse_phy";

        public static void setPhy(ItemStack stack, int value) {
            stack.getOrCreateTag().put(COLLAPSE_PHY, IntTag.valueOf(value));
        }

        public static int getPhy(ItemStack stack) {
            return stack.getOrCreateTag().getInt(COLLAPSE_PHY);
        }

        private final static String COLLAPSE_AGL = "story_collapse_agl";

        public static void setAgl(ItemStack stack, int value) {
            stack.getOrCreateTag().put(COLLAPSE_AGL, IntTag.valueOf(value));
        }

        public static int getAgl(ItemStack stack) {
            return stack.getOrCreateTag().getInt(COLLAPSE_AGL);
        }

    }

}
