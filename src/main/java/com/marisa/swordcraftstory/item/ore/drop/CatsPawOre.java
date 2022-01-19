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
        return item instanceof ArmorItem ? 18 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return item instanceof ArmorItem ? 4 : 0;
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
        return 0;
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
        return 0;
    }

    @Override
    public int strengthenPhy() {
        return 0;
    }

    @Override
    public int strengthenAgl() {
        return 20;
    }
}
