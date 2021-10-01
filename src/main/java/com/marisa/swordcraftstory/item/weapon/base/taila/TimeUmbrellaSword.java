package com.marisa.swordcraftstory.item.weapon.base.taila;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.IntNBT;

/**
 * 光阴流时伞
 */

public class TimeUmbrellaSword extends SwordItem implements WeaponModel {

    public TimeUmbrellaSword() {
        super(new IItemTier() {
            @Override
            public int getMaxUses() {
                return 2021;
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
                return Ingredient.fromItems(ItemRegistry.DRAGON_SLAVE_SWORD.get());
            }
        }, 10, -2.4F, new Properties().group(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    public boolean build(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case SWORD:
                stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.TIME_UMBRELLA_SWORD.getId()));
                return true;
            case BOW:
            case AXE:
            default:
                return false;
        }
    }

    @Override
    public String getModelName(ItemStack stack) {
        return this.getName().getString();
    }
}
