package com.marisa.swordcraftstory.item.weapon.base.taila.bow;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
 * 恒吹雪
 */

public class ConstantBlowersSnowBow extends BowItem implements WeaponModel {

    public ConstantBlowersSnowBow() {
        super(new Item.Properties().maxDamage(2021).group(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("幻化[弓]").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case BOW:
                stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.CONSTANT_BLOWERS_SNOW_BOW.getId()));
                return true;
            case SWORD:
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
