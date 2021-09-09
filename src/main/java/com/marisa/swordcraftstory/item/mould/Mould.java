package com.marisa.swordcraftstory.item.mould;

import net.minecraft.client.util.ITooltipFlag;
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

public abstract class Mould extends Item {

    /**
     * 攻击力
     */
    private int atk;

    /**
     * 物理防御力
     */
    private int def;

    /**
     * 魔法防御力
     */
    private int phy;

    /**
     * 敏捷值
     */
    private int agl;

    /**
     * 耐久度
     */
    private int dur;

    /**
     * 磨合度
     */
    private int tec;

    public Mould(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("铸模").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }
}
