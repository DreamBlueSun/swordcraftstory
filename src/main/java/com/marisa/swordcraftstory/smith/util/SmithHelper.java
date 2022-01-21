package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Random;

public class SmithHelper {

    /**
     * 左键攻击伤害
     */
    public static int getDamageAtk(ItemStack stack) {
        return Math.max(getItemAtk(stack.getItem()) + getSmithAtk(stack), 1);
    }

    /**
     * 物品原伤害
     */
    public static int getItemAtk(Item item) {
        if (StoryUtils.isRangedWeapon(item)) {
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
     * 锻冶增加的ATK
     */
    public static int getSmithAtk(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        int i = 0;
        //鉴定属性
        i += QualityHelper.getAtk(stack);
        //制作属性
        i += MakeHelper.getAtk(stack);
        //强化属性
        i += StrengthenHelper.getAtk(stack);
        //强刃属性
        i += EdgeHelper.getAtk(stack);
        return i;
    }

    /**
     * 锻冶增加的DEF
     */
    public static int getSmithDef(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        int i = 0;
        //鉴定属性
        i += QualityHelper.getDef(stack);
        //制作属性
        i += MakeHelper.getDef(stack);
        //强化属性
        i += StrengthenHelper.getDef(stack);
        //强刃属性
        i += EdgeHelper.getDef(stack);
        return Math.max(i, 0);
    }

    /**
     * 锻冶增加的PHY
     */
    public static int getSmithPhy(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        int i = 0;
        //鉴定属性
        i += QualityHelper.getPhy(stack);
        //制作属性
        i += MakeHelper.getPhy(stack);
        //强化属性
        i += StrengthenHelper.getPhy(stack);
        //强刃属性
        i += EdgeHelper.getPhy(stack);
        return Math.max(i, 0);
    }

    /**
     * 锻冶增加的AGL
     */
    public static int getSmithAgl(ItemStack stack) {
        if (stack.getTag() == null) return 0;
        int i = 0;
        //鉴定属性
        i += QualityHelper.getAgl(stack);
        //制作属性
        i += MakeHelper.getAgl(stack);
        //强化属性
        i += StrengthenHelper.getAgl(stack);
        //强刃属性
        i += EdgeHelper.getAgl(stack);
        return i;
    }

    private final static String DUR = "story_smith_dur";

    public static boolean isBroken(ItemStack stack) {
        return getDur(stack) == 0;
    }

    /**
     * 消耗物品的DUR
     */
    public static void minusDur(ItemStack stack) {
        minusDur(stack, 1);
    }

    public static void minusDur(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(DUR, IntTag.valueOf(Math.max(getDur(stack) - amount, 0)));
    }

    public static void plusDur(ItemStack stack, int amount) {
        stack.getOrCreateTag().put(DUR, IntTag.valueOf(Math.min(getDur(stack) + amount, getDurMax(stack))));
    }

    /**
     * 物品的当前DUR
     */
    public static int getDur(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.contains(DUR) ? Math.max(tag.getInt(DUR), 0) : DUR_BASE;
    }

    private final static int DUR_BASE = 50;

    /**
     * 物品的最大DUR
     */
    public static int getDurMax(ItemStack stack) {
        if (stack.getTag() == null) return 1;
        int i = 0;
        //鉴定属性
        i += QualityHelper.getDur(stack);
        //制作属性
        int make = MakeHelper.getDur(stack);
        i += make == 0 ? DUR_BASE : make;
        //强化属性
        i += StrengthenHelper.getDur(stack);
        //强刃属性
        i += EdgeHelper.getDur(stack);
        //附魔属性
        i += Math.max(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack), 0) * 5;
        return Math.max(i, 1);
    }

    /**
     * 最大暴击率
     */
    private static final int MAX_CRI = 1000;

    /**
     * 武器基础暴击率
     */
    private static final int BASE_CRI = 50;

    /**
     * 武器基础暴击率--斧头
     */
    private static final int BASE_CRI_AXE = 150;

    /**
     * 武器基础暴击率--镐子
     */
    private static final int BASE_CRI_PICKAXE = 100;

    public static boolean isCri(ItemStack stack) {
        return getCri(stack) > new Random().nextInt(MAX_CRI);
    }

    public static int getCri(ItemStack stack) {
        int cri;
        if (stack.getItem() instanceof AxeItem) {
            cri = BASE_CRI_AXE;
        } else if (stack.getItem() instanceof PickaxeItem) {
            cri = BASE_CRI_PICKAXE;
        } else {
            cri = BASE_CRI;
        }
        //鉴定属性
        cri += QualityHelper.getCri(stack);
        //TEC属性
        if (stack.getTag() != null) {
            cri += ((Math.min(EdgeHelper.getTec(stack), 250)) / 5);
        }
        return Math.min(cri, MAX_CRI);
    }

}
