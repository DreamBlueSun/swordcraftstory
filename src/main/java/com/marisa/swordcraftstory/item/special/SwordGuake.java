package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.combat.CloseCombat;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

/**
 * @description: 初心者匕首-挂科专属皮肤
 * @date: 2021/9/1 0001 22:27
 */

public class SwordGuake extends CloseCombat {

    public SwordGuake() {
        super(1, 7, 3, 0, 0, new IItemTier() {
            @Override
            public int getMaxUses() {
                return 64;
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
                return Ingredient.fromItems(ItemRegistry.SWORD_GUAKE.get());
            }
        });
    }

}
