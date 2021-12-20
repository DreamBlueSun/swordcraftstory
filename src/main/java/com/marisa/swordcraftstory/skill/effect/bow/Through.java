package com.marisa.swordcraftstory.skill.effect.bow;

import com.marisa.swordcraftstory.skill.effect.helper.AbstractEffect;
import net.minecraft.world.entity.projectile.AbstractArrow;

/**
 * 贯穿
 */

public class Through extends AbstractEffect {

    @Override
    public void arrowEntityAdd(AbstractArrow arrowEntity) {
        arrowEntity.setPierceLevel((byte) 127);
    }
}
