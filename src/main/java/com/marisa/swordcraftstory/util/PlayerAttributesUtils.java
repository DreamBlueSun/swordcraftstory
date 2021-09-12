package com.marisa.swordcraftstory.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @description: 玩家属性
 * @date: 2021/9/11 0011 12:41
 */

public class PlayerAttributesUtils {

    public static void onClone(PlayerEntity player, StoryPlayerData data, boolean heal) {
        int lv = StoryPlayerDataManager.getLv(data.getXp());
        onLevelUp(player, lv, heal);
    }

    public static void onLevelUp(PlayerEntity player, int lvOffset, boolean heal) {
        final int maxHealthAdd = lvOffset * 40;
        //如果玩家血量已经大于等于要增加的血量，则取消
        if ((int) player.getAttributeValue(Attributes.MAX_HEALTH) >= maxHealthAdd) {
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
