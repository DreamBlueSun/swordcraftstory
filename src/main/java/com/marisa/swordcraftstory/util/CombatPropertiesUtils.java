package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.combat.Combat;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/**
 * @description: 武器属性工具类
 * @date: 2021/9/5 0005 9:37
 */

public class CombatPropertiesUtils {

    public static void tipNovice(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("最适合初心者的练习用武器").mergeStyle(TextFormatting.WHITE));
    }

    public static void addInformation(Combat combat, ItemStack stack, List<ITextComponent> tooltip) {
        stack.setTagInfo("HideFlags", IntNBT.valueOf(2));
        tooltip.add(new TranslationTextComponent("稀有度  ").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getRank())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("攻击力  ").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAtk(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("物理防御").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getDef(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("魔法防御").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getPhy(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷    ").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAgl(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("磨合进度").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(getTec(stack) + "/255").mergeStyle(TextFormatting.LIGHT_PURPLE)));
    }

    public static int getAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        return v;
    }

    public static int getDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        return v;
    }

    public static int getPhy(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_phy");
        }
        return v;
    }

    public static int getAgl(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_agl");
        }
        return v;
    }

    public static int getTec(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_tec");
        }
        return v;
    }

    public static void incrTec(ItemStack stack) {
        int tec = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            tec = tag.getInt("story_combat_tec");
        }
        if (tec < Combat.MAX_TEC) {
            stack.setTagInfo("story_combat_tec", IntNBT.valueOf(tec + 1));
        }
    }

}
