package com.marisa.swordcraftstory.item.repair;

import com.marisa.swordcraftstory.group.StoryGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
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

    private static final FoodProperties BASE = new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build();

    public RepairItem(int amount) {
        super(new Properties().food(BASE).tab(StoryGroup.MAIN));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("修理").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("修理量：" + this.amount).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.IRON_GOLEM_REPAIR;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player) {
//            NonNullList<ItemStack> stacks = player.inventory.mainInventory;
//            for (ItemStack stack : stacks) {
//                if (stack.getItem() instanceof Weapon) {
//                    int cost = this.amount;
//                    Weapon weapon = (Weapon) stack.getItem();
//                    if (weapon.isBroken(stack)) {
//                        stack.removeChildTag("story_combat_broken");
//                        if (cost > stack.getMaxDamage()) {
//                            stack.setDamage(0);
//                            CombatPropertiesUtils.setDur(stack, Math.min(cost - stack.getMaxDamage(), weapon.getDurMaxAfterEffect(stack)));
//                        } else {
//                            stack.setDamage(stack.getMaxDamage() - cost);
//                        }
//                    } else {
//                        int stackDamage = stack.getDamage();
//                        int durMaxAfterEffect = weapon.getDurMaxAfterEffect(stack);
//                        if (stackDamage == 0 && weapon.getDur(stack) == durMaxAfterEffect) {
//                            continue;
//                        }
//                        if (cost > stackDamage) {
//                            stack.setDamage(0);
//                            CombatPropertiesUtils.setDur(stack, Math.min(cost - stackDamage, durMaxAfterEffect));
//                        } else {
//                            stack.setDamage(stackDamage - cost);
//                        }
//                    }
//                    break;
//                }
//            }
        }
        return super.finishUsingItem(itemStack, worldIn, entityLiving);
    }
}
