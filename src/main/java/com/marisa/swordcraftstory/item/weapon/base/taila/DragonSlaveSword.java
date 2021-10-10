package com.marisa.swordcraftstory.item.weapon.base.taila;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
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
 * 龙破斩
 */

public class DragonSlaveSword extends SwordItem implements WeaponModel {

    public DragonSlaveSword() {
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
                return Ingredient.fromItems(ItemRegistry.DRAGON_SLAVE_SWORD.get());
            }
        }, 10, -2.4F, new Item.Properties().group(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("幻化[剑]").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case SWORD:
                stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.DRAGON_SLAVE_SWORD.getId()));
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
