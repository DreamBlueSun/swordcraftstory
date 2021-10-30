package com.marisa.swordcraftstory.item.weapon.helper;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.util.StoryUUID;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/**
 * 物语武器公共方法
 */

public class WeaponCommonFunction {

    /**
     * 武器添加主手属性方法
     */
    public static Multimap<Attribute, AttributeModifier> attributeModifierBuild(ItemStack stack, Multimap<Attribute, AttributeModifier> superMainHandMap) {
        Weapon weapon = (Weapon) stack.getItem();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(superMainHandMap);
        builder.put(Attributes.ARMOR, new AttributeModifier(StoryUUID.ARMOR, "Armor armor modifier", weapon.getDef(stack), AttributeModifier.Operation.ADDITION));
        if (weapon.getAgl(stack) != 0) {
            double v;
            int agl = weapon.getAgl(stack);
            if (agl > 50) {
                v = Weapon.AGL_SPEED_BASE_NUM * 50 + (Weapon.AGL_SPEED_BASE_NUM * (agl - 50)) / 4;
            } else if (agl < -50) {
                v = Weapon.AGL_SPEED_BASE_NUM * -50 + (Weapon.AGL_SPEED_BASE_NUM * (agl + 50)) / 4;
            } else {
                v = Weapon.AGL_SPEED_BASE_NUM * agl;
            }
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(StoryUUID.MOVEMENT_SPEED, "Armor speed modifier", v, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        return builder.build();
    }

    /**
     * 武器受损方法
     */
    public static <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Random random) {
        Weapon weapon = (Weapon) stack.getItem();
        //已损坏时
        if (weapon.isBroken(stack)) {
            //取消损伤
            return 0;
        }
        if (stack.isDamageable()) {
            //优先消耗dur
            CompoundNBT tag = stack.getOrCreateTag();
            int dur = tag.getInt("story_combat_dur");
            if (dur > 0) {
                if (dur < amount) {
                    stack.setTagInfo("story_combat_dur", IntNBT.valueOf(0));
                    amount -= dur;
                } else {
                    dur -= amount;
                    stack.setTagInfo("story_combat_dur", IntNBT.valueOf(dur));
                    amount = 0;
                }
            }
            //后续消耗damage
            if (amount > 0) {
                int l = stack.getDamage() + amount;
                //物品要损坏时
                if (l >= stack.getMaxDamage()) {
                    //防止被损坏
                    l = 0;
                    //标记为已损坏
                    weapon.setBroken(stack);
                    //物品损坏声音
                    if (entity instanceof ServerPlayerEntity) {
                        ServerPlayerEntity playerEntity = (ServerPlayerEntity) entity;
                        ServerWorld worldIn = playerEntity.getServerWorld();
                        worldIn.playSound(null, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 3.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
                    }
                }
                stack.setDamage(l);
                //耐久条显示变化
                if (entity instanceof ServerPlayerEntity) {
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger((ServerPlayerEntity) entity, stack, l);
                }
            }
        }
        return 0;
    }

}
