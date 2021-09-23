package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.item.intensify.helper.Intensify;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
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
 * 抽象矿石类
 */

public abstract class AbstractOre extends Item implements WeaponMake, Intensify {

    public AbstractOre(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("合成").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }

    /**
     * 制作时累加模具属性值
     */
    protected void addMouldProperties(ItemStack stack, ItemStack mouldStack) {
        if (!stack.isEmpty()) {
            Mould mould = (Mould) mouldStack.getItem();
            CombatPropertiesUtils.setAtk(stack, mould.getAtk(mouldStack));
            CombatPropertiesUtils.setDef(stack, mould.getDef(mouldStack));
            CombatPropertiesUtils.setAgl(stack, mould.getAgl(mouldStack));
            CombatPropertiesUtils.setDur(stack, mould.getDurMax(mouldStack));
            CombatPropertiesUtils.setDurMax(stack, mould.getDurMax(mouldStack));
        }
    }
}
