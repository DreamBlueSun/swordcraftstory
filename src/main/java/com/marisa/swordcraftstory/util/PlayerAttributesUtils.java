package com.marisa.swordcraftstory.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

/**
 * 玩家属性工具类
 */

public class PlayerAttributesUtils {

    public static void onClone(PlayerEntity player, StoryPlayerData data, boolean heal) {
        int lv = StoryPlayerDataManager.getLv(data.getXp());
        onLevelUp(player, lv, heal);
    }

    public static void onLevelUp(PlayerEntity player, int lv, boolean heal) {
        final int maxHealth = 20 + lv * 40;
        //计算要增加的血量
        int maxHealthAdd = maxHealth - (int) player.getAttributeValue(Attributes.MAX_HEALTH);
        if (maxHealthAdd <= 0) {
            return;
        }
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", maxHealthAdd, AttributeModifier.Operation.ADDITION));
        player.getAttributeManager().reapplyModifiers(builder.build());
        if (heal) {
            player.heal(maxHealthAdd);
        }
    }

}
