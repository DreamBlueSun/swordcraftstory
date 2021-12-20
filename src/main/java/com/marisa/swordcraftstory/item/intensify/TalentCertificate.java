package com.marisa.swordcraftstory.item.intensify;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.intensify.helper.AbstractIntensify;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 达人之证
 */

public class TalentCertificate extends AbstractIntensify {

    public TalentCertificate() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("被当作达人的人才能获得的证明").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.TALENT_CERTIFICATE.getId(), Intensifys.TALENT_CERTIFICATE.getShow(), 5, 5, 5, 0);
    }
}
