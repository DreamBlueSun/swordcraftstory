package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * 不同矿石制作武器基础数据接口
 */

public interface WeaponMake {

    ItemStack weaponMake(ItemStack mouldStack);

    /**
     * 作为剑类武器
     */
    Item asSword();

    /**
     * 作为弓类武器
     */
    Item asBow();

    /**
     * 作为斧类武器
     */
    Item asAxe();

    int rank();

    int atk(WeaponType type);

    int def(WeaponType type);

    int agl(WeaponType type);

    int dur(WeaponType type);
}
