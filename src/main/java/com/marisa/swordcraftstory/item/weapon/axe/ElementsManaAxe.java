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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
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
        super(new IItemTier() {
            @Override
            public int getMaxUses() {
                return 2021;
            }

            @Override
            public float getEfficiency() {
                return 10.0F;
            }

            @Override
            public float getAttackDamage() {
                return -1.0F;
            }

            @Override
            public int getHarvestLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 26;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromItems(ItemRegistry.ELEMENTS_MANA_AXE.get());
            }
        }, 14, -3.0F, new Item.Properties().group(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("幻化[斧]").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        if (((Weapon) stack.getItem()).type() == WeaponType.AXE) {
            stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.ELEMENTS_MANA_AXE.getId()));
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
