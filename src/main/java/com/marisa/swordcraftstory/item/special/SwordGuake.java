package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
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
 * 初心者匕首-挂科专属皮肤
 */

public class SwordGuake extends AbstractSwordWeapon {

    public SwordGuake() {
        super(new IronOre(), WeaponSkills.UNKNOWN.getId());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipNovice(tooltip);
        tooltip.add(new TranslatableComponent("挂科仙客没有抽到的心愿武器").withStyle(ChatFormatting.DARK_PURPLE));

        tooltip.add(new TranslatableComponent("大概率会在以后的版本移除").withStyle(ChatFormatting.GREEN));
    }
}
