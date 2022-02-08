package com.marisa.swordcraftstory.item.repair;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.marisa.swordcraftstory.util.StoryUtils;
import com.marisa.swordcraftstory.sound.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 修理道具公共类
 */

public abstract class RepairItem extends Item {

    private final int amount;

    public RepairItem(int amount) {
        super(new Properties().tab(StoryGroup.MAIN));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("修理").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("修理量：" + this.amount).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 40;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entityLiving) {
        if (entityLiving instanceof Player player) {
            int repair = this.amount;
            NonNullList<ItemStack> stacks = player.getInventory().items;
            for (ItemStack stack : stacks) {
                if (!StoryUtils.isWeapon(stack.getItem())) continue;
                int durDamage = SmithHelper.getDurMax(stack) - SmithHelper.getDur(stack);
                if (durDamage <= 0) continue;
                SmithHelper.plusDur(stack, repair);
                if ((repair -= durDamage) <= 0) break;
            }
            level.playSound(null, player, SoundRegistry.SMITH_ITEM_REPAIR.get(), SoundSource.NEUTRAL, 1.0F, 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
        }
        itemStack.shrink(1);
        return super.finishUsingItem(itemStack, level, entityLiving);
    }
}
