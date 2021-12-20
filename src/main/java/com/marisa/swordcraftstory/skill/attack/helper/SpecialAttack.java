package com.marisa.swordcraftstory.skill.attack.helper;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * 特殊攻击接口
 */

public interface SpecialAttack {

    /**
     * 右键触发动作
     */
    InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, InteractionHand handIn);

    /**
     * 使用动作
     */
    UseAnim getUseAction();

    /**
     * 使用时长(tick)
     */
    int getUseDuration();

    /**
     * 使用结束动作
     */
    void onPlayerStoppedUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft);

    /**
     * 计算技能暴击率
     */
    int skillCri(int weaponCri);

}
