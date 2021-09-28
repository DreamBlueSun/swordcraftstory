package com.marisa.swordcraftstory.entity.projectile.instance;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


/**
 *
 */

public class AirCutterProjectileEntity extends AbstractArrowEntity {

    public AirCutterProjectileEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

//    private int ticksInGround;
//
//    public AirCutterProjectileEntity(EntityType<? extends DamagingProjectileEntity> p_i50173_1_, World p_i50173_2_) {
//        super(p_i50173_1_, p_i50173_2_);
//        this.ticksInGround = 0;
//    }
//
//
//    @Override
//    public void tick() {
//        super.tick();
//        ++this.ticksInGround;
//        if (this.ticksInGround >= 20) {
//            this.remove();
//        }
//    }
//
//    @Override
//    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
//        super.shoot(x, y, z, velocity, inaccuracy);
//        this.ticksInGround = 0;
//    }
//
//    @Override
//    public boolean canBeCollidedWith() {
//        return false;
//    }
//
//    @Override
//    protected boolean isFireballFiery() {
//        return false;
//    }
//
//    @Override
//    protected IParticleData getParticle() {
//        return ParticleTypes.CRIT;
//    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
