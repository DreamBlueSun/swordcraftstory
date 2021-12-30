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
 * 雷齐斯托原石
 */

public class LeijiStoweOre extends AbstractOre {

    public LeijiStoweOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("十分坚硬可以做出专门破坏武器的武器").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 7;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem || item instanceof PickaxeItem) {
            return new int[]{79, -10};
        } else if (item instanceof AxeItem || item instanceof HoeItem || item instanceof ShovelItem) {
            return new int[]{101, -30};
        } else if (item instanceof ProjectileWeaponItem) {
            return new int[]{79, -20};
        } else {
            return null;
        }
    }
}
