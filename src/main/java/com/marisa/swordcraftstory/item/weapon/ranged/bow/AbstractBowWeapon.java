package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
import com.marisa.swordcraftstory.item.weapon.ranged.AbstractRangedWeapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.item.ItemStack;

/**
 * 抽象弓类武器
 */

public abstract class AbstractBowWeapon extends AbstractRangedWeapon {

    public static final WeaponType TYPE = WeaponType.BOW;

    public AbstractBowWeapon(final AbstractOre ore) {
        super(ore.rank(), ore.atk(TYPE), ore.def(TYPE), ore.agl(TYPE), ore.dur(TYPE));
    }

    @Override
    public WeaponType type() {
        return TYPE;
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        ItemStack instance = ItemRegistry.BOW_MOULD.get().getDefaultInstance();
        CombatPropertiesUtils.setAtk(instance, getAtk(stack) / 5);
        CombatPropertiesUtils.setDef(instance, getDef(stack) / 5);
        int agl = getAgl(stack);
        CombatPropertiesUtils.setAgl(instance, agl > 4 ? agl / 5 : 0);
        int dur = (getMaxDamage(stack) + getDurMax(stack)) / 5;
        CombatPropertiesUtils.setDurMax(instance, dur);
        CombatPropertiesUtils.setDur(instance, dur);
        return instance;
    }
}
