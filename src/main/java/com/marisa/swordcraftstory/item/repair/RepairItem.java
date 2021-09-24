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
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity) {
            NonNullList<ItemStack> stacks = ((PlayerEntity) entityLiving).inventory.mainInventory;
            for (ItemStack itemStack : stacks) {
                Item item = itemStack.getItem();
                if (item instanceof Weapon) {
                    int damage = itemStack.getDamage();
                    int dur = ((Weapon) item).getDur(itemStack);
                    int durMax = ((Weapon) item).getDurMax(itemStack);
                    if (damage == 0 && dur == durMax) {
                        continue;
                    }
                    int cost = this.amount;
                    if (cost > damage) {
                        cost -= damage;
                        itemStack.setDamage(0);
                        dur += cost;
                        if (dur > durMax) {
                            dur = durMax;
                        }
                        CombatPropertiesUtils.setDur(itemStack, dur);
                    } else if (cost == damage) {
                        itemStack.setDamage(0);
                    } else {
                        itemStack.setDamage(damage - cost);
                    }
                    stack.removeChildTag("story_combat_broken");
                    break;
                }
            }
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
