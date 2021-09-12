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
 * @description: 漂亮原石
 * @date: 2021/9/4 0004 13:13
 */

public class PrettyOre extends OreItem {

    public PrettyOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("外观深受女性的青睐").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(CombatType type) {
        switch (type) {
            case BOW:
                return ItemRegistry.RIBBON_BOW.get().getDefaultInstance();
            case SWORD:
                return ItemRegistry.RIBBON_SWORD.get().getDefaultInstance();
            default:
                return null;
        }
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    public int atk(CombatType type) {
        switch (type) {
            case BOW:
                return 37;
            case SWORD:
                return 39;
            default:
                return 0;
        }
    }

    @Override
    public int def(CombatType type) {
        switch (type) {
            case BOW:
                return 11;
            case SWORD:
                return 26;
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
                return 72;
            case SWORD:
                return 84;
            default:
                return 0;
        }
    }
}
