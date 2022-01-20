package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 装备品质属性抽象类
 */

public abstract class AbstractQualityAttr {

    protected final UUID uuidLuck;

    /**
     * 幸运
     */
    protected final int luck;

    public AbstractQualityAttr(UUID uuidLuck, int luck) {
        this.uuidLuck = uuidLuck;
        this.luck = luck;
    }

    public void smithModify(ItemStack stack) {
        if (stack.isDamageableItem()) {
            stack.getOrCreateTag().putInt("HideFlags", 4);
            stack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
    }

    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        if (this.luck != 0) {
            event.addModifier(Attributes.LUCK, new AttributeModifier(this.uuidLuck, "quality attr modifier", this.luck, AttributeModifier.Operation.ADDITION));
        }
    }
}
