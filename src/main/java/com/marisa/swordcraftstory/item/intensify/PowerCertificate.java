package com.marisa.swordcraftstory.item.intensify;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.intensify.helper.AbstractIntensify;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
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
 * 怪力之证
 */

public class PowerCertificate extends AbstractIntensify {

    public PowerCertificate() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("拥有怪力之人才能获得的证明").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.POWER_CERTIFICATE.getId(), Intensifys.POWER_CERTIFICATE.getShow(), 5, 0, 0, 0);
    }
}
