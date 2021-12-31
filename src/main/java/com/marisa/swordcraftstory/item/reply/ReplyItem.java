package com.marisa.swordcraftstory.item.reply;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
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
 * 回复道具公共类
 */

public abstract class ReplyItem extends Item {

    private final float amount;

    private static final FoodProperties BASE = (new FoodProperties.Builder()).nutrition(0).saturationMod(0).alwaysEat().build();

    public ReplyItem(float amount) {
        super(new Item.Properties().food(BASE).tab(StoryGroup.MAIN));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        toolTip.add(new TextComponent("回复").withStyle(ChatFormatting.LIGHT_PURPLE));
        toolTip.add(new TextComponent("回复量：" + this.amount).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 40;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, LivingEntity entity) {
        entity.heal(this.amount);
        return super.finishUsingItem(stack, level, entity);
    }
}
