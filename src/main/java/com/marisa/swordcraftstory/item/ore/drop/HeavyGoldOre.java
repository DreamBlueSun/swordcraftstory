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
 * 重金矿
 */

public class HeavyGoldOre extends AbstractOre {

    public HeavyGoldOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("很重加工困难的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{30, 0};
        } else if (item instanceof AxeItem) {
            return new int[]{40, -15};
        } else if (item instanceof PickaxeItem) {
            return new int[]{35, -10};
        } else if (SmithNbtUtils.isRangedWeapon(item)) {
            return new int[]{30, -5};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{4, 0};
        } else {
            return null;
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.HEAVY_GOLD_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 7;
    }

    @Override
    public int strengthenDef() {
        return 1;
    }

    @Override
    public int strengthenPhy() {
        return 2;
    }

    @Override
    public int strengthenAgl() {
        return -7;
    }
}
