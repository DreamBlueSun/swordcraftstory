package com.marisa.swordcraftstory.event.pojo;

import com.marisa.swordcraftstory.smith.util.SmithHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 * 吸收
 */

public class Absorb {

    /**
     * 附魔百分比吸收(0.0-1.0)
     */
    private final float enchantAbsorb;

    /**
     * 状态百分比吸收(0.0-1.0)
     */
    private final float buffAbsorb;

    /**
     * 普通防御
     */
    private final float p;

    /**
     * 魔法防御
     */
    private final float m;

    /**
     * 真实防御
     */
    private final float r;

    public Absorb(LivingEntity target, DamageSource source) {
        //附魔伤害减免：每级1%免伤，最高40级
        this.enchantAbsorb = Mth.clamp(EnchantmentHelper.getDamageProtection(target.getArmorSlots(), source), 0, 40) * 0.01F;
        //每级抗性10%(1/10)免伤、最高4级
        MobEffectInstance effect = target.getEffect(MobEffects.DAMAGE_RESISTANCE);
        if (effect != null) {
            int i = Mth.clamp(effect.getAmplifier() + 1, 1, 4);
            this.buffAbsorb = Math.max(i / 10.0F, 0.0F);
        } else {
            this.buffAbsorb = 0.0F;
        }
        int def = 0;
        for (ItemStack stack : target.getArmorSlots()) {
            def += SmithHelper.getSmithDef(stack);
        }
        this.p = (float) target.getArmorValue() + def;
        int phy = 0;
        for (ItemStack stack : target.getArmorSlots()) {
            phy += SmithHelper.getSmithPhy(stack);
        }
        this.m = (float) target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) + phy;
        this.r = 0;
    }

    public float getEnchantAbsorb() {
        return enchantAbsorb;
    }

    public float getBuffAbsorb() {
        return buffAbsorb;
    }

    public float getP() {
        return p;
    }

    public float getM() {
        return m;
    }

    public float getR() {
        return r;
    }
}
