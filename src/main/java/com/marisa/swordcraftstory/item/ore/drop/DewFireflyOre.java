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
 * 露萤密石
 */

public class DewFireflyOre extends AbstractOre {

    public DewFireflyOre() {
        super(new Properties().tab(StoryGroup.MAIN), 8);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("像宝石一般散发光彩的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.DEW_FIREFLY_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 84;
            }
            case AXE -> {
                return 108;
            }
            case PICKAXE -> {
                return 96;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 6 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return StoryUtils.isArmor(item) ? 10 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 115;
            }
            case AXE -> {
                return 130;
            }
            case PICKAXE -> {
                return 140;
            }
            case RANGED_WEAPON -> {
                return 105;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.DEW_FIREFLY_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 32;
    }

    @Override
    public int strengthenDef() {
        return 6;
    }

    @Override
    public int strengthenPhy() {
        return 10;
    }

    @Override
    public int strengthenAgl() {
        return -5;
    }

    @Override
    public int strengthenDur() {
        return 30;
    }
}
