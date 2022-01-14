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
 * 斩铁矿
 */

public class CutIronOre extends AbstractOre {

    public CutIronOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("品质上乘但较难加工的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{28, 0};
        } else if (item instanceof AxeItem) {
            return new int[]{36, -20};
        } else if (item instanceof PickaxeItem) {
            return new int[]{32, -15};
        } else if (SmithNbtUtils.isRangedWeapon(item)) {
            return new int[]{28, -10};
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

    @Override
    public int strengthenId() {
        return EStrengthen.CUT_IRON_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 7;
    }

    @Override
    public int strengthenDef() {
        return 0;
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
