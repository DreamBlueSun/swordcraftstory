package com.marisa.swordcraftstory.item.reply;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 回复道具公共类
 * @date: 2021/9/6 0006 21:41
 */

public abstract class ReplyItem extends Item {

    private final float amount;

    private static final Food BASE = (new Food.Builder()).hunger(0).saturation(0).setAlwaysEdible().build();

    public ReplyItem(float amount) {
        super(new Properties().food(BASE).group(StoryGroup.COMBAT_GROUP));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("回复").mergeStyle(TextFormatting.LIGHT_PURPLE));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.heal(this.amount);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
