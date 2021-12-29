package com.marisa.swordcraftstory.item.weapon;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 *
 */

public class TestSword extends SwordItem {


    public TestSword() {
        super(new Tier() {
            @Override
            public int getUses() {
                return 100;
            }

            @Override
            public float getSpeed() {
                return 10.0F;
            }

            @Override
            public float getAttackDamageBonus() {
                return -1.0F;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public int getEnchantmentValue() {
                return 20;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
//                return Ingredient.of(ItemRegistry.TEST_SWORD.get());
                return null;
            }
        }, 6, -2.4F, new Item.Properties().tab(StoryGroup.MAIN));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TextComponent("喝下去可以回复HP").withStyle(ChatFormatting.WHITE));
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return super.getAttributeModifiers(slot, stack);
    }
}
