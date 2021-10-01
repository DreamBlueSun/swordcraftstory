package com.marisa.swordcraftstory.skill.attack.helper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 特殊攻击接口
 */

public interface SpecialAttack {

    /**
     * 右键触发动作
     */
    ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn);

    /**
     * 使用动作
     */
    UseAction getUseAction();

    /**
     * 使用时长(tick)
     */
    int getUseDuration();

    /**
     * 使用结束动作
     */
    void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft);

    /**
     * 计算技能暴击率
     */
    int skillCri(int weaponCri);

}
