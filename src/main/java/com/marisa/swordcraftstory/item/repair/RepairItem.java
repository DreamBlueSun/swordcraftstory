package com.marisa.swordcraftstory.item.repair;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 修理道具公共类
 */

public abstract class RepairItem extends Item {

    private final int amount;

    private static final Food BASE = (new Food.Builder()).hunger(0).saturation(0).setAlwaysEdible().build();

    public RepairItem(int amount) {
        super(new Properties().food(BASE).group(StoryGroup.COMBAT_GROUP));
        this.amount = amount;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("修理").mergeStyle(TextFormatting.LIGHT_PURPLE));
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
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_REPAIR;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity) {
            NonNullList<ItemStack> stacks = ((PlayerEntity) entityLiving).inventory.mainInventory;
            for (ItemStack stack : stacks) {
                if (stack.getItem() instanceof Weapon) {
                    int cost = this.amount;
                    Weapon weapon = (Weapon) stack.getItem();
                    if (weapon.isBroken(stack)) {
                        stack.removeChildTag("story_combat_broken");
                        if (cost > stack.getMaxDamage()) {
                            stack.setDamage(0);
                            CombatPropertiesUtils.setDur(stack, Math.min(cost - stack.getMaxDamage(), weapon.getDurMaxAfterEffect(stack)));
                        } else {
                            stack.setDamage(stack.getMaxDamage() - cost);
                        }
                    } else {
                        int stackDamage = stack.getDamage();
                        int durMaxAfterEffect = weapon.getDurMaxAfterEffect(stack);
                        if (stackDamage == 0 && weapon.getDur(stack) == durMaxAfterEffect) {
                            continue;
                        }
                        if (cost > stackDamage) {
                            stack.setDamage(0);
                            CombatPropertiesUtils.setDur(stack, Math.min(cost - stackDamage, durMaxAfterEffect));
                        } else {
                            stack.setDamage(stackDamage - cost);
                        }
                    }
                    break;
                }
            }
        }
        return super.onItemUseFinish(itemStack, worldIn, entityLiving);
    }
}
