package com.marisa.swordcraftstory.item.combat;

import com.marisa.swordcraftstory.group.GroupRegistry;
import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;

/**
 * @description:
 * @date: 2021/9/1 0001 22:27
 */

public class SwordNovice extends SwordItem {

    private static IItemTier tier = new IItemTier() {
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
            return Ingredient.fromItems(ItemRegistry.SWORD_NOVICE.get());
        }
    };

    public SwordNovice() {
        super(tier, 7, -2.2F, new Item.Properties().group(GroupRegistry.COMBAT_GROUP));
    }
}
