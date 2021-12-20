package com.marisa.swordcraftstory.skill.attack.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * 特殊攻击工具类
 */

public class SpecialAttackHelper {

    /**
     * 给予武器特殊攻击
     */
    public static void set(ItemStack stack, SpecialAttacks specialAttacks) {
        stack.getOrCreateTag().put("story_combat_special_attack", IntTag.valueOf(specialAttacks.getId()));
    }

    /**
     * 获取武器特殊攻击
     */
    public static SpecialAttacks get(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_special_attack")) {
            return SpecialAttacks.getById(tag.getInt("story_combat_special_attack"));
        }
        return null;
    }

    /**
     * 右键蓄力比重
     */
    public static float useSp(ItemStack stack, Player entity) {
        return (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
    }

}
