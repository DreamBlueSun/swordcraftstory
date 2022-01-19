package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.smith.IStrengthen;
import net.minecraft.world.item.ItemStack;

/**
 * 强化枚举
 */

public enum EStrengthen {

    UNKNOWN(0, StoryUtils.getItemLangId(null), new IStrengthen() {
        @Override
        public ItemStack doStrengthen(ItemStack stack) {
            return stack;
        }

        @Override
        public int strengthenId() {
            return 0;
        }

        @Override
        public int strengthenAtk() {
            return 0;
        }

        @Override
        public int strengthenDef() {
            return 0;
        }

        @Override
        public int strengthenPhy() {
            return 0;
        }

        @Override
        public int strengthenAgl() {
            return 0;
        }

        @Override
        public int strengthenDur() {
            return 0;
        }
    }),
    IRON_ORE(1, StoryUtils.getItemLangId(ItemRegistry.IRON_ORE.get()), (IStrengthen) ItemRegistry.IRON_ORE.get()),
    SLANDER_ORE(2, StoryUtils.getItemLangId(ItemRegistry.SLANDER_ORE.get()), (IStrengthen) ItemRegistry.SLANDER_ORE.get()),
    HARD_ORE(3, StoryUtils.getItemLangId(ItemRegistry.HARD_ORE.get()), (IStrengthen) ItemRegistry.HARD_ORE.get()),
    ARGENIR_ORE(4, StoryUtils.getItemLangId(ItemRegistry.ARGENIR_ORE.get()), (IStrengthen) ItemRegistry.ARGENIR_ORE.get()),
    BIG_ORE(5, StoryUtils.getItemLangId(ItemRegistry.BIG_ORE.get()), (IStrengthen) ItemRegistry.BIG_ORE.get()),
    ELPHUS_ORE(6, StoryUtils.getItemLangId(ItemRegistry.ELPHUS_ORE.get()), (IStrengthen) ItemRegistry.ELPHUS_ORE.get()),
    WINGS_FOSSIL_ORE(7, StoryUtils.getItemLangId(ItemRegistry.WINGS_FOSSIL_ORE.get()), (IStrengthen) ItemRegistry.WINGS_FOSSIL_ORE.get()),
    CUT_IRON_ORE(8, StoryUtils.getItemLangId(ItemRegistry.CUT_IRON_ORE.get()), (IStrengthen) ItemRegistry.CUT_IRON_ORE.get()),
    BIG_IRON_ORE(9, StoryUtils.getItemLangId(ItemRegistry.BIG_IRON_ORE.get()), (IStrengthen) ItemRegistry.BIG_IRON_ORE.get()),
    HEAVY_GOLD_ORE(10, StoryUtils.getItemLangId(ItemRegistry.HEAVY_GOLD_ORE.get()), (IStrengthen) ItemRegistry.HEAVY_GOLD_ORE.get()),
    REGIA_ORE(11, StoryUtils.getItemLangId(ItemRegistry.REGIA_ORE.get()), (IStrengthen) ItemRegistry.REGIA_ORE.get()),
    UKA_ORE(12, StoryUtils.getItemLangId(ItemRegistry.UKA_ORE.get()), (IStrengthen) ItemRegistry.UKA_ORE.get()),
    PRETTY_ORE(13, StoryUtils.getItemLangId(ItemRegistry.PRETTY_ORE.get()), (IStrengthen) ItemRegistry.PRETTY_ORE.get()),
    CARAPACE_FOSSIL_ORE(14, StoryUtils.getItemLangId(ItemRegistry.CARAPACE_FOSSIL_ORE.get()), (IStrengthen) ItemRegistry.CARAPACE_FOSSIL_ORE.get()),
    FANTASY_ORE(16, StoryUtils.getItemLangId(ItemRegistry.FANTASY_ORE.get()), (IStrengthen) ItemRegistry.FANTASY_ORE.get()),
    GALIA_ORE(20, StoryUtils.getItemLangId(ItemRegistry.GALIA_ORE.get()), (IStrengthen) ItemRegistry.GALIA_ORE.get()),
    CUISINE_ORE(21, StoryUtils.getItemLangId(ItemRegistry.CUISINE_ORE.get()), (IStrengthen) ItemRegistry.CUISINE_ORE.get()),
    DOUBLE_SNAKE_ORE(24, StoryUtils.getItemLangId(ItemRegistry.DOUBLE_SNAKE_ORE.get()), (IStrengthen) ItemRegistry.DOUBLE_SNAKE_ORE.get()),
    SPRINT_ORE(25, StoryUtils.getItemLangId(ItemRegistry.SPRINT_ORE.get()), (IStrengthen) ItemRegistry.SPRINT_ORE.get()),
    XCEL_ORE(26, StoryUtils.getItemLangId(ItemRegistry.XCEL_ORE.get()), (IStrengthen) ItemRegistry.XCEL_ORE.get()),
    LEIJI_STOWE_ORE(28, StoryUtils.getItemLangId(ItemRegistry.LEIJI_STOWE_ORE.get()), (IStrengthen) ItemRegistry.LEIJI_STOWE_ORE.get()),
    DEW_FIREFLY_ORE(30, StoryUtils.getItemLangId(ItemRegistry.DEW_FIREFLY_ORE.get()), (IStrengthen) ItemRegistry.DEW_FIREFLY_ORE.get()),
    STEEL_TEETH_ORE(33, StoryUtils.getItemLangId(ItemRegistry.STEEL_TEETH_ORE.get()), (IStrengthen) ItemRegistry.STEEL_TEETH_ORE.get()),
    CATS_PAW_ORE(36, StoryUtils.getItemLangId(ItemRegistry.CATS_PAW_ORE.get()), (IStrengthen) ItemRegistry.CATS_PAW_ORE.get()),
    LUMINOSITY_ORE(40, StoryUtils.getItemLangId(ItemRegistry.LUMINOSITY_ORE.get()), (IStrengthen) ItemRegistry.LUMINOSITY_ORE.get());

    private final int id;
    private final String name;
    private final IStrengthen strengthen;

    EStrengthen(int id, String name, IStrengthen strengthen) {
        this.id = id;
        this.name = name;
        this.strengthen = strengthen;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IStrengthen getStrengthen() {
        return strengthen;
    }

    public static EStrengthen getById(int id) {
        for (EStrengthen value : EStrengthen.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }

}
