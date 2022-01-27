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
 * 尤佳矿石
 */

public class UkaOre extends AbstractOre {

    public UkaOre() {
        super(new Properties().tab(StoryGroup.MAIN), 3);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("经常被当作高价的装饰使用").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public int makeId() {
        return EMake.UKA_ORE.getId();
    }

    @Override
    public int makeAtk(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 37;
            }
            case AXE -> {
                return 47;
            }
            case PICKAXE -> {
                return 42;
            }
            case RANGED_WEAPON -> {
                return 39;
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
        return StoryUtils.isArmor(item) ? 5 : 0;
    }

    @Override
    public int makeAgl(Item item) {
        return super.aglType1(item);
    }

    @Override
    public int makeDur(Item item) {
        switch (EMakeType.getByItem(item)) {
            case SWORD -> {
                return 82;
            }
            case AXE -> {
                return 96;
            }
            case PICKAXE -> {
                return 108;
            }
            case RANGED_WEAPON -> {
                return 71;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public int strengthenId() {
        return EStrengthen.UKA_ORE.getId();
    }

    @Override
    public int strengthenAtk() {
        return 10;
    }

    @Override
    public int strengthenDef() {
        return 1;
    }

    @Override
    public int strengthenPhy() {
        return 5;
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
