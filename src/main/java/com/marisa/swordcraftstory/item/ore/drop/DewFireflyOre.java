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
 * 露萤密石
 */

public class DewFireflyOre extends AbstractOre {

    public DewFireflyOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("像宝石一般散发光彩的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 8;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{84, 0};
        } else if (item instanceof AxeItem) {
            return new int[]{108, -15};
        } else if (item instanceof PickaxeItem) {
            return new int[]{96, -10};
        } else if (SmithNbtUtils.isRangedWeapon(item)) {
            return new int[]{84, -5};
        } else {
            return null;
        }
    }

    @Override
    protected int[] rankAttrArmor(Item item) {
        if (item instanceof ArmorItem) {
            return new int[]{15, 3};
        } else {
            return null;
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.DEW_FIREFLY_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 20;
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
        return -5;
    }
}
