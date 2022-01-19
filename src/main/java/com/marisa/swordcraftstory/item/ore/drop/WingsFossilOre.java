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
 * 翅膀化石
 */

public class WingsFossilOre extends AbstractOre {

    public WingsFossilOre() {
        super(new Properties().tab(StoryGroup.MAIN), 2);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("使武器轻量化的素材").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.WINGS_FOSSIL_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 22;
            }
            case AXE -> {
                return 31;
            }
            case PICKAXE -> {
                return 27;
            }
            case RANGED_WEAPON -> {
                return 25;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return 0;
    }

    @Override
    public int makePhy(Item item) {
        return item instanceof ArmorItem ? 4 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 72;
            }
            case AXE -> {
                return 87;
            }
            case PICKAXE -> {
                return 101;
            }
            case RANGED_WEAPON -> {
                return 62;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.WINGS_FOSSIL_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 0;
    }

    @Override
    public int strengthenDef() {
        return 0;
    }

    @Override
    public int strengthenPhy() {
        return 4;
    }

    @Override
    public int strengthenAgl() {
        return 5;
    }

    @Override
    public int strengthenDur() {
        return -5;
    }
}
