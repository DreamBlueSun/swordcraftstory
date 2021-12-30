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
 * 大铁矿石
 */

public class BigIronOre extends AbstractOre {

    public BigIronOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("非常大的铁矿石块").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem || item instanceof PickaxeItem) {
            return new int[]{32, -10};
        } else if (item instanceof AxeItem || item instanceof HoeItem || item instanceof ShovelItem) {
            return new int[]{38, -30};
        } else if (item instanceof ProjectileWeaponItem) {
            return new int[]{32, -20};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{4, 1};
        } else {
            return null;
        }
    }
}
