package com.marisa.swordcraftstory.item.combat;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.GroupRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * @description: 近战武器抽象类
 * @date: 2021/9/4 0004 6:18
 */

public abstract class CloseCombat extends SwordItem implements Combat {

    /**
     * 稀有度级别
     */
    private final int rank;

    /**
     * 物理防御力
     */
    private int def;

    /**
     * 魔法防御力
     */
    private int phy;

    /**
     * 敏捷值（每点增加0.1%移速）
     */
    private int agl;

    /**
     * 磨合度
     */
    private int tec;

    public CloseCombat(final int rank, final int atk, final int def, final int phy, final int agl, final IItemTier tier) {
        super(tier, atk, 0.0F, new Item.Properties().group(GroupRegistry.COMBAT_GROUP));
        this.rank = rank;
        this.def = def;
        this.phy = phy;
        this.agl = agl;
        this.tec = 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("稀有度  ").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(getRank())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("物理防御").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(getDef())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("魔法防御").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(getPhy())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("敏捷    ").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(getAgl())).mergeStyle(TextFormatting.LIGHT_PURPLE)));
        tooltip.add(new TranslationTextComponent("磨合进度").mergeStyle(TextFormatting.YELLOW)
                .appendString("   ").appendSibling(new TranslationTextComponent(String.valueOf(getTec()) + "/255").mergeStyle(TextFormatting.LIGHT_PURPLE)));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(final EquipmentSlotType equipmentSlot, final ItemStack stack) {
        if (equipmentSlot == EquipmentSlotType.MAINHAND && getAgl() != 0) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getAttributeModifiers(EquipmentSlotType.MAINHAND));
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("58b7ff54-706b-4b0b-80f7-0dce04a67325"), "Armor speed modifier", (0.001 * getAgl()), AttributeModifier.Operation.MULTIPLY_TOTAL));
            return builder.build();
        }
        return super.getAttributeModifiers(equipmentSlot, stack);
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public int getAtk() {
        return (int) super.getAttackDamage();
    }

    @Override
    public int getDef() {
        return this.def;
    }

    @Override
    public int getPhy() {
        return this.phy;
    }

    @Override
    public int getAgl() {
        return this.agl;
    }

    @Override
    public int getDur() {
        ItemStack stack = new ItemStack(this);
        return stack.getMaxDamage() - stack.getDamage();
    }

    @Override
    public int getTec() {
        return this.tec;
    }

}
