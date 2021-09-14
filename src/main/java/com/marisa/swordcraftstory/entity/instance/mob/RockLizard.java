package com.marisa.swordcraftstory.entity.instance.mob;

import com.marisa.swordcraftstory.entity.EntityStatus;
import com.marisa.swordcraftstory.entity.StoryMob;
import com.marisa.swordcraftstory.entity.ai.AttackGoal;
import com.marisa.swordcraftstory.entity.ai.RunAroundGoal;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

/**
 * 岩蜥蜴
 */

public class RockLizard extends AnimalEntity implements StoryMob, EntityStatus {

    public static final int HP = 65;
    public static final int ATK = 28;
    public static final int DEF = 8;
    public static final int AGL = -21;

    public static final double MOVE_SPEED = StoryMob.BASE_SPEED + (StoryMob.AGL_SPEED * AGL);

    private boolean isAttacking;

    public RockLizard(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.isAttacking = false;
    }

    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return CowEntity.registerAttributes()
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.25D)
                .createMutableAttribute(Attributes.MAX_HEALTH, HP)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, MOVE_SPEED);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new AttackGoal(this, MOVE_SPEED, false, 20, 130, this));
        this.goalSelector.addGoal(3, new RunAroundGoal(this, MOVE_SPEED, this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, StoryMob.LOOK_AT_DISTANCE));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    /**
     * 获取声音类型
     */
    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    /**
     * 是否禁用于和平模式
     */
    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public boolean isAttacking() {
        return this.isAttacking;
    }

    @Override
    public void startAttacking() {
        this.isAttacking = true;
    }

    @Override
    public void stopAttacking() {
        this.isAttacking = false;
    }
}
