package com.marisa.swordcraftstory.util.obj;

/**
 * @description: 伤害
 * @date: 2021/9/4 0004 2:25
 */

public class Damage {

    /**
     * 普通伤害
     */
    private float p;

    /**
     * 魔法伤害
     */
    private float m;

    /**
     * 真实伤害
     */
    private float r;

    public Damage() {
        this.p = 0.0F;
        this.m = 0.0F;
        this.r = 0.0F;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public void addP(float p) {
        this.p += p;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public void addM(float m) {
        this.m += m;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void addR(float r) {
        this.r += r;
    }

}