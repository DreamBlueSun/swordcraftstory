package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.EStrengthen;
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
 * 料理矿
 */

public class CuisineOre extends AbstractOre {

    public CuisineOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("被称为传说中料理武器的材料").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 5;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{56, 0};
        } else if (item instanceof AxeItem) {
            return new int[]{72, -15};
        } else if (item instanceof PickaxeItem) {
            return new int[]{66, -10};
        } else if (StoryUtils.isRangedWeapon(item)) {
            return new int[]{56, -5};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{8, 1};
        } else {
            return null;
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.CUISINE_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 10;
    }

    @Override
    public int strengthenDef() {
        return 2;
    }

    @Override
    public int strengthenPhy() {
        return 2;
    }

    @Override
    public int strengthenAgl() {
        return 2;
    }
}
