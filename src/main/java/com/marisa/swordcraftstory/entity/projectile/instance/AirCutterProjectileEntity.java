package com.marisa.swordcraftstory.entity.projectile.instance;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


/**
 *
 */

public class AirCutterProjectileEntity extends ArrowEntity {


    public AirCutterProjectileEntity(EntityType<? extends ArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
