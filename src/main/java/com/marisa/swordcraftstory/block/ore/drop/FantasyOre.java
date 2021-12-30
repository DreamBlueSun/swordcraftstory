package com.marisa.swordcraftstory.block.ore.drop;

import com.marisa.swordcraftstory.block.ore.AbstractOre;
import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 幻想矿
 */

public class FantasyOre extends AbstractOre {

    public FantasyOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("散发着奇异光辉的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 4;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem || item instanceof PickaxeItem) {
            return new int[]{46, 0};
        } else if (item instanceof AxeItem || item instanceof HoeItem || item instanceof ShovelItem) {
            return new int[]{58, -25};
        } else if (item instanceof ProjectileWeaponItem) {
            return new int[]{46, -5};
        } else {
            return null;
        }
    }
}
