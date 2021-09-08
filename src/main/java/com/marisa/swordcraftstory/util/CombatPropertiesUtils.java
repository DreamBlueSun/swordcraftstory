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
        tooltip.add(new TranslationTextComponent("稀有度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getRank())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("攻击力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAtk(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("防御力").mergeStyle(TextFormatting.YELLOW)
//                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getDef(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
//        tooltip.add(new TranslationTextComponent("抗性").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getPhy(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAgl(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        float critical = (float) (Combat.CRITICAL_BASE_NUM + (combat.getTec(stack) / 5)) / 10;
        tooltip.add(new TranslationTextComponent("暴击率").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(critical + "%").mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("磨合度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(getTec(stack) + "/255").mergeStyle(TextFormatting.LIGHT_PURPLE)));
        int useNumMax = combat.getDur(stack) + stack.getMaxDamage();
        int usedNum = combat.getDurDamage(stack) + stack.getDamage();
        tooltip.add(new TranslationTextComponent("耐久值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent((useNumMax - usedNum) + "/" + useNumMax).mergeStyle(TextFormatting.LIGHT_PURPLE)));
    }

    public static int getAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        return v;
    }

    public static void intensifyEdgeAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(v + Combat.INTENSIFY_EDGE_ONCE_NUM_ATK));
    }

    public static int getDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        return v;
    }

    public static void intensifyEdgeDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(v + Combat.INTENSIFY_EDGE_ONCE_NUM_DEF));
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

    public static void intensifyEdgeAgl(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_agl");
        }
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(v + Combat.INTENSIFY_EDGE_ONCE_NUM_AGL));
    }

    public static int getDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur");
        }
        return v;
    }

    public static int getDurDamage(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_damage");
        }
        return v;
    }

    /**
     * 处理dur伤害
     * 返回溢出的dur伤害
     */
    public static int doDurDamage(ItemStack stack, int amount) {
        int eV = 0;
        if (amount < 1) {
            return eV;
        }
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_damage");
        }
        v += amount;
        int durMax = ((Combat) stack.getItem()).getDur(stack);
        if (v > durMax) {
            eV = v - durMax;
            v = durMax;
        }
        stack.setTagInfo("story_combat_dur_damage", IntNBT.valueOf(v));
        return eV;
    }

    public static void intensifyEdgeDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur");
        }
        stack.setTagInfo("story_combat_dur", IntNBT.valueOf(v + Combat.INTENSIFY_EDGE_ONCE_NUM_DUR));
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
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_tec");
        }
        if (v < Combat.MAX_TEC) {
            stack.setTagInfo("story_combat_tec", IntNBT.valueOf(v + 1));
        }
    }

}
