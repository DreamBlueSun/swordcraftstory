package com.marisa.swordcraftstory.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Random;

/**
 * @description: Mob属性
 * @date: 2021/9/11 0011 12:41
 */

public class MobAttributesUtils {

    public static void onJoinWorld(PlayerEntity closestPlayer, MobEntity mobEntity) {
        if (closestPlayer != null) {
            //mob实体加入世界时，根据最近玩家等级增加属性
            int lv = mobSpanLv(closestPlayer);
            modifyMobAttr(mobEntity, lv);
            //生成完毕后自定义名称
            mobEntity.setCustomName(new TranslationTextComponent("[" + mobEntity.getDisplayName().getString() + "]").mergeStyle(TextFormatting.RED)
                    .appendSibling(new TranslationTextComponent("LV").mergeStyle(TextFormatting.WHITE))
                    .appendSibling(new TranslationTextComponent(String.valueOf(lv)).mergeStyle(TextFormatting.GREEN)));
        } else if (mobEntity.hasCustomName() && !mobEntity.getDisplayName().getStyle().isEmpty()) {
            //没有玩家(例如世界加载完毕时)时如果有自定义名称、Style不为Empty并且名称格式正确，从名称恢复等级
            int lv = getLvByName(mobEntity.getDisplayName().getString());
            if (lv > 0) {
                modifyMobAttr(mobEntity, lv);
            }
        }
    }

    public static int getLvByName(String displayName) {
        int lv = 0;
        try {
            String[] split = displayName.split("]LV");
            if (split.length == 2) {
                lv = Integer.parseInt(split[1]);
            }
        } catch (Exception e) {
            Story.LOG.error("从名称-" + displayName + "-恢复等级-异常：", e);
        }
        return lv;
    }

    private static int mobSpanLv(PlayerEntity closestPlayer) {
        int lv = StoryPlayerDataManager.getLv(StoryPlayerDataManager.get(closestPlayer.getCachedUniqueIdString()).getXp());
        //随机+-1~2LV
        int offset = new Random().nextInt(3);
        if (new Random().nextInt(2) > 0) {
            lv += offset;
        } else if (lv >= offset) {
            lv -= offset;
        }
        return lv;
    }

    private static void modifyMobAttr(MobEntity mobEntity, int lv) {
        int maxHealthAdd = lv * 70;
        int attackDamageAdd = lv * 5;
        int armorAdd = lv * 3;
        //如果原mob血量大于等于50(判定为BOSS生物)，则血量额外增加、攻击额外增加、防御额外增加
        if (mobEntity.getMaxHealth() >= 50) {
            maxHealthAdd += mobEntity.getMaxHealth() * 10;
            maxHealthAdd += lv * 2;
            maxHealthAdd += lv;
        }
        //如果生物血量已经大于等于要增加的量，则取消
        if ((int)mobEntity.getAttributeValue(Attributes.MAX_HEALTH) >= maxHealthAdd) {
            return;
        }
        //设置mob属性值
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", maxHealthAdd, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier("attack damage modifier", attackDamageAdd, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR, new AttributeModifier("armor modifier", armorAdd, AttributeModifier.Operation.ADDITION));
        mobEntity.getAttributeManager().reapplyModifiers(builder.build());
        //未之定义名称时，判定为新生成mob，恢复生命值到最大
        if (!mobEntity.hasCustomName()) {
            mobEntity.heal(maxHealthAdd);
        }
    }

}
