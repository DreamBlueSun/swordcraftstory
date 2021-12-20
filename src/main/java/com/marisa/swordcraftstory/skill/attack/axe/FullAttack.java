package com.marisa.swordcraftstory.skill.attack.axe;

import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.skill.attack.helper.AbstractAttack;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * 乾坤一击
 */

public class FullAttack extends AbstractAttack {

    public FullAttack(int cost) {
        super(cost);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if ((entityLiving instanceof Player playerIn)) {
            if (SpecialAttackHelper.useSp(stack, playerIn) >= 0.4) {
                if (playerIn.level.isClientSide()) {
                    //client
                    Minecraft minecraft = Minecraft.getInstance();
                    if (minecraft.hitResult != null && minecraft.hitResult.getType() == HitResult.Type.ENTITY) {
                        if (minecraft.gameMode != null) {
                            minecraft.gameMode.attack(playerIn, ((EntityHitResult)minecraft.hitResult).getEntity());
                            playerIn.swing(InteractionHand.MAIN_HAND);
                        }
                    }
                } else {
                    //server
                    ((Weapon) stack.getItem()).onSpecialAttack(stack);
                    super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
                }
            }
        }
    }

    @Override
    public int skillCri(int weaponCri) {
        return weaponCri + 1000;
    }
}
