package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 重金矿
 */

public class HeavyGoldOre extends AbstractOre {

    public HeavyGoldOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("很重加工困难的矿石").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.SEVERE_PENALTY_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.SEVERE_PENALTY_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.SEVERE_PENALTY_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.SEVERE_PENALTY_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.SEVERE_PENALTY_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.SEVERE_PENALTY_AXE.get();
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
            case SWORD:
                return 30;
            case AXE:
                return 40;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 9;
            case SWORD:
                return 21;
            case AXE:
                return 28;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
                return -5;
            case AXE:
                return -25;
            case SWORD:
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 67;
            case SWORD:
                return 76;
            case AXE:
                return 92;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.HEAVY_GOLD_ORE.getId(), Intensifys.HEAVY_GOLD_ORE.getShow(), 7, 2, -7, 0);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.HEAVY_GOLD_ORE.getId()));
        return super.build(stack);
    }
}
