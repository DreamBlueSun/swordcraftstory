package com.marisa.swordcraftstory.item.combat;

import net.minecraft.item.ItemStack;

/**
 * 不同矿石制作武器基础数据接口
 */

public interface WeaponMake {

    ItemStack weaponMake(CombatType type);

    int rank();

    int atk(CombatType type);

    int def(CombatType type);

    int agl(CombatType type);

    int dur(CombatType type);
}
