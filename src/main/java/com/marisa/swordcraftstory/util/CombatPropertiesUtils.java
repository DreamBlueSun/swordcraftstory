package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.skill.effect.helper.Effects;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.skill.effect.helper.EffectHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * 武器属性工具类
 */

public class CombatPropertiesUtils {

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
                .appendString("     ").appendSibling(new TranslationTextComponent(getTec(stack) + "/" + Weapon.MAX_TEC).mergeStyle(TextFormatting.LIGHT_PURPLE)));
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
        List<String> list = getIntensifyName(stack);
        if (list != null) {
            for (String show : list) {
                tooltip.add(new TranslationTextComponent("强化").mergeStyle(TextFormatting.AQUA).appendString(" ")
                        .appendSibling(new TranslationTextComponent(">>>").mergeStyle(TextFormatting.RED))
                        .appendString(" ").appendSibling(new TranslationTextComponent(show).mergeStyle(TextFormatting.AQUA)));
            }
        }
    }

    /**
     * 判断可以进行强化武器（3次上限）
     */
    public static boolean canIntensifyAttr(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify");
            return listNBT.size() < 3;
        }
        return true;
    }

    /**
     * 强化武器属性
     */
    public static void intensifyAttr(ItemStack stack, IntensifyAttr attr) {
        intensifyEdgeAtk(stack, attr.getAtk());
        intensifyEdgeDef(stack, attr.getDef());
        intensifyEdgeAgl(stack, attr.getAgl());
        intensifyEdgeDur(stack, attr.getDur());
        //添加强化NBT
        CompoundNBT tag = stack.getOrCreateTag();
        ListNBT listNBT;
        if (tag.contains("story_combat_intensify")) {
            listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
        } else {
            listNBT = new ListNBT();
        }
        listNBT.add(IntNBT.valueOf(attr.getId()));
        tag.put("story_combat_intensify", listNBT);
    }

    /**
     * 获取强化ID列表
     */
    public static List<Integer> getIntensifyIds(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<Integer> list = new ArrayList<>();
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
            for (int i = 0; i < listNBT.size(); i++) {
                list.add(Intensifys.getById(listNBT.getInt(i)).getId());
            }
            return list;
        }
        return null;
    }

    /**
     * 获取强化展示名称列表
     */
    public static List<String> getIntensifyName(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<String> list = new ArrayList<>();
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
            for (int i = 0; i < listNBT.size(); i++) {
                list.add(Intensifys.getById(listNBT.getInt(i)).getShow());
            }
            return list;
        }
        return null;
    }

    public static int getAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        return v;
    }

    public static void setAtk(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeAtk(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_atk");
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(v + amount));
    }

    public static int getDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        return v;
    }

    public static void setDef(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeDef(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_def");
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(v + amount));
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

    public static void setAgl(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeAgl(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_agl");
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(v + amount));
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

    public static void setDurMax(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(amount));
    }

    public static int getDurMaxAfterEffect(ItemStack stack) {
        int v = getDurMax(stack);
        int lvlUnBreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
        if (lvlUnBreaking > 0) {
            v += lvlUnBreaking * 15;
        }
        return v;
    }

    public static void intensifyEdgeDur(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_dur_max");
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(v + amount));
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
        if (v < Weapon.MAX_TEC) {
            stack.setTagInfo("story_combat_tec", IntNBT.valueOf(v + 1));
        }
    }

    public static void clearTec(ItemStack stack) {
        stack.setTagInfo("story_combat_tec", IntNBT.valueOf(0));
    }

    /**
     * 复制物品的附魔
     */
    public static void copyEnchantmentTag(ItemStack source, ItemStack target) {
        ListNBT listEnchant = source.getEnchantmentTagList();
        if (listEnchant.size() > 0) {
            CompoundNBT tag = target.getOrCreateTag();
            for (INBT inbt : listEnchant) {
                if (!tag.contains("Enchantments", 9)) {
                    tag.put("Enchantments", new ListNBT());
                }
                ListNBT listnbt = tag.getList("Enchantments", 10);
                listnbt.add(inbt);
            }
        }
    }

}
