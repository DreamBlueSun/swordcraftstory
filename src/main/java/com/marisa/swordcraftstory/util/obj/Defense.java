package com.marisa.swordcraftstory.util.obj;

/**
 * @description: 防御力
 * @date: 2021/9/4 0004 3:37
 */

public class Defense {

    /**
     * 普通防御
     */
    private int p;

    /**
     * 魔法防御
     */
    private int m;

    public Defense() {
        this.p = 0;
        this.m = 0;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void addP(int p) {
        this.p += p;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void addM(int m) {
        this.m += m;
    }
}
