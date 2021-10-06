package com.marisa.swordcraftstory.util;

import com.google.common.collect.ImmutableMultimap;
import com.marisa.swordcraftstory.save.MobAttrSaveData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.obj.MobAttr;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/**
 * Mob属性工具类
 */

public class MobAttributesUtils {

    /**
     * 设置mob属性值
     */
    private static void modifyMobAttr(MobEntity mobEntity, MobAttr mobAttr) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier("Max health modifier", mobAttr.getMaxHpAdd(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR, new AttributeModifier("armor modifier", mobAttr.getArmorAdd(), AttributeModifier.Operation.ADDITION));
        mobEntity.getAttributeManager().reapplyModifiers(builder.build());
    }

    /**
     * 加载保存的属性
     */
    public static void loadAttr(MobEntity mobEntity, MobAttr mobAttr) {
        //设置mob属性值
        modifyMobAttr(mobEntity, mobAttr);
        //回复生命值
        mobEntity.heal(mobAttr.getHp() - mobEntity.getHealth());
    }

    /**
     * 新增属性
     */
    public static MobAttr newAttr(MobEntity mobEntity, PlayerEntity closestPlayer) {
        //mob实体加入世界时，根据最近玩家等级增加属性
        int lv = closestPlayer != null ? mobSpanLv(closestPlayer) : 0;
        int maxHealthAdd = lv * (int) MathHelper.clamp(mobEntity.getMaxHealth(), 2.0F, 20.0F);
        int armorAdd = lv;
        //如果mob原血量大于等于100(判定为BOSS生物)，则血量额外增加、防御额外增加
        boolean isBoos = mobEntity.getMaxHealth() >= 100;
        if (isBoos) {
            maxHealthAdd = lv * (int) MathHelper.clamp(mobEntity.getMaxHealth(), 20.0F, 200.0F);
            armorAdd += lv;
        }
        MobAttr mobAttr = new MobAttr(mobEntity.getUniqueID().toString(), lv, maxHealthAdd, armorAdd);
        //设置mob属性值
        modifyMobAttr(mobEntity, mobAttr);
        //回复生命值
        mobEntity.heal(mobAttr.getMaxHpAdd());
        return mobAttr;
    }

    /**
     * Mob出生等级
     */
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

    /**
     * 获取Mob等级
     */
    public static int getMobLv(ServerWorld world, String uuid) {
        MobAttr mobAttr = MobAttrSaveData.getInstance(world).get(uuid);
        return mobAttr != null ? mobAttr.getLv() : 0;
    }

}
