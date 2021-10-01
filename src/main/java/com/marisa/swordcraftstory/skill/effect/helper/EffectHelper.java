package com.marisa.swordcraftstory.skill.effect.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;

/**
 * 效果工具类
 */

public class EffectHelper {

    /**
     * 给予武器效果
     */
    public static void set(ItemStack stack, Effects effects) {
        //添加强化效果NBT
        stack.setTagInfo("story_combat_effect", IntNBT.valueOf(effects.getId()));
    }

    /**
     * 获取武器效果
     */
    public static Effects get(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_effect")) {
            return Effects.getById(tag.getInt("story_combat_effect"));
        }
        return null;
    }
}
