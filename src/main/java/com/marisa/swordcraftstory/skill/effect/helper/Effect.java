package com.marisa.swordcraftstory.skill.effect.helper;

import net.minecraft.world.entity.projectile.AbstractArrow;

/**
 * 效果接口
 */

public interface Effect {

    /**
     * 该效果赋予箭实体效果
     */
    void arrowEntityAdd(AbstractArrow arrowEntity);
}
