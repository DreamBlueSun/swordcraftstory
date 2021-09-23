package com.marisa.swordcraftstory.item.weapon;

/**
 * 武器类型枚举
 */

public enum WeaponType {

    SWORD("剑", 0),
    BOW("弓", 1),
    AXE("斧", 2);

    private String name;

    private int type;

    WeaponType(String name, int type) {
        this.name = name;
        this.type = type;
    }

}
