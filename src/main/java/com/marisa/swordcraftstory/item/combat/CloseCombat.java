package com.marisa.swordcraftstory.item.combat;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.StoryUUID;
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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

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
     * 攻击力
     */
    private final int atk;

    /**
     * 物理防御力
     */
    private final int def;

    /**
     * 魔法防御力
     */
    private final int phy;

    /**
     * 敏捷值（每点增加0.1%移速）
     */
    private final int agl;

    public CloseCombat(final int rank, final int atk, final int def, final int phy, final int agl, final IItemTier tier) {
        super(tier, atk, 0.0F, new Item.Properties().group(StoryGroup.COMBAT_GROUP));
        this.rank = rank;
        this.atk = atk;
        this.def = def;
        this.phy = phy;
        this.agl = agl;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CombatPropertiesUtils.addInformation(this, stack, tooltip);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(final EquipmentSlotType equipmentSlot, final ItemStack stack) {
        if (equipmentSlot == EquipmentSlotType.MAINHAND && getAgl(stack) != 0) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getAttributeModifiers(EquipmentSlotType.MAINHAND));
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(StoryUUID.MOVEMENT_SPEED, "Armor speed modifier", (Combat.AGL_SPEED_BASE_NUM * getAgl(stack)), AttributeModifier.Operation.MULTIPLY_TOTAL));
            return builder.build();
        }
        return super.getAttributeModifiers(equipmentSlot, stack);
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public int getAtk(ItemStack stack) {
        return this.atk + CombatPropertiesUtils.getAtk(stack);
    }

    @Override
    public int getDef(ItemStack stack) {
        return this.def + CombatPropertiesUtils.getDef(stack);
    }

    @Override
    public int getPhy(ItemStack stack) {
        return this.phy + CombatPropertiesUtils.getPhy(stack);
    }

    @Override
    public int getAgl(ItemStack stack) {
        return this.agl + CombatPropertiesUtils.getAgl(stack);
    }

    @Override
    public int getDur(ItemStack stack) {
        return CombatPropertiesUtils.getDur(stack);
    }

    @Override
    public int getDurMax(ItemStack stack) {
        return CombatPropertiesUtils.getDurMax(stack);
    }

    @Override
    public int getTec(ItemStack stack) {
        return CombatPropertiesUtils.getTec(stack);
    }

    @Override
    public void incrTec(ItemStack stack) {
        CombatPropertiesUtils.incrTec(stack);
    }
}
