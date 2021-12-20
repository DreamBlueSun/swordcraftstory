package com.marisa.swordcraftstory.item.intensify.helper;

/**
 * 强化属性
 */

public class IntensifyAttr {

    final private int id;
    final private String show;
    final private int atk;
    final private int def;
    final private int agl;
    final private int dur;

    public IntensifyAttr(int id, String show, int atk, int def, int agl, int dur) {
        this.id = id;
        this.show = show;
        this.atk = atk;
        this.def = def;
        this.agl = agl;
        this.dur = dur;
    }

    public int getId() {
        return this.id;
    }

    public String getShow() {
        return show;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getDef() {
        return this.def;
    }

    public int getAgl() {
        return this.agl;
    }

    public int getDur() {
        return this.dur;
    }
}
