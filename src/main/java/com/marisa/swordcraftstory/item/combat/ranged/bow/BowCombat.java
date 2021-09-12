package com.marisa.swordcraftstory.item.combat.ranged.bow;

import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.item.combat.CombatType;
import com.marisa.swordcraftstory.item.combat.ranged.RangedCombat;
import com.marisa.swordcraftstory.item.ore.OreItem;

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
}
