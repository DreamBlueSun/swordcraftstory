package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 护腿类品质属性
 */

public class LegsQualityAttr extends ArmorQualityAttr {

    private static final UUID UUID_LUCK = UUID.randomUUID();
    private static final UUID UUID_MOVE_SPEED = UUID.randomUUID();

    /**
     * 百分比移速
     */
    private final double moveSpeed;

    public LegsQualityAttr(int luck, int def, int phy, double moveSpeed) {
        super(UUID_LUCK, luck, def, phy);
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        if (this.moveSpeed != 0.0D) {
            event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID_MOVE_SPEED, "quality attr modifier", this.moveSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        super.addAttributeModifier(event);
    }
}
