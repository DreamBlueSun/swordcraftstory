package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.smith.IMake;
import com.marisa.swordcraftstory.smith.util.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 近战模具
 */

public abstract class WeaponMould extends Mould {

    public WeaponMould() {
        super();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TranslatableComponent("攻击").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(CollapseHelper.getAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(CollapseHelper.getAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public ItemStack make(ItemStack stack, ItemStack mould, IMake iMake) {
        ItemStack copy = stack.copy();
        if (MakeHelper.getMakeRank(copy) == 0) {
            EnchantHelper.remEnchantmentTag(copy);
            EnchantHelper.copyEnchantmentTag(mould, copy);
            //升阶属性
            iMake.doMake(copy);
            //模具属性添加至强刃属性
            EdgeHelper.setAtk(copy, EdgeHelper.getAtk(stack) + CollapseHelper.getAtk(mould));
            EdgeHelper.setAgl(copy, EdgeHelper.getAgl(stack) + CollapseHelper.getAgl(mould));
        }
        return copy;
    }

    protected ItemStack collapseWeapon(ItemStack mould, ItemStack stack, int offset) {
        CollapseHelper.setAtk(mould, Math.max(SmithHelper.getDamageAtk(stack) / offset, 0));
        CollapseHelper.setAgl(mould, Math.max(SmithHelper.getSmithAgl(stack) / offset, 0));
        return mould;
    }
}
