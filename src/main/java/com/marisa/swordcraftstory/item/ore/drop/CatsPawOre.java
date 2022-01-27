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
 * 肉球的化石
 */

public class CatsPawOre extends AbstractOre {

    public CatsPawOre() {
        super(new Properties().tab(StoryGroup.MAIN), 9);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("明明是石头却很柔软手感非常好").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.CATS_PAW_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 90;
            }
            case AXE -> {
                return 100;
            }
            case PICKAXE -> {
                return 95;
            }
            case RANGED_WEAPON -> {
                return 85;
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
        return StoryUtils.isArmor(item) ? 12 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 15;
            }
            case AXE, RANGED_WEAPON -> {
                return 5;
            }
            case PICKAXE -> {
                return 10;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, AXE, PICKAXE, RANGED_WEAPON -> {
                return 100;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.CATS_PAW_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return -10;
    }

    @Override
    public int strengthenDef() {
        return 7;
    }

    @Override
    public int strengthenPhy() {
        return 12;
    }

    @Override
    public int strengthenAgl() {
        return 25;
    }

    @Override
    public int strengthenDur() {
        return -10;
    }
}
