package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.combat.Combat;
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
 * @description:
 * @date: 2021/9/1 0001 1:38
 */

public abstract class Mould extends Item implements Combat {

    /**
     * 攻击力
     */
    private int atk;

    /**
     * 物理防御力
     */
    private int def;

    /**
     * 敏捷值
     */
    private int agl;

    /**
     * 耐久度
     */
    private int durMax;

    public Mould(Properties properties) {
        super(properties);
        this.atk = 0;
        this.def = 0;
        this.agl = 0;
        this.durMax = 0;
    }

    public Mould(Properties properties, int atk, int def, int agl, int durMax) {
        super(properties);
        this.atk = atk;
        this.def = def;
        this.agl = agl;
        this.durMax = durMax;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("铸模").mergeStyle(TextFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslationTextComponent("攻击力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(this.atk)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("防御力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(this.def)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(this.agl)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(this.durMax)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
    }

    @Override
    public boolean isMould() {
        return true;
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    public int getAtk(ItemStack stack) {
        return this.atk;
    }

    @Override
    public int getDef(ItemStack stack) {
        return this.def;
    }

    @Override
    public int getPhy(ItemStack stack) {
        return 0;
    }

    @Override
    public int getAgl(ItemStack stack) {
        return this.agl;
    }

    @Override
    public int getDur(ItemStack stack) {
        return 0;
    }

    @Override
    public int getDurMax(ItemStack stack) {
        return this.durMax;
    }

    @Override
    public int getTec(ItemStack stack) {
        return 0;
    }

    @Override
    public void incrTec(ItemStack stack) {

    }
}
