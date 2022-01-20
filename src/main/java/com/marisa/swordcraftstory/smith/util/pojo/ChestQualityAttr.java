package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 胸甲类品质属性
 */

public class ChestQualityAttr extends ArmorQualityAttr {

    private static final UUID UUID_LUCK = UUID.randomUUID();
    private static final UUID UUID_MAX_HEALTH = UUID.randomUUID();

    /**
     * 血量
     */
    private final int hp;

    public ChestQualityAttr(int luck, int def, int phy, int hp) {
        super(UUID_LUCK, luck, def, phy);
        this.hp = hp;
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        if (this.hp != 0) {
            event.addModifier(Attributes.MAX_HEALTH, new AttributeModifier(UUID_MAX_HEALTH, "quality attr modifier", this.hp, AttributeModifier.Operation.ADDITION));
        }
        super.addAttributeModifier(event);
    }
}
