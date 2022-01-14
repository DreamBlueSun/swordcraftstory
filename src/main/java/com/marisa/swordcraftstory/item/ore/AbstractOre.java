package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.smith.IStrengthen;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import com.marisa.swordcraftstory.smith.util.StrengthenHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 抽象矿石类
 */

public abstract class AbstractOre extends Item implements IStrengthen {

    public AbstractOre(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("矿石").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(rank())).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    public abstract int rank();

    /**
     * 不同矿石返回其等阶对应的属性数组{ATK,AGL}
     */
    protected abstract int[] rankAttr(Item item);

    /**
     * 不同矿石返回其等阶对应的属性数组{DEF,PHY}
     */
    protected abstract int[] rankAttrArmor(Item item);

    public ItemStack itemRankUp(ItemStack stack) {
        ItemStack copy = stack.copy();
        if (copy.getItem() instanceof ArmorItem) {
            int[] attr = rankAttrArmor(copy.getItem());
            if (attr != null) {
                SmithNbtUtils.setRank(copy, rank());
                SmithNbtUtils.setRankAttrArmor(copy, attr);
                return copy;
            }
        } else {
            int[] attr = rankAttr(copy.getItem());
            if (attr != null) {
                SmithNbtUtils.setRank(copy, rank());
                SmithNbtUtils.setRankAttr(copy, attr);
                return copy;
            }
        }
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public ItemStack doStrengthen(ItemStack stack) {
        ItemStack copy = stack.copy();
        int[] ints = StrengthenHelper.getStrengthenIds(copy);
        if (ints == null) {
            StrengthenHelper.setStrengthen(copy, new int[]{this.strengthenId()});
        } else {
            int[] intsNew = new int[ints.length + 1];
            System.arraycopy(ints, 0, intsNew, 0, ints.length);
            intsNew[ints.length] = this.strengthenId();
            StrengthenHelper.setStrengthen(copy, intsNew);
        }
        return copy;
    }
}
