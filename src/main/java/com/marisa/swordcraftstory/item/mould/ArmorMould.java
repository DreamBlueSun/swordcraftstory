package com.marisa.swordcraftstory.item.mould;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 盔甲模具
 */

public abstract class ArmorMould extends Mould {

    public ArmorMould() {
        super();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(new TranslatableComponent("护甲").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getDef(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(new TranslatableComponent("韧性").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getPhy(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    @Override
    public ItemStack make(ItemStack stack, ItemStack mould, AbstractOre ore) {
        ItemStack copy = stack.copy();
        if (SmithNbtUtils.getRank(copy) == 0) {
            SmithNbtUtils.remEnchantmentTag(copy);
            SmithNbtUtils.copyEnchantmentTag(mould, copy);
            SmithNbtUtils.setDef(copy, SmithNbtUtils.getDef(copy) + SmithNbtUtils.getDef(mould));
            SmithNbtUtils.setPhy(copy, SmithNbtUtils.getPhy(copy) + SmithNbtUtils.getPhy(mould));
            copy = ore.itemRankUp(copy);
        }
        return copy;
    }

    protected ItemStack collapseArmor(ItemStack mould, ItemStack stack, int offset) {
        ArmorItem armor = (ArmorItem) stack.getItem();
        int def = armor.getDefense();
        def += SmithNbtUtils.getDef(stack);
        int phy = (int) armor.getToughness();
        phy += SmithNbtUtils.getPhy(stack);
        SmithNbtUtils.setDef(mould, Math.max(def / offset, 0));
        SmithNbtUtils.setPhy(mould, Math.max(phy / offset, 0));
        return mould;
    }
}
