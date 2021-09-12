package com.marisa.swordcraftstory.item.combat;

import net.minecraft.item.ItemStack;

/**
 * @description: 不同矿石制作武器基础数据接口
 * @date: 2021/9/12 0012 17:09
 */

public interface WeaponInfo {

    ItemStack weaponMake(CombatType type);

    int rank();
    int atk(CombatType type);
    int def(CombatType type);
    int agl(CombatType type);
    int dur(CombatType type);
}
