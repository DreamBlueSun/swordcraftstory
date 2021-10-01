package com.marisa.swordcraftstory.item.model;

import net.minecraft.item.ItemStack;

/**
 * 模型幻化工具类
 */

public class ModelChangeHelper {

    /**
     * 获取模型名称
     */
    public static String getModelName(ItemStack stack) {
        int id = stack.getOrCreateTag().getInt("story_combat_model_change");
        WeaponModel model = WeaponModels.getById(id).getModel();
        if (model == null) {
            return "未幻化";
        }
        return model.getModelName(stack);
    }

}
