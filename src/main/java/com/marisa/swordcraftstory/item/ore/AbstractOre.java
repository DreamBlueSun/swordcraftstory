package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.smith.IMake;
import com.marisa.swordcraftstory.smith.IStrengthen;
import com.marisa.swordcraftstory.smith.EMakeType;
import com.marisa.swordcraftstory.smith.util.MakeHelper;
import com.marisa.swordcraftstory.smith.util.StrengthenHelper;
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
 * 抽象矿石类
 */

public abstract class AbstractOre extends Item implements IMake, IStrengthen {

    private final int rank;

    public AbstractOre(Properties properties, int rank) {
        super(properties);
        this.rank = rank;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("矿石").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(this.rank)).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int makeRank() {
        return this.rank;
    }

    @Override
    public void doMake(ItemStack stack) {
        MakeHelper.setMake(stack, makeId());
    }

    @Override
    public ItemStack doStrengthen(ItemStack stack) {
        ItemStack copy = stack.copy();
        int[] ints = StrengthenHelper.getStrengthenIds(copy);
        if (ints == null) {
            StrengthenHelper.setStrengthen(copy, new int[]{this.strengthenId()});
        } else {
            int[] intsNew = new int[ints.length + 1];
            System.arraycopy(ints, 0, intsNew, 0, ints.length);
            intsNew[ints.length] = this.strengthenId();
            StrengthenHelper.setStrengthen(copy, intsNew);
        }
        return copy;
    }

    protected int aglType1(Item item) {
        switch (EMakeType.getByItem(item)) {
            case AXE -> {
                return -20;
            }
            case PICKAXE -> {
                return -15;
            }
            case RANGED_WEAPON -> {
                return -10;
            }
            default -> {
                return 0;
            }
        }
    }

    protected int aglType2(Item item) {
        switch (EMakeType.getByItem(item)) {
            case AXE -> {
                return -15;
            }
            case PICKAXE -> {
                return -10;
            }
            case RANGED_WEAPON -> {
                return -5;
            }
            default -> {
                return 0;
            }
        }
    }

    protected int aglType3(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return -5;
            }
            case AXE -> {
                return -20;
            }
            case PICKAXE -> {
                return -15;
            }
            case RANGED_WEAPON -> {
                return -10;
            }
            default -> {
                return 0;
            }
        }
    }
}
