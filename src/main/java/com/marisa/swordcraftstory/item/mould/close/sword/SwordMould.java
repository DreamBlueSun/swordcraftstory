package com.marisa.swordcraftstory.item.mould.close.sword;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import com.marisa.swordcraftstory.item.mould.Mould;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 剑模具
 */

public class SwordMould extends Mould {

    public SwordMould() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("锻造剑类武器的素材").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public WeaponType type() {
        return WeaponType.SWORD;
    }
}
