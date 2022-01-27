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
 * 幻想矿
 */

public class FantasyOre extends AbstractOre {

    public FantasyOre() {
        super(new Properties().tab(StoryGroup.MAIN), 4);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("散发着奇异光辉的矿石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.FANTASY_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD, RANGED_WEAPON -> {
                return 46;
            }
            case AXE -> {
                return 58;
            }
            case PICKAXE -> {
                return 52;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int makeDef(Item item) {
        return StoryUtils.isArmor(item) ? 1 : 0;
    }

    @Override
    public int makePhy(Item item) {
        return StoryUtils.isArmor(item) ? 7 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType2(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 87;
            }
            case AXE -> {
                return 102;
            }
            case PICKAXE -> {
                return 114;
            }
            case RANGED_WEAPON -> {
                return 77;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.FANTASY_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 0;
    }

    @Override
    public int strengthenDef() {
        return 1;
    }

    @Override
    public int strengthenPhy() {
        return 7;
    }

    @Override
    public int strengthenAgl() {
        return 0;
    }

    @Override
    public int strengthenDur() {
        return 40;
    }
}
