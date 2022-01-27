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
 * 雷齐斯托原石
 */

public class LeijiStoweOre extends AbstractOre {

    public LeijiStoweOre() {
        super(new Properties().tab(StoryGroup.MAIN), 7);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("十分坚硬可以做出专门破坏武器的武器").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.LEIJI_STOWE_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 79;
            }
            case AXE -> {
                return 101;
            }
            case PICKAXE -> {
                return 90;
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
        return StoryUtils.isArmor(item) ? 4 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType3(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 111;
            }
            case AXE -> {
                return 126;
            }
            case PICKAXE -> {
                return 136;
            }
            case RANGED_WEAPON -> {
                return 101;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.LEIJI_STOWE_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 36;
    }

    @Override
    public int strengthenDef() {
        return 10;
    }

    @Override
    public int strengthenPhy() {
        return 4;
    }

    @Override
    public int strengthenAgl() {
        return -10;
    }

    @Override
    public int strengthenDur() {
        return 30;
    }
}
