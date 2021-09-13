package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.combat.WeaponType;
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
 * 铁矿石
 */

public class IronOre extends OreItem {

    public IronOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("容易加工是锻造练习最好的材料").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(WeaponType type) {
        switch (type) {
            case BOW:
                return ItemRegistry.NOVICE_BOW.get().getDefaultInstance();
            case SWORD:
                return ItemRegistry.NOVICE_SWORD.get().getDefaultInstance();
            default:
                return null;
        }
    }

    @Override
    public int rank() {
        return 1;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 4;
            case SWORD:
                return 7;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 1;
            case SWORD:
                return 3;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
                return -5;
            case SWORD:
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 50;
            case SWORD:
                return 64;
            default:
                return 0;
        }
    }
}
