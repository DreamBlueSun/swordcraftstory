package com.marisa.swordcraftstory.item.model;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
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
        AbstractOre ore = WeaponModels.getById(id).getOre();
        if (ore == null) {
            return "未幻化";
        }
        switch (((Weapon)stack.getItem()).type()) {
            case SWORD:
                return ore.asSword().getName().getString();
            case BOW:
                return ore.asBow().getName().getString();
            case AXE:
                return ore.asAxe().getName().getString();
            default:
                return "未幻化";
        }
    }

}
