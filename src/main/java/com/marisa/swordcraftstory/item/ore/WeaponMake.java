package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.item.weapon.WeaponType;
import net.minecraft.item.ItemStack;

/**
 * 不同矿石制作武器基础数据接口
 */

public interface WeaponMake {

    ItemStack weaponMake(WeaponType type);

    int rank();

    int atk(WeaponType type);

    int def(WeaponType type);

    int agl(WeaponType type);

    int dur(WeaponType type);
}
