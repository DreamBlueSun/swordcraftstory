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
 * 雷吉亚矿
 */

public class RegiaOre extends AbstractOre {

    public RegiaOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("加工瞬间会有自然之风扑面的感觉").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    protected int[] rankAttr(Item item) {
        if (item instanceof SwordItem) {
            return new int[]{35, 0};
        } else if (item instanceof AxeItem) {
            return new int[]{45, -15};
        } else if (item instanceof PickaxeItem) {
            return new int[]{40, -10};
        } else if (StoryUtils.isRangedWeapon(item)) {
            return new int[]{35, -5};
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
        return EStrengthen.REGIA_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 6;
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
        return 0;
    }
}
