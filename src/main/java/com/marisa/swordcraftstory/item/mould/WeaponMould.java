package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponUtils;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 近战模具
 */

public abstract class WeaponMould extends Mould {

    public WeaponMould() {
        super();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TranslatableComponent("攻击").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public ItemStack make(ItemStack stack, ItemStack mould, AbstractOre ore) {
        ItemStack copy = stack.copy();
        if (SmithNbtUtils.getRank(copy) == 0) {
            SmithNbtUtils.remEnchantmentTag(copy);
            SmithNbtUtils.copyEnchantmentTag(mould, copy);
            SmithNbtUtils.setAtk(copy, SmithNbtUtils.getAtk(copy) + SmithNbtUtils.getAtk(mould));
            SmithNbtUtils.setAgl(copy, SmithNbtUtils.getAgl(copy) + SmithNbtUtils.getAgl(mould));
            copy = ore.itemRankUp(copy);
        }
        return copy;
    }

    protected ItemStack collapseWeapon(ItemStack mould, ItemStack stack, int offset) {
        int atk = WeaponUtils.getBaseAttackDamage(stack.getItem());
        atk += SmithNbtUtils.getAtk(stack);
        int agl = SmithNbtUtils.getAgl(stack);
        SmithNbtUtils.setAtk(mould, Math.max(atk / offset, 0));
        SmithNbtUtils.setAgl(mould, Math.max(agl / offset, 0));
        return mould;
    }
}
