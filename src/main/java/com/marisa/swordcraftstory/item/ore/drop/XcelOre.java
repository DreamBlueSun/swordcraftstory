package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.EMake;
import com.marisa.swordcraftstory.smith.EMakeType;
import com.marisa.swordcraftstory.smith.EStrengthen;
import com.marisa.swordcraftstory.util.StoryUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 艾克赛尔矿石
 */

public class XcelOre extends AbstractOre {

    public XcelOre() {
        super(new Properties().tab(StoryGroup.MAIN), 7);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("被锻造师用作试炼的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.XCEL_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 70;
            }
            case AXE -> {
                return 90;
            }
            case PICKAXE -> {
                return 80;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 7 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return StoryUtils.isArmor(item) ? 7 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 105;
            }
            case AXE -> {
                return 120;
            }
            case PICKAXE -> {
                return 130;
            }
            case RANGED_WEAPON -> {
                return 95;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.XCEL_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 10;
    }

    @Override
    public int strengthenDef() {
        return 7;
    }

    @Override
    public int strengthenPhy() {
        return 7;
    }

    @Override
    public int strengthenAgl() {
        return 5;
    }

    @Override
    public int strengthenDur() {
        return 20;
    }
}
