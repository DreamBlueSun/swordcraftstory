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
 * 双蛇矿石
 */

public class DoubleSnakeOre extends AbstractOre {

    public DoubleSnakeOre() {
        super(new Properties().tab(StoryGroup.MAIN), 6);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("矿石中不知为何浮现出蛇的模样").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.DOUBLE_SNAKE_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 65;
            }
            case AXE -> {
                return 83;
            }
            case PICKAXE -> {
                return 74;
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
        return item instanceof ArmorItem ? 6 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 5;
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

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 101;
            }
            case AXE -> {
                return 116;
            }
            case PICKAXE -> {
                return 127;
            }
            case RANGED_WEAPON -> {
                return 91;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.DOUBLE_SNAKE_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 16;
    }

    @Override
    public int strengthenDef() {
        return 6;
    }

    @Override
    public int strengthenPhy() {
        return 6;
    }

    @Override
    public int strengthenAgl() {
        return 3;
    }

    @Override
    public int strengthenDur() {
        return 10;
    }
}
