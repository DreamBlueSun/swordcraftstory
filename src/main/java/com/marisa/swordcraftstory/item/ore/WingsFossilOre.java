package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.combat.CombatType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @description: 翅膀化石
 * @date: 2021/9/9 0009 22:59
 */

public class WingsFossilOre extends OreItem {

    public WingsFossilOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("使武器轻量化的素材").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(CombatType type) {
        switch (type) {
            case BOW:
                return ItemRegistry.FEATHER_BOW.get().getDefaultInstance();
            case SWORD:
                return ItemRegistry.FEATHER_SWORD.get().getDefaultInstance();
            default:
                return null;
        }
    }

    @Override
    public int rank() {
        return 2;
    }

    @Override
    public int atk(CombatType type) {
        switch (type) {
            case BOW:
                return 25;
            case SWORD:
                return 22;
            default:
                return 0;
        }
    }

    @Override
    public int def(CombatType type) {
        switch (type) {
            case BOW:
                return 7;
            case SWORD:
                return 16;
            default:
                return 0;
        }
    }

    @Override
    public int agl(CombatType type) {
        switch (type) {
            case BOW:
                return -5;
            case SWORD:
            default:
                return 0;
        }
    }

    @Override
    public int dur(CombatType type) {
        switch (type) {
            case BOW:
                return 62;
            case SWORD:
                return 72;
            default:
                return 0;
        }
    }
}
