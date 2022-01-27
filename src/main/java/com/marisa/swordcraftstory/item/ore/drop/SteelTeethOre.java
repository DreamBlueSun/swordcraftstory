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
 * 钢牙原石
 */

public class SteelTeethOre extends AbstractOre {

    public SteelTeethOre() {
        super(new Properties().tab(StoryGroup.MAIN), 8);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("很坚硬难以加工很多人都无法使用").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.STEEL_TEETH_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 90;
            }
            case AXE -> {
                return 114;
            }
            case PICKAXE -> {
                return 102;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 10 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return StoryUtils.isArmor(item) ? 6 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType3(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 118;
            }
            case AXE -> {
                return 133;
            }
            case PICKAXE -> {
                return 143;
            }
            case RANGED_WEAPON -> {
                return 108;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.STEEL_TEETH_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 32;
    }

    @Override
    public int strengthenDef() {
        return 10;
    }

    @Override
    public int strengthenPhy() {
        return 6;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }

    @Override
    public int strengthenDur() {
        return 0;
    }
}
