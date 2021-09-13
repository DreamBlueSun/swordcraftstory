package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
import com.marisa.swordcraftstory.item.weapon.close.AbstractMeleeWeapon;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

/**
 * 抽象剑类武器
 */

public abstract class AbstractSwordWeapon extends AbstractMeleeWeapon {

    public static final WeaponType TYPE = WeaponType.SWORD;

    public AbstractSwordWeapon(final AbstractOre ore) {
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
    public WeaponType type() {
        return TYPE;
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        ItemStack instance = ItemRegistry.SWORD_MOULD.get().getDefaultInstance();
        CombatPropertiesUtils.setAtk(instance, getAtk(stack) / 7);
        CombatPropertiesUtils.setDef(instance, getDef(stack) / 7);
        int agl = getAgl(stack);
        CombatPropertiesUtils.setAgl(instance, agl > 6 ? agl / 7 : 0);
        int dur = (getMaxDamage(stack) + getDurMax(stack)) / 7;
        CombatPropertiesUtils.setDurMax(instance, dur);
        CombatPropertiesUtils.setDur(instance, dur);
        return instance;
    }
}
