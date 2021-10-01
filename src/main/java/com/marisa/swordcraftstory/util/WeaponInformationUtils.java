package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.intensify.helper.IntensifyHelper;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.skill.effect.helper.EffectHelper;
import com.marisa.swordcraftstory.skill.effect.helper.Effects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/**
 * 武器信息工具类
 */

public class WeaponInformationUtils {

    //初心者武器Information
    public static void tipNovice(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("最适合初心者的练习用武器").mergeStyle(TextFormatting.WHITE));
    }

    //硬武器Information
    public static void tipHard(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("小小的又轻又坚固用起来很顺手").mergeStyle(TextFormatting.WHITE));
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

    //斩铁武器Information
    public static void tipCutIron(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("考虑到空气阻力而设计的武器").mergeStyle(TextFormatting.WHITE));
    }

    //大铁矿石武器Information
    public static void tipBigIron(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("非常巨大沉重使用困难").mergeStyle(TextFormatting.WHITE));
    }

    //重金矿武器Information
    public static void tipHeavyGold(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("充满了难以使用的重量与压迫感").mergeStyle(TextFormatting.WHITE));
    }

    //雷吉亚矿武器Information
    public static void tipRegia(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("很好用的武器深受冒险者青睐").mergeStyle(TextFormatting.WHITE));
    }

    //典雅武器Information
    public static void tipElegance(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("这典雅的外观得到很多好评").mergeStyle(TextFormatting.WHITE));
    }

    //缎带武器Information
    public static void tipRibbon(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("附有缎带使武器性能飞跃性地提高").mergeStyle(TextFormatting.WHITE));
    }

    public static void addInformation(Weapon weapon, ItemStack stack, List<ITextComponent> tooltip) {
        stack.setTagInfo("HideFlags", IntNBT.valueOf(2));
        if (weapon.isBroken(stack)) {
            tooltip.add(new TranslationTextComponent("已损坏").mergeStyle(TextFormatting.DARK_GRAY));
        }
        tooltip.add(new TranslationTextComponent("武器").mergeStyle(TextFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslationTextComponent("稀有度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(weapon.getRank())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("攻击力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(weapon.getAtk(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("防御力").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(weapon.getDef(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷值").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(String.valueOf(weapon.getAgl(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        float critical = (float) weapon.getCri(stack) / 10;
        tooltip.add(new TranslationTextComponent("暴击率").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(critical + "%").mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("熟练度").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(CombatPropertiesUtils.getTec(stack) + "/" + Weapon.MAX_TEC).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        int lvlNnBreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlNnBreaking > 0) {
            tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                    .appendString("     ").appendSibling(new TranslationTextComponent(weapon.getDur(stack) + "/" + weapon.getDurMax(stack)).mergeStyle(TextFormatting.LIGHT_PURPLE))
                    .appendSibling(new TranslationTextComponent("(+" + (lvlNnBreaking * 15) + ")").mergeStyle(TextFormatting.DARK_PURPLE)));
        } else {
            tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                    .appendString("     ").appendSibling(new TranslationTextComponent(weapon.getDur(stack) + "/" + weapon.getDurMax(stack)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        }
        //特殊攻击字段显示
        SpecialAttacks specialAttack = SpecialAttackHelper.get(stack);
        if (specialAttack != null) {
            tooltip.add(new TranslationTextComponent("特殊攻击[" + specialAttack.getShow() + "]").mergeStyle(TextFormatting.BLUE));
        }
        //效果字段显示
        Effects effect = EffectHelper.get(stack);
        if (effect != null) {
            tooltip.add(new TranslationTextComponent("效果[" + effect.getShow() + "]").mergeStyle(TextFormatting.BLUE));
        }
        //强化字段显示
        List<String> list = IntensifyHelper.getIntensifyName(stack);
        if (list != null) {
            for (String show : list) {
                tooltip.add(new TranslationTextComponent("强化").mergeStyle(TextFormatting.AQUA).appendString(" ")
                        .appendSibling(new TranslationTextComponent(">>>").mergeStyle(TextFormatting.RED))
                        .appendString(" ").appendSibling(new TranslationTextComponent(show).mergeStyle(TextFormatting.AQUA)));
            }
        }
    }

}
