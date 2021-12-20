package com.marisa.swordcraftstory.item.weapon.base.taila.bow;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
 * 恒吹雪
 */

public class ConstantBlowersSnowBow extends BowItem implements WeaponModel {

    public ConstantBlowersSnowBow() {
        super(new Item.Properties().maxDamage(2021).tab(StoryGroup.MODEL_CHANGE_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("幻化[弓]").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean build(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case BOW:
                stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.CONSTANT_BLOWERS_SNOW_BOW.getId()));
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
