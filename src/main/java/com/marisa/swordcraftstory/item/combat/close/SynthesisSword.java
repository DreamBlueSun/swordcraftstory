package com.marisa.swordcraftstory.item.combat.close;

import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.combat.CloseCombat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 合成剑
 * @date: 2021/9/1 0001 22:27
 */

public class SynthesisSword extends CloseCombat {

    public SynthesisSword() {
        super(2, 24, 17, 0, 0, new IItemTier() {
            @Override
            public int getMaxUses() {
                return 74;
            }

            @Override
            public float getEfficiency() {
                return 10.0F;
            }

            @Override
            public float getAttackDamage() {
                return -1.0F;
            }

            @Override
            public int getHarvestLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 26;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromItems(ItemRegistry.SYNTHESIS_SWORD.get());
            }
        });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipSynthesis(tooltip);
    }
}
