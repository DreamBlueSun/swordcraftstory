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
 * @description: 艾尔弗伊斯原石
 * @date: 2021/9/4 0004 13:13
 */

public class ElphusOre extends OreItem {

    public ElphusOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("敲击之后会发出好听的声音").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(CombatType type) {
        switch (type) {
            case BOW:
                return ItemRegistry.SYNTHESIS_BOW.get().getDefaultInstance();
            case SWORD:
                return ItemRegistry.SYNTHESIS_SWORD.get().getDefaultInstance();
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
                return 21;
            case SWORD:
                return 24;
            default:
                return 0;
        }
    }

    @Override
    public int def(CombatType type) {
        switch (type) {
            case BOW:
                return 6;
            case SWORD:
                return 17;
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
                return 61;
            case SWORD:
                return 74;
            default:
                return 0;
        }
    }
}
