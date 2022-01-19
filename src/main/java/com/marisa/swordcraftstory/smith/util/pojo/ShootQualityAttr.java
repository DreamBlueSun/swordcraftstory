package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 射击类武器品质属性
 */

public class ShootQualityAttr extends WeaponQualityAttr {

    private static final UUID UUID_LUCK = UUID.randomUUID();

    public ShootQualityAttr(int luck, int atk, int agl, int dur, int cri) {
        super(UUID_LUCK, luck, atk, agl, dur, cri);
    }

    @Override
    public void smithModify(ItemStack stack) {
        super.smithModify(stack);
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        super.addAttributeModifier(event);
    }
}
