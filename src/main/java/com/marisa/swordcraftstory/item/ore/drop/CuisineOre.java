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
 * 料理矿
 */

public class CuisineOre extends AbstractOre {

    public CuisineOre() {
        super(new Properties().tab(StoryGroup.MAIN), 5);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("被称为传说中料理武器的材料").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.CUISINE_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 56;
            }
            case AXE -> {
                return 72;
            }
            case PICKAXE -> {
                return 66;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return item instanceof ArmorItem ? 5 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return item instanceof ArmorItem ? 5 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 96;
            }
            case AXE -> {
                return 111;
            }
            case PICKAXE -> {
                return 123;
            }
            case RANGED_WEAPON -> {
                return 86;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.CUISINE_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 10;
    }

    @Override
    public int strengthenDef() {
        return 5;
    }

    @Override
    public int strengthenPhy() {
        return 5;
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
