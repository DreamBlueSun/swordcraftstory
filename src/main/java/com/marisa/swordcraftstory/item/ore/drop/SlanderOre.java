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
 * 斯兰德原石
 */

public class SlanderOre extends AbstractOre {

    public SlanderOre() {
        super(new Properties().tab(StoryGroup.MAIN), 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("可以做纤细的武器").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.SLANDER_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 9;
            }
            case AXE -> {
                return 11;
            }
            case PICKAXE -> {
                return 10;
            }
            case RANGED_WEAPON -> {
                return 8;
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
        return item instanceof ArmorItem ? 2 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 63;
            }
            case AXE -> {
                return 76;
            }
            case PICKAXE -> {
                return 83;
            }
            case RANGED_WEAPON -> {
                return 51;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.SLANDER_ORE.getId();
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
        return 2;
    }

    @Override
    public int strengthenAgl() {
        return 2;
    }

    @Override
    public int strengthenDur() {
        return 0;
    }
}
