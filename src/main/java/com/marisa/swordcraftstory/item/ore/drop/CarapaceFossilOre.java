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
 * 甲壳化石
 */

public class CarapaceFossilOre extends AbstractOre {

    public CarapaceFossilOre() {
        super(new Properties().tab(StoryGroup.MAIN), 4);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("优质的甲壳变成的化石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.CARAPACE_FOSSIL_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 42;
            }
            case AXE -> {
                return 56;
            }
            case PICKAXE -> {
                return 49;
            }
            case RANGED_WEAPON -> {
                return 44;
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
                return 85;
            }
            case AXE -> {
                return 100;
            }
            case PICKAXE -> {
                return 110;
            }
            case RANGED_WEAPON -> {
                return 75;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.CARAPACE_FOSSIL_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 12;
    }

    @Override
    public int strengthenDef() {
        return 7;
    }

    @Override
    public int strengthenPhy() {
        return 1;
    }

    @Override
    public int strengthenAgl() {
        return 1;
    }

    @Override
    public int strengthenDur() {
        return 5;
    }
}
