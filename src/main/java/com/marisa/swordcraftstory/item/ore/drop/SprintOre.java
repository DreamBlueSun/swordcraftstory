package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
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
 * 斯普林特矿石
 */

public class SprintOre extends AbstractOre {

    public SprintOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("非常稀少的矿石很难入手").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 6;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{67, -5};
        } else if (item instanceof AxeItem) {
            return new int[]{85, -20};
        } else if (item instanceof PickaxeItem) {
            return new int[]{76, -15};
        } else if (StoryUtils.isRangedWeapon(item)) {
            return new int[]{67, -10};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{10, 2};
        } else {
            return null;
        }
    }

    @Override
    public int strengthenId() {
        return 0;
    }

    @Override
    public int strengthenAtk() {
        return 15;
    }

    @Override
    public int strengthenDef() {
        return 6;
    }

    @Override
    public int strengthenPhy() {
        return 0;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }
}
