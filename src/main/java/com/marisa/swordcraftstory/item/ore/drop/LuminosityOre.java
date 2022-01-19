package com.marisa.swordcraftstory.item.ore.drop;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.smith.util.EMake;
import com.marisa.swordcraftstory.smith.util.EMakeType;
import com.marisa.swordcraftstory.smith.util.EStrengthen;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ArmorItem;
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
 * 光辉之石
 */

public class LuminosityOre extends AbstractOre {

    public LuminosityOre() {
        super(new Properties().tab(StoryGroup.MAIN), 9);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("会发光的不可思议的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.LUMINOSITY_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 100;
            }
            case AXE -> {
                return 130;
            }
            case PICKAXE -> {
                return 115;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return item instanceof ArmorItem ? 12 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return item instanceof ArmorItem ? 12 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 5;
            }
            case AXE -> {
                return -10;
            }
            case PICKAXE -> {
                return -5;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 135;
            }
            case AXE -> {
                return 145;
            }
            case PICKAXE -> {
                return 155;
            }
            case RANGED_WEAPON -> {
                return 125;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.LUMINOSITY_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 42;
    }

    @Override
    public int strengthenDef() {
        return 12;
    }

    @Override
    public int strengthenPhy() {
        return 12;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }

    @Override
    public int strengthenDur() {
        return 15;
    }
}
