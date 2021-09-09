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

    //初心者武器Information
    public static void tipNovice(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("最适合初心者的练习用武器").mergeStyle(TextFormatting.WHITE));
    }

    //细的武器Information
    public static void tipThin(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("虽然细但比想象的更坚固").mergeStyle(TextFormatting.WHITE));
    }

    //庄严武器Information
    public static void tipSolemnity(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("进行精细加工的优雅兵器").mergeStyle(TextFormatting.WHITE));
    }

    //铁的武器Information
    public static void tipIron(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("特征是宽大结实的刀刃").mergeStyle(TextFormatting.WHITE));
    }

    //合成武器Information
    public static void tipSynthesis(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("形状特殊的漂亮武器").mergeStyle(TextFormatting.WHITE));
    }

    //羽毛武器Information
    public static void tipFeather(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("像羽毛一样轻很好用的武器").mergeStyle(TextFormatting.WHITE));
    }

    //典雅武器Information
    public static void tipElegance(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("这典雅的外观得到很多好评").mergeStyle(TextFormatting.WHITE));
    }

    //缎带武器Information
    public static void tipRibbon(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("附有缎带使武器性能飞跃性地提高").mergeStyle(TextFormatting.WHITE));
    }

    public static void addInformation(Combat combat, ItemStack stack, List<ITextComponent> tooltip) {
        stack.setTagInfo("HideFlags", IntNBT.valueOf(2));
        tooltip.add(new TranslationTextComponent("武器").mergeStyle(TextFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslationTextComponent("稀有度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getRank())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("攻击力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAtk(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("防御力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getDef(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
//        tooltip.add(new TranslationTextComponent("抗性").mergeStyle(TextFormatting.YELLOW)
//                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getPhy(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(combat.getAgl(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        float critical = (float) (Combat.CRITICAL_BASE_NUM + (combat.getTec(stack) / 5)) / 10;
        tooltip.add(new TranslationTextComponent("暴击率").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(critical + "%").mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("磨合度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(getTec(stack) + "/" + Combat.MAX_TEC).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(combat.getDur(stack) + "/" + combat.getDurMax(stack)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
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
        clearTec(stack);
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
        clearTec(stack);
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
        clearTec(stack);
    }

    public static int getDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur");
        }
        return v;
    }

    public static void setDur(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_dur", IntNBT.valueOf(amount));
    }

    public static int getDurMax(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_max");
        }
        return v;
    }

    public static void intensifyEdgeDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_max");
        }
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(v + Combat.INTENSIFY_EDGE_ONCE_NUM_DUR));
        clearTec(stack);
    }

    /**
     * 消耗dur修补耐久度
     */
    public static void useDur(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag == null) {
            return;
        }
        int damage = tag.getInt("Damage");
        if (damage == 0) {
            return;
        }
        int dur = tag.getInt("story_combat_dur");
        if (dur == 0) {
            return;
        }
        if (dur < damage) {
            stack.setTagInfo("story_combat_dur", IntNBT.valueOf(0));
            damage -= dur;
            stack.setTagInfo("Damage", IntNBT.valueOf(damage));
        } else {
            stack.setTagInfo("Damage", IntNBT.valueOf(0));
            dur -= damage;
            stack.setTagInfo("story_combat_dur", IntNBT.valueOf(dur));
        }
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

    public static void clearTec(ItemStack stack) {
        stack.setTagInfo("story_combat_tec", IntNBT.valueOf(0));
    }

}
