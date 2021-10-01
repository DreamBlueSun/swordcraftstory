package com.marisa.swordcraftstory.skill.attack.helper;

import com.marisa.swordcraftstory.item.weapon.Weapon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 抽象特殊攻击
 */

public abstract class AbstractAttack implements SpecialAttack {

    private final int cost;

    public AbstractAttack(int cost) {
        this.cost = cost;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        Weapon weapon = (Weapon) stack.getItem();
        if (weapon.isBroken(stack) || weapon.getDurAndDamage(stack) < this.cost) {
            //损坏后或耐久不足将不能再使用特殊攻击
            return ActionResult.resultFail(stack);
        }
        weapon.onSpecialAttack(stack);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(stack);
    }

    @Override
    public UseAction getUseAction() {
        return UseAction.SPEAR;
    }

    @Override
    public int getUseDuration() {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if ((entityLiving instanceof PlayerEntity)) {
            stack.damageItem(13, entityLiving, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        }
    }
}
