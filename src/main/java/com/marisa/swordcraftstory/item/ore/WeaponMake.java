package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.item.weapon.WeaponType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 不同矿石制作武器基础数据接口
 */

public interface WeaponMake {

    ItemStack weaponMake(ItemStack mouldStack);

    /**
     * 作为剑类武器
     */
    Item asSword();

    int rank();

    int atk(WeaponType type);

    int def(WeaponType type);

    int agl(WeaponType type);

    int dur(WeaponType type);
}
