package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
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
 * 尤佳矿石
 */

public class UkaOre extends OreItem {

    public UkaOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("经常被当作高价的装饰使用").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(WeaponType type) {
        switch (type) {
            case BOW:
                return ItemRegistry.ELEGANCE_BOW.get().getDefaultInstance();
            case SWORD:
                return ItemRegistry.ELEGANCE_SWORD.get().getDefaultInstance();
            default:
                return null;
        }
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 39;
            case SWORD:
                return 37;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 10;
            case SWORD:
                return 28;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
                return -20;
            case SWORD:
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 71;
            case SWORD:
                return 82;
            default:
                return 0;
        }
    }
}
