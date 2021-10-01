package com.marisa.swordcraftstory.skill.attack.sword;

import com.marisa.swordcraftstory.entity.EntityTypeRegistry;
import com.marisa.swordcraftstory.entity.projectile.instance.AirCutterProjectileEntity;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.skill.attack.helper.AbstractAttack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 *
 */

public class AirCutter extends AbstractAttack {

    public AirCutter(int cost) {
        super(cost);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if ((entityLiving instanceof PlayerEntity)) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            AirCutterProjectileEntity airCutter = new AirCutterProjectileEntity(EntityTypeRegistry.AIR_CUTTER.get(), worldIn);
            airCutter.setDamage(((Weapon)stack.getItem()).getAtk(stack) * 1.6D);
            airCutter.setShooter(playerIn);
            airCutter.setNoGravity(true);
            airCutter.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
            airCutter.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double) 0.1F, playerIn.getPosZ());
            airCutter.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.6F, 0.0F);
            worldIn.addEntity(airCutter);
        }
    }
}
