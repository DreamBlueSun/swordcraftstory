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
 * 大铁矿石
 */

public class BigIronOre extends AbstractOre {

    public BigIronOre() {
        super(new Properties().tab(StoryGroup.MAIN), 3);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("非常大的铁矿石块").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.BIG_IRON_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 32;
            }
            case AXE -> {
                return 38;
            }
            case PICKAXE -> {
                return 35;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return item instanceof ArmorItem ? 6 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType3(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 77;
            }
            case AXE -> {
                return 91;
            }
            case PICKAXE -> {
                return 107;
            }
            case RANGED_WEAPON -> {
                return 66;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.BIG_IRON_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 12;
    }

    @Override
    public int strengthenDef() {
        return 6;
    }

    @Override
    public int strengthenPhy() {
        return 0;
    }

    @Override
    public int strengthenAgl() {
        return -3;
    }

    @Override
    public int strengthenDur() {
        return 15;
    }
}
