package com.marisa.swordcraftstory.item.combat.close.sword;

import com.marisa.swordcraftstory.item.combat.CombatType;
import com.marisa.swordcraftstory.item.combat.close.CloseCombat;
import com.marisa.swordcraftstory.item.ore.OreItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

/**
 * @description: 剑类武器抽象类
 * @date: 2021/9/4 0004 6:18
 */

public abstract class SwordCombat extends CloseCombat {

    public static final CombatType TYPE = CombatType.SWORD;

    public SwordCombat(final OreItem ore) {
        super(ore.rank(), ore.atk(TYPE), ore.def(TYPE), ore.agl(TYPE), new IItemTier() {
            @Override
            public int getMaxUses() {
                return ore.dur(TYPE);
            }

            @Override
            public float getEfficiency() {
                return 10.0F;
            }

            @Override
            public float getAttackDamage() {
                return -1.0F;
            }

            @Override
            public int getHarvestLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 26;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return null;
            }
        });
    }

    @Override
    public CombatType type() {
        return TYPE;
    }
}
