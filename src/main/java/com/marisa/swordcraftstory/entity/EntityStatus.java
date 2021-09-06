package com.marisa.swordcraftstory.entity;

/**
 * @description:
 * @date: 2021/9/6 0006 1:37
 */

public interface EntityStatus {

    boolean isAttacking();

    void startAttacking();

    void stopAttacking();
}
