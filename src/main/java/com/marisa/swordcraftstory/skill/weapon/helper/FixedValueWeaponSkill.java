package com.marisa.swordcraftstory.skill.weapon.helper;

/**
 * 抽象武技
 */

public class FixedValueWeaponSkill extends AbstractWeaponSkill {

    public int amount;

    public FixedValueWeaponSkill(int id, String name, int cost, int learnPoint, int amount) {
        super(id, name, cost, learnPoint);
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
