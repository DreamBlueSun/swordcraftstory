package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;

/**
 * 武器属性工具类
 */

public class CombatPropertiesUtils {

    public static int getAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        return v;
    }

    public static void setAtk(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(amount));
    }

    public static int getDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        return v;
    }

    public static void setDef(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(amount));
    }

    public static int getAgl(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_agl");
        }
        return v;
    }

    public static void setAgl(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(amount));
    }

    public static int getDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur");
        }
        return v;
    }

    public static void setDur(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_dur", IntNBT.valueOf(amount));
    }

    public static int getDurMax(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_max");
        }
        return v;
    }

    public static void setDurMax(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(amount));
    }

    public static int getDurMaxAfterEffect(ItemStack stack) {
        int v = getDurMax(stack);
        int lvlUnBreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlUnBreaking > 0) {
            v += lvlUnBreaking * 15;
        }
        return v;
    }

    public static int getTec(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_tec");
        }
        return v;
    }

    public static void incrTec(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_tec");
        }
        if (v < Weapon.MAX_TEC) {
            stack.setTagInfo("story_combat_tec", IntNBT.valueOf(v + 1));
        }
    }

    public static void clearTec(ItemStack stack) {
        stack.setTagInfo("story_combat_tec", IntNBT.valueOf(0));
    }

    /**
     * 复制物品的附魔
     */
    public static void copyEnchantmentTag(ItemStack source, ItemStack target) {
        ListNBT listEnchant = source.getEnchantmentTagList();
        if (listEnchant.size() > 0) {
            CompoundNBT tag = target.getOrCreateTag();
            for (INBT inbt : listEnchant) {
                if (!tag.contains("Enchantments", 9)) {
                    tag.put("Enchantments", new ListNBT());
                }
                ListNBT listnbt = tag.getList("Enchantments", 10);
                listnbt.add(inbt);
            }
        }
    }

}
