package com.marisa.swordcraftstory.util.obj;

/**
 * Mob属性
 */

public class MobAttr {

    private final String uuid;
    private final int lv;
    private final int maxHpAdd;
    private final int armorAdd;

    private float hp;

    public MobAttr(String uuid, int lv, int maxHpAdd, int armorAdd) {
        this.uuid = uuid;
        this.lv = lv;
        this.maxHpAdd = maxHpAdd;
        this.armorAdd = armorAdd;
        this.hp = 65535.0F;
    }

    public String getUuid() {
        return uuid;
    }

    public int getLv() {
        return lv;
    }

    public int getMaxHpAdd() {
        return maxHpAdd;
    }

    public int getArmorAdd() {
        return armorAdd;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
