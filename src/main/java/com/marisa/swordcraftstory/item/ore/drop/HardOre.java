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
 * 加德英矿石
 */

public class HardOre extends AbstractOre {

    public HardOre() {
        super(new Properties().tab(StoryGroup.MAIN), 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("比普通的矿石更硬").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.HARD_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 14;
            }
            case AXE -> {
                return 18;
            }
            case PICKAXE -> {
                return 16;
            }
            case RANGED_WEAPON -> {
                return 12;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 2 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 65;
            }
            case AXE -> {
                return 80;
            }
            case PICKAXE -> {
                return 92;
            }
            case RANGED_WEAPON -> {
                return 55;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.HARD_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 0;
    }

    @Override
    public int strengthenDef() {
        return 2;
    }

    @Override
    public int strengthenPhy() {
        return 0;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }

    @Override
    public int strengthenDur() {
        return 10;
    }
}
