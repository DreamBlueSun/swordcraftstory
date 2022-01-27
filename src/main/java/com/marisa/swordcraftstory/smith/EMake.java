package com.marisa.swordcraftstory.smith;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.util.StoryUtils;

/**
 * 矿石枚举
 */

public enum EMake {

    UNKNOWN(0, StoryUtils.getItemLangId(null), null),
    IRON_ORE(1, StoryUtils.getItemLangId(ItemRegistry.IRON_ORE.get()), (IMake) ItemRegistry.IRON_ORE.get()),
    SLANDER_ORE(2, StoryUtils.getItemLangId(ItemRegistry.SLANDER_ORE.get()), (IMake) ItemRegistry.SLANDER_ORE.get()),
    HARD_ORE(3, StoryUtils.getItemLangId(ItemRegistry.HARD_ORE.get()), (IMake) ItemRegistry.HARD_ORE.get()),
    ARGENIR_ORE(4, StoryUtils.getItemLangId(ItemRegistry.ARGENIR_ORE.get()), (IMake) ItemRegistry.ARGENIR_ORE.get()),
    BIG_ORE(5, StoryUtils.getItemLangId(ItemRegistry.BIG_ORE.get()), (IMake) ItemRegistry.BIG_ORE.get()),
    ELPHUS_ORE(6, StoryUtils.getItemLangId(ItemRegistry.ELPHUS_ORE.get()), (IMake) ItemRegistry.ELPHUS_ORE.get()),
    WINGS_FOSSIL_ORE(7, StoryUtils.getItemLangId(ItemRegistry.WINGS_FOSSIL_ORE.get()), (IMake) ItemRegistry.WINGS_FOSSIL_ORE.get()),
    CUT_IRON_ORE(8, StoryUtils.getItemLangId(ItemRegistry.CUT_IRON_ORE.get()), (IMake) ItemRegistry.CUT_IRON_ORE.get()),
    BIG_IRON_ORE(9, StoryUtils.getItemLangId(ItemRegistry.BIG_IRON_ORE.get()), (IMake) ItemRegistry.BIG_IRON_ORE.get()),
    HEAVY_GOLD_ORE(10, StoryUtils.getItemLangId(ItemRegistry.HEAVY_GOLD_ORE.get()), (IMake) ItemRegistry.HEAVY_GOLD_ORE.get()),
    REGIA_ORE(11, StoryUtils.getItemLangId(ItemRegistry.REGIA_ORE.get()), (IMake) ItemRegistry.REGIA_ORE.get()),
    UKA_ORE(12, StoryUtils.getItemLangId(ItemRegistry.UKA_ORE.get()), (IMake) ItemRegistry.UKA_ORE.get()),
    PRETTY_ORE(13, StoryUtils.getItemLangId(ItemRegistry.PRETTY_ORE.get()), (IMake) ItemRegistry.PRETTY_ORE.get()),
    CARAPACE_FOSSIL_ORE(14, StoryUtils.getItemLangId(ItemRegistry.CARAPACE_FOSSIL_ORE.get()), (IMake) ItemRegistry.CARAPACE_FOSSIL_ORE.get()),
    FANTASY_ORE(16, StoryUtils.getItemLangId(ItemRegistry.FANTASY_ORE.get()), (IMake) ItemRegistry.FANTASY_ORE.get()),
    GALIA_ORE(20, StoryUtils.getItemLangId(ItemRegistry.GALIA_ORE.get()), (IMake) ItemRegistry.GALIA_ORE.get()),
    CUISINE_ORE(21, StoryUtils.getItemLangId(ItemRegistry.CUISINE_ORE.get()), (IMake) ItemRegistry.CUISINE_ORE.get()),
    DOUBLE_SNAKE_ORE(24, StoryUtils.getItemLangId(ItemRegistry.DOUBLE_SNAKE_ORE.get()), (IMake) ItemRegistry.DOUBLE_SNAKE_ORE.get()),
    SPRINT_ORE(25, StoryUtils.getItemLangId(ItemRegistry.SPRINT_ORE.get()), (IMake) ItemRegistry.SPRINT_ORE.get()),
    XCEL_ORE(26, StoryUtils.getItemLangId(ItemRegistry.XCEL_ORE.get()), (IMake) ItemRegistry.XCEL_ORE.get()),
    LEIJI_STOWE_ORE(28, StoryUtils.getItemLangId(ItemRegistry.LEIJI_STOWE_ORE.get()), (IMake) ItemRegistry.LEIJI_STOWE_ORE.get()),
    DEW_FIREFLY_ORE(30, StoryUtils.getItemLangId(ItemRegistry.DEW_FIREFLY_ORE.get()), (IMake) ItemRegistry.DEW_FIREFLY_ORE.get()),
    STEEL_TEETH_ORE(33, StoryUtils.getItemLangId(ItemRegistry.STEEL_TEETH_ORE.get()), (IMake) ItemRegistry.STEEL_TEETH_ORE.get()),
    CATS_PAW_ORE(36, StoryUtils.getItemLangId(ItemRegistry.CATS_PAW_ORE.get()), (IMake) ItemRegistry.CATS_PAW_ORE.get()),
    LUMINOSITY_ORE(40, StoryUtils.getItemLangId(ItemRegistry.LUMINOSITY_ORE.get()), (IMake) ItemRegistry.LUMINOSITY_ORE.get());

    private final int id;
    private final String name;
    private final IMake make;

    EMake(int id, String name, IMake make) {
        this.id = id;
        this.name = name;
        this.make = make;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IMake getMake() {
        return make;
    }

    public static EMake getById(int id) {
        for (EMake value : EMake.values()) {
            if (value.id == id) return value;
        }
        return UNKNOWN;
    }

}
