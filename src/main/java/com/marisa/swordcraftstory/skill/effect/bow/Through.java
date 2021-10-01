package com.marisa.swordcraftstory.skill.effect.bow;

import com.marisa.swordcraftstory.skill.effect.helper.AbstractEffect;
import net.minecraft.entity.projectile.AbstractArrowEntity;

/**
 * 贯穿
 */

public class Through extends AbstractEffect {

    @Override
    public void arrowEntityAdd(AbstractArrowEntity arrowEntity) {
        arrowEntity.setPierceLevel((byte) 127);
    }
}
