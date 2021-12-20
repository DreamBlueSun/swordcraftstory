package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * 武器属性工具类
 */

public class CombatPropertiesUtils {

    public static int getAtk(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_atk");
    }

    public static void setAtk(ItemStack stack, int amount) {
        stack.getOrCreateTag().put("story_combat_atk", IntTag.valueOf(amount));
    }

    public static int getDef(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_def");
    }

    public static void setDef(ItemStack stack, int amount) {
        stack.getOrCreateTag().put("story_combat_def", IntTag.valueOf(amount));
    }

    public static int getAgl(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_agl");
    }

    public static void setAgl(ItemStack stack, int amount) {
        stack.getOrCreateTag().put("story_combat_agl", IntTag.valueOf(amount));
    }

    public static int getDur(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_dur");
    }

    public static void setDur(ItemStack stack, int amount) {
        stack.getOrCreateTag().put("story_combat_dur", IntTag.valueOf(amount));
    }

    public static int getDurMax(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_dur_max");
    }

    public static void setDurMax(ItemStack stack, int amount) {
        stack.getOrCreateTag().put("story_combat_dur_max", IntTag.valueOf(amount));
    }

    public static int getDurMaxAfterEffect(ItemStack stack) {
        int v = getDurMax(stack);
        int lvlUnBreaking = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlUnBreaking > 0) {
            v += lvlUnBreaking * 15;
        }
        return v;
    }

    public static int getTec(ItemStack stack) {
        return stack.getOrCreateTag().getInt("story_combat_tec");
    }

    public static void incrTec(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_tec");
        if (v < Weapon.MAX_TEC) {
            tag.put("story_combat_tec", IntTag.valueOf(v + 1));
        }
    }

    public static void clearTec(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_tec", IntTag.valueOf(0));
    }

    /**
     * 复制物品的附魔
     */
    public static void copyEnchantmentTag(ItemStack source, ItemStack target) {
        ListTag listEnchant = source.getEnchantmentTags();
        if (listEnchant.size() > 0) {
            CompoundTag tag = target.getOrCreateTag();
            for (Tag inbt : listEnchant) {
                if (!tag.contains("Enchantments", 9)) {
                    tag.put("Enchantments", new ListTag());
                }
                ListTag listnbt = tag.getList("Enchantments", 10);
                listnbt.add(inbt);
            }
        }
    }

}
