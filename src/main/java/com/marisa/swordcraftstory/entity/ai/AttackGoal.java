package com.marisa.swordcraftstory.entity.ai;

import com.marisa.swordcraftstory.entity.EntityStatus;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

/**
 * 攻击冷却逻辑
 */

public class AttackGoal extends MeleeAttackGoal {

    private final int maxCooldown;
    private int cooldown;
    private EntityStatus entityStatus;

    public AttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory, int cooldownIn, int maxCooldownIn, EntityStatus entityStatus) {
        super(creature, speedIn, useLongMemory);
        this.maxCooldown = maxCooldownIn;
        this.cooldown = cooldownIn;
        this.entityStatus = entityStatus;
    }

    @Override
    public boolean shouldExecute() {
        if (cooldown > 0) {
            cooldown--;
        } else {
            return super.shouldExecute();
        }
        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        entityStatus.startAttacking();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        cooldown = maxCooldown;
        entityStatus.stopAttacking();
    }
}
