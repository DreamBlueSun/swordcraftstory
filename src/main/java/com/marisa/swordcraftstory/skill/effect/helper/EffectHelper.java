package com.marisa.swordcraftstory.skill.effect.helper;

import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;

/**
 * 效果工具类
 */

public class EffectHelper {

    /**
     * 给予武器效果
     */
    public static void set(ItemStack stack, Effects effects) {
        //添加强化效果NBT
        stack.getOrCreateTag().put("story_combat_effect", IntTag.valueOf(effects.getId()));
    }

    /**
     * 获取武器效果
     */
    public static Effects get(ItemStack stack) {
        return Effects.getById(stack.getOrCreateTag().getInt("story_combat_effect"));
    }
}
