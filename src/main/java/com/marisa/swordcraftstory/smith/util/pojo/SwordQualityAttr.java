package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 剑类武器品质属性
 */

public class SwordQualityAttr extends WeaponQualityAttr {

    private static final UUID UUID_LUCK = UUID.randomUUID();
    private static final UUID UUID_ATTACK_SPEED = UUID.randomUUID();

    /**
     * 攻速
     */
    private final double atkS;

    public SwordQualityAttr(int luck, int atk, int agl, int dur, int cri, double atkS) {
        super(UUID_LUCK, luck, atk, agl, dur, cri);
        this.atkS = atkS;
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        if (this.atkS != 0.0D) {
            event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(UUID_ATTACK_SPEED, "quality attr modifier", this.atkS, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        super.addAttributeModifier(event);
    }
}
