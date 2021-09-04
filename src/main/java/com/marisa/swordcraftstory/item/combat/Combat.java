package com.marisa.swordcraftstory.item.combat;

/**
 * @description:
 * @date: 2021/9/4 0004 6:57
 */

public interface Combat {

    /**
     * 武器最大磨合度
     */
    int MAX_TEC = 255;

    int getRank();

    int getAtk();

    int getDef();

    int getPhy();

    int getAgl();

    int getDur();

    int getTec();
}
