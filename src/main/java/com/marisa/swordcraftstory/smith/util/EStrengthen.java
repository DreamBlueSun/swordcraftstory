package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.smith.IStrengthen;
import net.minecraft.world.item.ItemStack;

/**
 * 强化枚举
 */

public enum EStrengthen {

    UNKNOWN(0, StoryUtils.getItemName(null), new IStrengthen() {
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
        public int strengthenAgl() {
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
    }),
    IRON_ORE(1, StoryUtils.getItemName(ItemRegistry.IRON_ORE.get()), (IStrengthen) ItemRegistry.IRON_ORE.get()),
    SLANDER_ORE(2, StoryUtils.getItemName(ItemRegistry.SLANDER_ORE.get()), (IStrengthen) ItemRegistry.SLANDER_ORE.get()),
    HARD_ORE(3, StoryUtils.getItemName(ItemRegistry.HARD_ORE.get()), (IStrengthen) ItemRegistry.HARD_ORE.get()),
    ARGENIR_ORE(4, StoryUtils.getItemName(ItemRegistry.ARGENIR_ORE.get()), (IStrengthen) ItemRegistry.ARGENIR_ORE.get()),
    BIG_ORE(5, StoryUtils.getItemName(ItemRegistry.BIG_ORE.get()), (IStrengthen) ItemRegistry.BIG_ORE.get()),
    ELPHUS_ORE(6, StoryUtils.getItemName(ItemRegistry.ELPHUS_ORE.get()), (IStrengthen) ItemRegistry.ELPHUS_ORE.get()),
    WINGS_FOSSIL_ORE(7, StoryUtils.getItemName(ItemRegistry.WINGS_FOSSIL_ORE.get()), (IStrengthen) ItemRegistry.WINGS_FOSSIL_ORE.get()),
    CUT_IRON_ORE(8, StoryUtils.getItemName(ItemRegistry.CUT_IRON_ORE.get()), (IStrengthen) ItemRegistry.CUT_IRON_ORE.get()),
    BIG_IRON_ORE(9, StoryUtils.getItemName(ItemRegistry.BIG_IRON_ORE.get()), (IStrengthen) ItemRegistry.BIG_IRON_ORE.get()),
    HEAVY_GOLD_ORE(10, StoryUtils.getItemName(ItemRegistry.HEAVY_GOLD_ORE.get()), (IStrengthen) ItemRegistry.HEAVY_GOLD_ORE.get()),
    REGIA_ORE(11, StoryUtils.getItemName(ItemRegistry.REGIA_ORE.get()), (IStrengthen) ItemRegistry.REGIA_ORE.get()),
    UKA_ORE(12, StoryUtils.getItemName(ItemRegistry.UKA_ORE.get()), (IStrengthen) ItemRegistry.UKA_ORE.get()),
    PRETTY_ORE(13, StoryUtils.getItemName(ItemRegistry.PRETTY_ORE.get()), (IStrengthen) ItemRegistry.PRETTY_ORE.get()),
    CARAPACE_FOSSIL_ORE(14, StoryUtils.getItemName(ItemRegistry.CARAPACE_FOSSIL_ORE.get()), (IStrengthen) ItemRegistry.CARAPACE_FOSSIL_ORE.get()),
    FANTASY_ORE(16, StoryUtils.getItemName(ItemRegistry.FANTASY_ORE.get()), (IStrengthen) ItemRegistry.FANTASY_ORE.get()),
    GALIA_ORE(20, StoryUtils.getItemName(ItemRegistry.GALIA_ORE.get()), (IStrengthen) ItemRegistry.GALIA_ORE.get()),
    CUISINE_ORE(21, StoryUtils.getItemName(ItemRegistry.CUISINE_ORE.get()), (IStrengthen) ItemRegistry.CUISINE_ORE.get()),
    DOUBLE_SNAKE_ORE(24, StoryUtils.getItemName(ItemRegistry.DOUBLE_SNAKE_ORE.get()), (IStrengthen) ItemRegistry.DOUBLE_SNAKE_ORE.get()),
    SPRINT_ORE(25, StoryUtils.getItemName(ItemRegistry.SPRINT_ORE.get()), (IStrengthen) ItemRegistry.SPRINT_ORE.get()),
    XCEL_ORE(26, StoryUtils.getItemName(ItemRegistry.XCEL_ORE.get()), (IStrengthen) ItemRegistry.XCEL_ORE.get()),
    LEIJI_STOWE_ORE(28, StoryUtils.getItemName(ItemRegistry.LEIJI_STOWE_ORE.get()), (IStrengthen) ItemRegistry.LEIJI_STOWE_ORE.get()),
    DEW_FIREFLY_ORE(30, StoryUtils.getItemName(ItemRegistry.DEW_FIREFLY_ORE.get()), (IStrengthen) ItemRegistry.DEW_FIREFLY_ORE.get()),
    STEEL_TEETH_ORE(33, StoryUtils.getItemName(ItemRegistry.STEEL_TEETH_ORE.get()), (IStrengthen) ItemRegistry.STEEL_TEETH_ORE.get()),
    CATS_PAW_ORE(36, StoryUtils.getItemName(ItemRegistry.CATS_PAW_ORE.get()), (IStrengthen) ItemRegistry.CATS_PAW_ORE.get()),
    LUMINOSITY_ORE(40, StoryUtils.getItemName(ItemRegistry.LUMINOSITY_ORE.get()), (IStrengthen) ItemRegistry.LUMINOSITY_ORE.get());

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
