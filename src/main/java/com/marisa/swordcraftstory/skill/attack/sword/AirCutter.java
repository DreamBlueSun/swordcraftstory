package com.marisa.swordcraftstory.skill.attack.sword;

import com.marisa.swordcraftstory.skill.attack.helper.AbstractAttack;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 真空刃
 */

public class AirCutter extends AbstractAttack {

    public AirCutter(int cost) {
        super(cost);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if ((entityLiving instanceof Player playerIn)) {
            if (SpecialAttackHelper.useSp(stack, playerIn) >= 0.2) {
//                AirCutterProjectileEntity airCutter = new AirCutterProjectileEntity(EntityTypeRegistry.AIR_CUTTER.get(), worldIn);
//                airCutter.setDamage(((Weapon) stack.getItem()).getAtk(stack) * 1.6D);
//                airCutter.setShooter(playerIn);
//                airCutter.setNoGravity(true);
//                airCutter.pickupStatus = AbstractArrow.PickupStatus.DISALLOWED;
//                airCutter.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double) 0.1F, playerIn.getPosZ());
//                airCutter.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.6F, 0.0F);
//                worldIn.addEntity(airCutter);
                super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
            }
        }
    }

    @Override
    public int skillCri(int weaponCri) {
        return 0;
    }
}
