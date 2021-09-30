package com.marisa.swordcraftstory.entity.mob.ai;

import com.marisa.swordcraftstory.entity.mob.EntityStatus;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.PanicGoal;

/**
 * 是否执行游荡逻辑
 */

public class RunAroundGoal extends PanicGoal {

    private EntityStatus entityStatus;

    public RunAroundGoal(final CreatureEntity creatureIn, final double speedIn, EntityStatus entityStatus) {
        super(creatureIn, speedIn);
        this.entityStatus = entityStatus;
    }

    @Override
    public boolean shouldExecute() {
        if (entityStatus.isAttacking()) {
            return false;
        }
        return findRandomPosition();
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (entityStatus.isAttacking()) {
            return false;
        }
        return super.shouldContinueExecuting();
    }
}
