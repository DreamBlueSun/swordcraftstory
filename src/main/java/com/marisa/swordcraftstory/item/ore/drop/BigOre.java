package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
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
 * 大矿石
 */

public class BigOre extends AbstractOre {

    public BigOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("非常大的矿石看来可以做出大型武器").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 2;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{21, 0};
        } else if (item instanceof AxeItem || item instanceof PickaxeItem) {
            return new int[]{29, -30};
        } else if (SmithNbtUtils.isRangedWeapon(item)) {
            return new int[]{23, -20};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{3, 0};
        } else {
            return null;
        }
    }
}
