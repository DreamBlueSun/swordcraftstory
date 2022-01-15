package com.marisa.swordcraftstory.smith.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

/**
 * 基本工具类
 */

public class StoryUtils {

    private static final String UNKNOWN = "未知";

    /**
     * 获取Item语言文件ID
     */
    public static String getItemLangId(Item item) {
        if (item == null) {
            return UNKNOWN;
        }
        ResourceLocation location = item.getRegistryName();
        if (location == null) {
            return UNKNOWN;
        }
        return "item." + location.getNamespace() + "." + location.getPath();
    }

    /**
     * 判断是否为近战武器
     */
    public static boolean isMeleeWeapon(Item item) {
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof PickaxeItem;
    }

    /**
     * 判断是否为远程武器
     */
    public static boolean isRangedWeapon(Item item) {
        return item instanceof BowItem || item instanceof CrossbowItem;
    }

    /**
     * 判断是否为武器
     */
    public static boolean isWeapon(Item item) {
        return isMeleeWeapon(item) || isRangedWeapon(item);
    }

    /**
     * 判断是否算作此模组Item
     */
    public static boolean isModItem(Item item) {
        return isMeleeWeapon(item) || isRangedWeapon(item) || item instanceof ArmorItem;
    }
}
