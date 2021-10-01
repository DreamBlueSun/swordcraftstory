package com.marisa.swordcraftstory.skill.effect.helper;

import net.minecraft.entity.projectile.AbstractArrowEntity;

/**
 * 效果接口
 */

public interface Effect {

    /**
     * 该效果赋予箭实体效果
     */
    void arrowEntityAdd(AbstractArrowEntity arrowEntity);
}
