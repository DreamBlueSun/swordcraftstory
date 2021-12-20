package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.model.ModelChangeHelper;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

/**
 * 武器信息工具类
 */

public class WeaponInformationUtils {

    //初心者武器Information
    public static void tipNovice(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("最适合初心者的练习用武器").withStyle(ChatFormatting.WHITE));
    }

    //硬武器Information
    public static void tipHard(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("小小的又轻又坚固用起来很顺手").withStyle(ChatFormatting.WHITE));
    }

    //细的武器Information
    public static void tipThin(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("虽然细但比想象的更坚固").withStyle(ChatFormatting.WHITE));
    }

    //庄严武器Information
    public static void tipSolemnity(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("进行精细加工的优雅兵器").withStyle(ChatFormatting.WHITE));
    }

    //铁的武器Information
    public static void tipIron(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("特征是宽大结实的刀刃").withStyle(ChatFormatting.WHITE));
    }

    //合成武器Information
    public static void tipSynthesis(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("形状特殊的漂亮武器").withStyle(ChatFormatting.WHITE));
    }

    //羽毛武器Information
    public static void tipFeather(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("像羽毛一样轻很好用的武器").withStyle(ChatFormatting.WHITE));
    }

    //斩铁武器Information
    public static void tipCutIron(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("考虑到空气阻力而设计的武器").withStyle(ChatFormatting.WHITE));
    }

    //大铁矿石武器Information
    public static void tipBigIron(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("非常巨大沉重使用困难").withStyle(ChatFormatting.WHITE));
    }

    //重金矿武器Information
    public static void tipHeavyGold(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("充满了难以使用的重量与压迫感").withStyle(ChatFormatting.WHITE));
    }

    //雷吉亚矿武器Information
    public static void tipRegia(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("很好用的武器深受冒险者青睐").withStyle(ChatFormatting.WHITE));
    }

    //典雅武器Information
    public static void tipElegance(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("这典雅的外观得到很多好评").withStyle(ChatFormatting.WHITE));
    }

    //缎带武器Information
    public static void tipRibbon(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("附有缎带使武器性能飞跃性地提高").withStyle(ChatFormatting.WHITE));
    }

    //甲壳武器Information
    public static void tipCarapaceFossil(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("复杂的工艺中可感受到其锋芒存在").withStyle(ChatFormatting.WHITE));
    }

    //幻象武器Information
    public static void tipFantasy(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("那不可思议的形状能让人在瞬间看见幻象").withStyle(ChatFormatting.WHITE));
    }

    //加利亚武器Information
    public static void tipGalia(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("构造复杂但性能卓越").withStyle(ChatFormatting.WHITE));
    }

    //料理武器Information
    public static void tipCuisine(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("用了这个就可以漂亮地还击回去了").withStyle(ChatFormatting.WHITE));
    }

    //双蛇武器Information
    public static void tipDoubleSnake(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("充满制造者恶意的武器").withStyle(ChatFormatting.WHITE));
    }

    //斯普林特武器Information
    public static void tipSprint(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("优雅的外表使持有者也变得气度非凡").withStyle(ChatFormatting.WHITE));
    }

    //艾克赛尔武器Information
    public static void tipXcel(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("无论是做武器还是装饰都很合适").withStyle(ChatFormatting.WHITE));
    }

    //雷齐斯托武器Information
    public static void tipLeijiStowe(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("用惯了以后更显其琢磨之姿").withStyle(ChatFormatting.WHITE));
    }

    //露萤武器Information
    public static void tipDewFirefly(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("用非凡的技法锻造出的梦幻武器").withStyle(ChatFormatting.WHITE));
    }

    //钢牙武器Information
    public static void tipSteelTeeth(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("如铁碎牙一般粗暴的武器").withStyle(ChatFormatting.WHITE));
    }

    //肉球武器Information
    public static void tipCatsPaw(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("就是现在决定了  喵喵喵").withStyle(ChatFormatting.WHITE));
    }

    //光辉武器Information
    public static void tipLuminosity(List<Component> tooltip) {
        tooltip.add(new TranslatableComponent("那美丽的翅膀简直像···").withStyle(ChatFormatting.WHITE));
    }

    public static void addInformation(Weapon weapon, ItemStack stack, List<Component> tooltip) {
        stack.getOrCreateTag().put("HideFlags", IntTag.valueOf(2));
        tooltip.add(new TranslatableComponent("武器").withStyle(ChatFormatting.LIGHT_PURPLE));
        if (weapon.isBroken(stack)) {
            tooltip.add(new TranslatableComponent("已损坏").withStyle(ChatFormatting.DARK_GRAY));
        }
        //幻化名称
        tooltip.add(new TranslatableComponent("幻化[" + ModelChangeHelper.getModelName(stack) + "]").withStyle(ChatFormatting.GREEN));
        //属性
        tooltip.add(new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(weapon.getRank())).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("攻击力").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(weapon.getAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("防御力").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(weapon.getDef(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("敏捷值").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(weapon.getAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        float critical = (float) weapon.getCri(stack) / 10;
        tooltip.add(new TranslatableComponent("暴击率").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(critical + "%").withStyle(ChatFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslatableComponent("熟练度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(CombatPropertiesUtils.getTec(stack) + "/" + Weapon.MAX_TEC).withStyle(ChatFormatting.LIGHT_PURPLE)));
        int lvlNnBreaking = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlNnBreaking > 0) {
            tooltip.add(new TranslatableComponent("耐久池").withStyle(ChatFormatting.YELLOW)
                    .append("     ").append(new TranslatableComponent(weapon.getDur(stack) + "/" + weapon.getDurMax(stack)).withStyle(ChatFormatting.LIGHT_PURPLE))
                    .append(new TranslatableComponent("(+" + (lvlNnBreaking * 15) + ")").withStyle(ChatFormatting.DARK_PURPLE)));
        } else {
            tooltip.add(new TranslatableComponent("耐久池").withStyle(ChatFormatting.YELLOW)
                    .append("     ").append(new TranslatableComponent(weapon.getDur(stack) + "/" + weapon.getDurMax(stack)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        }
//        //武技
//        String skillName = WeaponSkills.getById(weapon.getWeaponSkillId()).getName();
//        tooltip.add(new TranslatableComponent("武技").withStyle(ChatFormatting.AQUA)
//                .append(" ").append(new TranslatableComponent(">>>").withStyle(ChatFormatting.RED))
//                .append(" ").append(new TranslatableComponent(skillName).withStyle(ChatFormatting.AQUA)));
//        //特殊攻击字段显示
//        SpecialAttacks specialAttack = SpecialAttackHelper.get(stack);
//        if (specialAttack != null) {
//            tooltip.add(new TranslatableComponent("特殊攻击[" + specialAttack.getShow() + "]").withStyle(ChatFormatting.BLUE));
//        }
//        //效果字段显示
//        Effects effect = EffectHelper.get(stack);
//        if (effect != null) {
//            tooltip.add(new TranslatableComponent("效果[" + effect.getShow() + "]").withStyle(ChatFormatting.BLUE));
//        }
//        //强化字段显示
//        List<String> list = IntensifyHelper.getIntensifyName(stack);
//        if (list != null) {
//            for (String show : list) {
//                tooltip.add(new TranslatableComponent("强化").withStyle(ChatFormatting.AQUA)
//                        .append(" ").append(new TranslatableComponent(">>>").withStyle(ChatFormatting.RED))
//                        .append(" ").append(new TranslatableComponent(show).withStyle(ChatFormatting.AQUA)));
//            }
//        }
    }

}
