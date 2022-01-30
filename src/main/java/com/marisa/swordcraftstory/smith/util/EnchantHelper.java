package com.marisa.swordcraftstory.smith.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * 附魔工具类
 */

public class EnchantHelper {

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

    /**
     * 移除物品的附魔
     */
    public static void remEnchantmentTag(ItemStack target) {
        CompoundTag tag = target.getOrCreateTag();
        if (tag.contains("Enchantments", 9)) {
            tag.remove("Enchantments");
        }
    }

    /**
     *
     */
    public static int getItemEnchantmentLevelByMobType(MobType mobType, ItemStack stack) {
        int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (mobType == MobType.UNDEAD) {
            return Math.max(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SMITE, stack), sharpness);
        } else if (mobType == MobType.ARTHROPOD) {
            return Math.max(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, stack), sharpness);
        } else if (mobType == MobType.WATER) {
            return Math.max(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.IMPALING, stack), sharpness);
        } else {
            return sharpness;
        }
    }

}
