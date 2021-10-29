package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
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
 * 双蛇矿石
 */

public class DoubleSnakeOre extends AbstractOre {

    public DoubleSnakeOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("矿石中不知为何浮现出蛇的模样").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.DOUBLE_SNAKE_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.DOUBLE_SNAKE_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.DOUBLE_SNAKE_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.DOUBLE_SNAKE_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.DOUBLE_SNAKE_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.DOUBLE_SNAKE_AXE.get();
    }

    @Override
    public int rank() {
        return 6;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
            case SWORD:
                return 65;
            case AXE:
                return 83;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 18;
            case SWORD:
                return 46;
            case AXE:
                return 56;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
                return -20;
            case AXE:
                return -30;
            case SWORD:
                return -10;
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 91;
            case SWORD:
                return 101;
            case AXE:
                return 116;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.DOUBLE_SNAKE_ORE.getId(), Intensifys.DOUBLE_SNAKE_ORE.getShow(), 8, 5, -5, 0);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.DOUBLE_SNAKE_ORE.getId()));
        return super.build(stack);
    }
}