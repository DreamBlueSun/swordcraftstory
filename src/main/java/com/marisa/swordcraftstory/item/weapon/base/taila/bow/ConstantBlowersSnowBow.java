package com.marisa.swordcraftstory.item.weapon.base.taila.bow;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.IntNBT;

/**
 * 恒吹雪
 */

public class ConstantBlowersSnowBow extends BowItem implements WeaponModel {

    public ConstantBlowersSnowBow() {
        super(new Item.Properties().maxDamage(2021).group(StoryGroup.MODEL_CHANGE_GROUP));
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
