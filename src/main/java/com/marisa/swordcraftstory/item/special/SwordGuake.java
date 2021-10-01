package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 初心者匕首-挂科专属皮肤
 */

public class SwordGuake extends AbstractSwordWeapon {

    public SwordGuake() {
        super(new IronOre());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipNovice(tooltip);
        tooltip.add(new TranslationTextComponent("挂科仙客没有抽到的心愿武器").mergeStyle(TextFormatting.DARK_PURPLE));
    }
}
