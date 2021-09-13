package com.marisa.swordcraftstory.item.combat.ranged.bow;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.item.combat.CombatType;
import com.marisa.swordcraftstory.item.combat.ranged.RangedCombat;
import com.marisa.swordcraftstory.item.ore.OreItem;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.item.ItemStack;

/**
 * @description: 弓类武器抽象类
 * @date: 2021/9/4 0004 6:36
 */

public abstract class BowCombat extends RangedCombat implements Combat {

    public static final CombatType TYPE = CombatType.BOW;

    public BowCombat(final OreItem ore) {
        super(ore.rank(), ore.atk(TYPE), ore.def(TYPE), ore.agl(TYPE), ore.dur(TYPE));
    }

    @Override
    public CombatType type() {
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
