package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.SmithHelper;
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
                .append(new TranslatableComponent(String.valueOf(SmithHelper.Collapse.getAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(SmithHelper.Collapse.getAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public ItemStack make(ItemStack stack, ItemStack mould, AbstractOre ore) {
        ItemStack copy = stack.copy();
        if (SmithNbtUtils.getRank(copy) == 0) {
            SmithNbtUtils.remEnchantmentTag(copy);
            SmithNbtUtils.copyEnchantmentTag(mould, copy);
            SmithHelper.Edge.setAtk(copy, SmithHelper.Collapse.getAtk(mould));
            SmithNbtUtils.setAgl(copy, SmithHelper.Collapse.getAgl(mould));
            copy = ore.itemRankUp(copy);
        }
        return copy;
    }

    protected ItemStack collapseWeapon(ItemStack mould, ItemStack stack, int offset) {
        SmithHelper.Collapse.setAtk(mould, Math.max(SmithHelper.getDamageAtk(stack) / offset, 0));
        SmithHelper.Collapse.setAgl(mould, Math.max(SmithNbtUtils.getAgl(stack) / offset, 0));
        return mould;
    }
}
