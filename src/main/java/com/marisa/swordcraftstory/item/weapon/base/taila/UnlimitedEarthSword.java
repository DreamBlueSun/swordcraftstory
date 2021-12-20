package com.marisa.swordcraftstory.item.weapon.base.taila;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 无限大地
 */

public class UnlimitedEarthSword extends SwordItem implements WeaponModel {

    public UnlimitedEarthSword() {
        super(new Tier() {
            @Override
            public int getUses() {
                return 2021;
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
                return 26;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(ItemRegistry.DRAGON_SLAVE_SWORD.get());
            }
        }, 10, -2.4F, new Properties().tab(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("幻化[剑]").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case SWORD:
                stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.UNLIMITED_EARTH_SWORD.getId()));
                return true;
            case BOW:
            case AXE:
            default:
                return false;
        }
    }

    @Override
    public String getModelName(ItemStack stack) {
        return this.getName().getString();
    }
}
