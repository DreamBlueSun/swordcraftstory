package com.marisa.swordcraftstory.item.combat;

/**
 * @description: 武器枚举
 * @date: 2021/9/12 0012 17:02
 */

public enum CombatType {

    SWORD("剑", 0),
    BOW("弓", 1);

    private String name;

    private int type;

    CombatType(String name, int type) {
        this.name = name;
        this.type = type;
    }

}
