package com.marisa.swordcraftstory.skill.attack.helper;

import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * 抽象特殊攻击
 */

public abstract class AbstractAttack implements SpecialAttack {

    private final int cost;

    public AbstractAttack(int cost) {
        this.cost = cost;
    }

    @Override
    public InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        Weapon weapon = (Weapon) stack.getItem();
        if (weapon.isBroken(stack) || weapon.getDurAndDamage(stack) < this.cost) {
            //损坏后或耐久不足将不能再使用特殊攻击
            return InteractionResultHolder.fail(stack);
        }
//        playerIn.setActiveHand(handIn);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public UseAnim getUseAction() {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration() {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        stack.hurtAndBreak(this.cost, entityLiving, (entity) -> entity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
    }
}
