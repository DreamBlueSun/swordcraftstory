package com.marisa.swordcraftstory.skill.weapon.helper;

import com.marisa.swordcraftstory.skill.weapon.HpUpSmall;

/**
 *
 */

public enum WeaponSkills {
    HP_UP_SMALL(1, new HpUpSmall());

    private int id;

    private AbstractWeaponSkill skill;

    WeaponSkills(int id, AbstractWeaponSkill skill) {
        this.id = id;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public AbstractWeaponSkill getSkill() {
        return skill;
    }
}
