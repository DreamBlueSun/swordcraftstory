package com.marisa.swordcraftstory.skill.weapon.helper;

/**
 * 抽象武技
 */

public class AbstractWeaponSkill {

    private final int id;

    private final String name;

    private final int cost;

    private final int learnPoint;

    public AbstractWeaponSkill(int id, String name, int cost, int learnPoint) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.learnPoint = learnPoint;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getLearnPoint() {
        return learnPoint;
    }

    public String getStringId() {
        return String.valueOf(this.id);
    }
}
