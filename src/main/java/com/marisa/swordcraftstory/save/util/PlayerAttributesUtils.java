package com.marisa.swordcraftstory.save.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.save.PlayerData;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

/**
 * 玩家属性工具类
 */

public class PlayerAttributesUtils {

    public static void onClone(Player player, PlayerData data, boolean heal) {
        upMaxHp(player, PlayerDataManager.getLv(data.getXp()), heal);
    }

    public static void checkLvUp(Player player, PlayerData playerData) {
        final int lv = PlayerDataManager.getLv(playerData.getXp());
        if (lv - PlayerDataManager.getLv(playerData.getXpLast()) > 0) {
            PlayerAttributesUtils.upMaxHp(player, lv, true);
        }
    }

    private static final int HP_MAX_BASE = 120;
    private static final int HP_MAX_OFFSET = 20;

    public static void upMaxHp(Player player, final int lv, boolean heal) {
        //血量
        int maxHealth = HP_MAX_BASE + HP_MAX_OFFSET * lv;
        int maxHealthAdd = maxHealth - (int) player.getAttributeValue(Attributes.MAX_HEALTH);
        //执行
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", maxHealthAdd, AttributeModifier.Operation.ADDITION));
        player.getAttributes().addTransientAttributeModifiers(builder.build());
        //回复
        if (heal) {
            player.heal(maxHealthAdd);
        }
    }

}
