package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.weapon.helper.Combat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("模具").mergeStyle(TextFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslationTextComponent("攻击力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(getAtk(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("防御力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(getDef(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(getAgl(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(getDurMax(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
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
