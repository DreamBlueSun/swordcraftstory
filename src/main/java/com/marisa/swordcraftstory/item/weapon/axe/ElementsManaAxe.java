package com.marisa.swordcraftstory.item.weapon.axe;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
 * 元素之斧
 */

public class ElementsManaAxe extends AxeItem implements WeaponModel {

    public ElementsManaAxe() {
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
                return Ingredient.of(ItemRegistry.ELEMENTS_MANA_AXE.get());
            }
        }, 14, -3.0F, new Item.Properties().tab(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("幻化[斧]").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        if (((Weapon) stack.getItem()).type() == WeaponType.AXE) {
            stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.ELEMENTS_MANA_AXE.getId()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getModelName(ItemStack stack) {
        return this.getName().getString();
    }
}
