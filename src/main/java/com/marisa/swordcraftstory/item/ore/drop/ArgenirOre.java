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
 * 阿吉尼尔矿
 */

public class ArgenirOre extends AbstractOre {

    public ArgenirOre() {
        super(new Properties().tab(StoryGroup.MAIN), 2);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("总觉得是值得信赖的好矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.ARGENIR_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 16;
            }
            case AXE -> {
                return 20;
            }
            case PICKAXE -> {
                return 18;
            }
            case RANGED_WEAPON -> {
                return 14;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 3 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return StoryUtils.isArmor(item) ? 1 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType1(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 67;
            }
            case AXE -> {
                return 81;
            }
            case PICKAXE -> {
                return 96;
            }
            case RANGED_WEAPON -> {
                return 56;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.ARGENIR_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 4;
    }

    @Override
    public int strengthenDef() {
        return 3;
    }

    @Override
    public int strengthenPhy() {
        return 1;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }

    @Override
    public int strengthenDur() {
        return 10;
    }
}
