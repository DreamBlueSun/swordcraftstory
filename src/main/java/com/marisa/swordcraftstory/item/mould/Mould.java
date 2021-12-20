package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.weapon.helper.Combat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 抽象模具类
 */

public abstract class Mould extends Item implements Combat {

    public Mould(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("模具").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("攻击力").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(getAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("防御力").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(getDef(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("敏捷值").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(getAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("耐久池").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(getDurMax(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
    }

    @Override
    public int getAtk(ItemStack stack) {
        return CombatPropertiesUtils.getAtk(stack);
    }

    @Override
    public int getDef(ItemStack stack) {
        return CombatPropertiesUtils.getDef(stack);
    }

    @Override
    public int getAgl(ItemStack stack) {
        return CombatPropertiesUtils.getAgl(stack);
    }

    @Override
    public int getDurMax(ItemStack stack) {
        return CombatPropertiesUtils.getDurMax(stack);
    }
}
