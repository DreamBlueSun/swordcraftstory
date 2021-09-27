package com.marisa.swordcraftstory.entity.mob;

/**
 * 实体状态
 */

public interface EntityStatus {

    boolean isAttacking();

    void startAttacking();

    void stopAttacking();
}
