package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.IStrengthen;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * 强化装备工具类
 */

public class StrengthenHelper {

    public static int getStrengthenRank(Player player) {
        int strengthenLv = 0;
        int lv = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
        if (lv >= 18) {
            strengthenLv = IStrengthen.MAX_SIZE;
        } else if (lv >= 9) {
            strengthenLv = 2;
        } else if (lv >= 2) {
            strengthenLv = 1;
        }
        return strengthenLv;
    }

    private final static String ID = "story_smith_strengthen";

    public static void setStrengthen(ItemStack stack, int[] ints) {
        stack.getOrCreateTag().put(ID, new IntArrayTag(ints));
    }

    public static int[] getStrengthenIds(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] ints = stack.getTag().getIntArray(ID);
        return ints.length > 0 ? ints : null;
    }

    public static IStrengthen[] getStrengthens(ItemStack stack) {
        if (stack.getTag() == null) {
            return null;
        }
        int[] ints = stack.getTag().getIntArray(ID);
        if (ints.length > 0) {
            IStrengthen[] strengthens = new IStrengthen[ints.length];
            for (int i = 0; i < ints.length; i++) {
                strengthens[i] = EStrengthen.getById(ints[i]).getStrengthen();
            }
            return strengthens;
        }
        return null;
    }

    public static int getAtk(ItemStack stack) {
        int i = 0;
        IStrengthen[] strengthens = getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenAtk();
            }
        }
        return i;
    }

    public static int getDef(ItemStack stack) {
        int i = 0;
        IStrengthen[] strengthens = getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenDef();
            }
        }
        return i;
    }

    public static int getPhy(ItemStack stack) {
        int i = 0;
        IStrengthen[] strengthens = getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenPhy();
            }
        }
        return i;
    }

    public static int getAgl(ItemStack stack) {
        int i = 0;
        IStrengthen[] strengthens = getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenAgl();
            }
        }
        return i;
    }

    public static int getDur(ItemStack stack) {
        int i = 0;
        IStrengthen[] strengthens = getStrengthens(stack);
        if (strengthens != null) {
            for (IStrengthen strengthen : strengthens) {
                i += strengthen.strengthenDur();
            }
        }
        return i;
    }

}
