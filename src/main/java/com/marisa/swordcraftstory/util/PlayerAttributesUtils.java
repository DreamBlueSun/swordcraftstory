//package com.marisa.swordcraftstory.util;
//
//import com.google.common.collect.ImmutableMultimap;
//import com.marisa.swordcraftstory.save.StoryPlayerData;
//import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
//import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkillHelper;
//import net.minecraft.entity.ai.attributes.Attribute;
//import net.minecraft.entity.ai.attributes.AttributeModifier;
//import net.minecraft.entity.ai.attributes.Attributes;
//import net.minecraft.entity.player.Player;
//
///**
// * 玩家属性工具类
// */
//
//public class PlayerAttributesUtils {
//
//    private static final int HP_MAX_BASE = 20;
//
//    public static void onClone(Player player, StoryPlayerData data, boolean heal) {
//        int lv = StoryPlayerDataManager.getLv(data.getXp());
//        onLevelUp(player, lv, heal);
//    }
//
//    public static void onLevelUp(Player player, int lv, boolean heal) {
//        //血量
//        int maxHealth = HP_MAX_BASE * (lv + 1);
//        int maxHealthAdd = maxHealth - (int) player.getAttributeValue(Attributes.MAX_HEALTH);
//        //执行
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", maxHealthAdd, AttributeModifier.Operation.ADDITION));
//        player.getAttributeManager().reapplyModifiers(builder.build());
//        //回复
//        if (heal) {
//            player.heal(maxHealthAdd);
//        }
//    }
//
//    public static void onConfigAttr(Player player, boolean newLoad, boolean heal) {
//        StoryPlayerData data = StoryPlayerDataManager.get(player.getStringUUID());
//        //血量
//        int newHpStory = WeaponSkillHelper.fixedUp(player, WeaponSkillHelper.LIST_HP_UP_ID);
//        int maxHealthAdd = newHpStory;
//        if (!newLoad) {
//            maxHealthAdd -= data.getHpStory();
//        }
//        data.setHpStory(newHpStory);
//        //攻击
//        data.setAtkStory(WeaponSkillHelper.fixedUp(player, WeaponSkillHelper.LIST_ATK_UP_ID));
//        //盔甲
//        int newDefStory = WeaponSkillHelper.fixedUp(player, WeaponSkillHelper.LIST_DEF_UP_ID);
//        int armorAdd = newDefStory;
//        if (!newLoad) {
//            armorAdd -= data.getDefStory();
//        }
//        data.setDefStory(newDefStory);
//        //执行
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", maxHealthAdd, AttributeModifier.Operation.ADDITION));
//        builder.put(Attributes.ARMOR, new AttributeModifier("armor modifier", armorAdd, AttributeModifier.Operation.ADDITION));
//        player.getAttributeManager().reapplyModifiers(builder.build());
//        //回复
//        if (heal) {
//            player.heal(maxHealthAdd);
//        } else if (maxHealthAdd < 0) {
//            //移除血量上限时减去当前血量
//            float f = player.getHealth() + maxHealthAdd;
//            if (f < 0.0F) {
//                f = 1.0F;
//            }
//            player.setHealth(f);
//        }
//    }
//
//}
