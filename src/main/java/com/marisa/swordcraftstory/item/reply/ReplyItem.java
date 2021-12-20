package com.marisa.swordcraftstory.item.reply;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 回复道具公共类
 */

public abstract class ReplyItem extends Item {

    private final float amount;

    private static final Food BASE = (new Food.Builder()).hunger(0).saturation(0).setAlwaysEdible().build();

    public ReplyItem(float amount) {
        super(new Properties().food(BASE).tab(StoryGroup.MAIN));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("回复").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("回复量：" + this.amount).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.heal(this.amount);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
