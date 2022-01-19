package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.smith.IMake;
import com.marisa.swordcraftstory.smith.util.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 盔甲模具
 */

public abstract class ArmorMould extends Mould {

    public ArmorMould() {
        super();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TranslatableComponent("护甲").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(CollapseHelper.getDef(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(new TranslatableComponent("韧性").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(CollapseHelper.getPhy(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public ItemStack make(ItemStack stack, ItemStack mould, IMake iMake) {
        ItemStack copy = stack.copy();
        if (MakeHelper.getMakeRank(copy) == 0) {
            EnchantHelper.remEnchantmentTag(copy);
            EnchantHelper.copyEnchantmentTag(mould, copy);
            //制作属性
            iMake.doMake(copy);
            //模具属性添加至强刃属性
            EdgeHelper.setDef(copy, EdgeHelper.getDef(stack) + CollapseHelper.getDef(mould));
            EdgeHelper.setPhy(copy, EdgeHelper.getPhy(stack) + CollapseHelper.getPhy(mould));
        }
        return copy;
    }

    protected ItemStack collapseArmor(ItemStack mould, ItemStack stack, int offset) {
        ArmorItem armor = (ArmorItem) stack.getItem();
        int def = armor.getDefense();
        def += SmithHelper.getSmithDef(stack);
        CollapseHelper.setDef(mould, Math.max(def / offset, 0));
        int phy = (int) armor.getToughness();
        phy += SmithHelper.getSmithPhy(stack);
        CollapseHelper.setPhy(mould, Math.max(phy / offset, 0));
        return mould;
    }
}
