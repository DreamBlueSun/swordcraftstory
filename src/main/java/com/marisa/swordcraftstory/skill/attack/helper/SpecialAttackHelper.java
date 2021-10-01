package com.marisa.swordcraftstory.skill.attack.helper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;

/**
 * 特殊攻击工具类
 */

public class SpecialAttackHelper {

    /**
     * 给予武器特殊攻击
     */
    public static void set(ItemStack stack, SpecialAttacks specialAttacks) {
        stack.setTagInfo("story_combat_special_attack", IntNBT.valueOf(specialAttacks.getId()));
    }

    /**
     * 获取武器特殊攻击
     */
    public static SpecialAttacks get(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_special_attack")) {
            return SpecialAttacks.getById(tag.getInt("story_combat_special_attack"));
        }
        return null;
    }

    /**
     * 右键蓄力比重
     */
    public static float useSp(ItemStack stack, PlayerEntity entity) {
        return (stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
    }

}
