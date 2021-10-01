package com.marisa.swordcraftstory.skill.attack.axe;

import com.marisa.swordcraftstory.skill.attack.helper.AbstractAttack;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * 全力一击
 */

public class FullAttack extends AbstractAttack {

    public FullAttack(int cost) {
        super(cost);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if ((entityLiving instanceof PlayerEntity)) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            if (playerIn.world.isRemote() && SpecialAttackHelper.useSp(stack, playerIn) >= 0.5) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.getType() == RayTraceResult.Type.ENTITY) {
                    if (minecraft.playerController != null) {
                        minecraft.playerController.attackEntity(playerIn, ((EntityRayTraceResult) minecraft.objectMouseOver).getEntity());
                        playerIn.swingArm(Hand.MAIN_HAND);
                    }
                }
            }
        }
    }

    @Override
    public int skillCri(int weaponCri) {
        return weaponCri + 1000;
    }
}
