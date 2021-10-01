package com.marisa.swordcraftstory.item.model;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;

/**
 * 武器模型枚举类
 */

public enum WeaponModels {

    UNKNOWN(0, null),

    //矿石
    IRON_ORE(1, (AbstractOre) ItemRegistry.IRON_ORE.get()),
    SLANDER_ORE(2, (AbstractOre) ItemRegistry.SLANDER_ORE.get()),
    HARD_ORE(3, (AbstractOre) ItemRegistry.HARD_ORE.get()),
    ARGENIR_ORE(4, (AbstractOre) ItemRegistry.ARGENIR_ORE.get()),
    BIG_ORE(5, (AbstractOre) ItemRegistry.BIG_ORE.get()),
    ELPHUS_ORE(6, (AbstractOre) ItemRegistry.ELPHUS_ORE.get()),
    WINGS_FOSSIL_ORE(7, (AbstractOre) ItemRegistry.WINGS_FOSSIL_ORE.get()),
    CUT_IRON_ORE(8, (AbstractOre) ItemRegistry.CUT_IRON_ORE.get()),
    BIG_IRON_ORE(9, (AbstractOre) ItemRegistry.BIG_IRON_ORE.get()),
    HEAVY_GOLD_ORE(10, (AbstractOre) ItemRegistry.HEAVY_GOLD_ORE.get()),
    REGIA_ORE(11, (AbstractOre) ItemRegistry.REGIA_ORE.get()),
    UKA_ORE(12, (AbstractOre) ItemRegistry.UKA_ORE.get()),
    PRETTY_ORE(13, (AbstractOre) ItemRegistry.PRETTY_ORE.get());

    /**
     * 模型id
     */
    private int id;

    /**
     * 对应矿石
     */
    private AbstractOre ore;

    WeaponModels(int id, AbstractOre ore) {
        this.id = id;
        this.ore = ore;
    }

    public int getId() {
        return id;
    }

    public AbstractOre getOre() {
        return ore;
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
