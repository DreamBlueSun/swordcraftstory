package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.EStrengthen;
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
 * 光辉之石
 */

public class LuminosityOre extends AbstractOre {

    public LuminosityOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("会发光的不可思议的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 9;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{100, 5};
        } else if (item instanceof AxeItem) {
            return new int[]{130, -10};
        } else if (item instanceof PickaxeItem) {
            return new int[]{115, -5};
        } else if (SmithNbtUtils.isRangedWeapon(item)) {
            return new int[]{100, 0};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{18, 4};
        } else {
            return null;
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.LUMINOSITY_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 45;
    }

    @Override
    public int strengthenDef() {
        return 12;
    }

    @Override
    public int strengthenPhy() {
        return 4;
    }

    @Override
    public int strengthenAgl() {
        return 10;
    }
}
