package com.marisa.swordcraftstory.smith.util;

import net.minecraft.world.item.*;

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
        if (stack.getTag() == null) {
            return 0;
        }
        int i = 0;
        //鉴定属性
        i += QualityHelper.getAtk(stack);
        //制作属性
        int[] rankAttr = RankHelper.getRankAttr(stack);
        if (rankAttr != null) {
            i += rankAttr[0];
        }
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
        if (stack.getTag() == null) {
            return 0;
        }
        int i = 0;
        //鉴定属性
        i += QualityHelper.getDef(stack);
        //制作属性
        int[] rankAttr = RankHelper.getRankAttrArmor(stack);
        if (rankAttr != null) {
            i += rankAttr[0];
        }
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
        if (stack.getTag() == null) {
            return 0;
        }
        int i = 0;
        //鉴定属性
        i += QualityHelper.getPhy(stack);
        //制作属性
        int[] rankAttr = RankHelper.getRankAttrArmor(stack);
        if (rankAttr != null) {
            i += rankAttr[1];
        }
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
        if (stack.getTag() == null) {
            return 0;
        }
        int i = 0;
        //鉴定属性
        i += QualityHelper.getAgl(stack);
        //制作属性
        int[] rankAttr = RankHelper.getRankAttr(stack);
        if (rankAttr != null) {
            i += rankAttr[1];
        }
        //强化属性
        i += StrengthenHelper.getAgl(stack);
        //强刃属性
        i += EdgeHelper.getAgl(stack);
        return i;
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
