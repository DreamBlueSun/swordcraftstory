package com.marisa.swordcraftstory.util;

import com.marisa.swordcraftstory.item.weapon.Weapon;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/**
 * 武器属性工具类
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
        tooltip.add(new TranslationTextComponent("耐久池").mergeStyle(TextFormatting.YELLOW)
                .appendString("     ").appendSibling(new TranslationTextComponent(weapon.getDur(stack) + "/" + weapon.getDurMax(stack)).mergeStyle(TextFormatting.LIGHT_PURPLE)));
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

    public static void intensifyEdgeAtk(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_atk");
        }
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(v + Weapon.INTENSIFY_EDGE_ONCE_NUM_ATK));
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

    public static void setDef(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeDef(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_def");
        }
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(v + Weapon.INTENSIFY_EDGE_ONCE_NUM_DEF));
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

    public static void setAgl(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeAgl(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_agl");
        }
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(v + Weapon.INTENSIFY_EDGE_ONCE_NUM_AGL));
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

    public static void setDurMax(ItemStack stack, int amount) {
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(amount));
    }

    public static void intensifyEdgeDur(ItemStack stack) {
        int v = 0;
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            v = tag.getInt("story_combat_dur_max");
        }
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(v + Weapon.INTENSIFY_EDGE_ONCE_NUM_DUR));
        clearTec(stack);
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
