package com.marisa.swordcraftstory.item.model;

import com.marisa.swordcraftstory.item.ItemRegistry;

/**
 * 武器模型枚举类
 */

public enum WeaponModels {

    UNKNOWN(0, null),

    //矿石
    IRON_ORE(1, (WeaponModel) ItemRegistry.IRON_ORE.get()),
    SLANDER_ORE(2, (WeaponModel) ItemRegistry.SLANDER_ORE.get()),
    HARD_ORE(3, (WeaponModel) ItemRegistry.HARD_ORE.get()),
    ARGENIR_ORE(4, (WeaponModel) ItemRegistry.ARGENIR_ORE.get()),
    BIG_ORE(5, (WeaponModel) ItemRegistry.BIG_ORE.get()),
    ELPHUS_ORE(6, (WeaponModel) ItemRegistry.ELPHUS_ORE.get()),
    WINGS_FOSSIL_ORE(7, (WeaponModel) ItemRegistry.WINGS_FOSSIL_ORE.get()),
    CUT_IRON_ORE(8, (WeaponModel) ItemRegistry.CUT_IRON_ORE.get()),
    BIG_IRON_ORE(9, (WeaponModel) ItemRegistry.BIG_IRON_ORE.get()),
    HEAVY_GOLD_ORE(10, (WeaponModel) ItemRegistry.HEAVY_GOLD_ORE.get()),
    REGIA_ORE(11, (WeaponModel) ItemRegistry.REGIA_ORE.get()),
    UKA_ORE(12, (WeaponModel) ItemRegistry.UKA_ORE.get()),
    PRETTY_ORE(13, (WeaponModel) ItemRegistry.PRETTY_ORE.get()),
    CARAPACE_FOSSIL_ORE(14, (WeaponModel) ItemRegistry.CARAPACE_FOSSIL_ORE.get()),


    //泰拉模型-剑
    DRAGON_SLAVE_SWORD(1001, (WeaponModel) ItemRegistry.DRAGON_SLAVE_SWORD.get()),
    UNLIMITED_EARTH_SWORD(1002, (WeaponModel) ItemRegistry.UNLIMITED_EARTH_SWORD.get()),
    ELEMENTS_MANA_SWORD(1003, (WeaponModel) ItemRegistry.ELEMENTS_MANA_SWORD.get()),
    TIME_UMBRELLA_SWORD(1004, (WeaponModel) ItemRegistry.TIME_UMBRELLA_SWORD.get()),

    //泰拉模型-弓
    CONSTANT_BLOWERS_SNOW_BOW(2001, (WeaponModel) ItemRegistry.CONSTANT_BLOWERS_SNOW_BOW.get());


    /**
     * 模型id
     */
    private int id;

    /**
     * 对应矿石
     */
    private WeaponModel model;

    WeaponModels(int id, WeaponModel model) {
        this.id = id;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public WeaponModel getModel() {
        return model;
    }

    public static WeaponModels getById(int id) {
        for (WeaponModels value : WeaponModels.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
