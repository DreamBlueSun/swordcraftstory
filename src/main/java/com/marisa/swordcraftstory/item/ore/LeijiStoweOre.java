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
 * 雷齐斯托原石
 */

public class LeijiStoweOre extends AbstractOre {

    public LeijiStoweOre() {
        super(new Properties().group(StoryGroup.COMBAT_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("十分坚硬可以做出专门破坏武器的武器").mergeStyle(TextFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.LEIJI_STOWE_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.LEIJI_STOWE_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.LEIJI_STOWE_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.LEIJI_STOWE_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.LEIJI_STOWE_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.LEIJI_STOWE_AXE.get();
    }

    @Override
    public int rank() {
        return 7;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
            case SWORD:
                return 79;
            case AXE:
                return 101;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 22;
            case SWORD:
                return 56;
            case AXE:
                return 68;
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
                return 101;
            case SWORD:
                return 111;
            case AXE:
                return 126;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.LEIJI_STOWE_ORE.getId(), Intensifys.LEIJI_STOWE_ORE.getShow(), 15, 15, -10, 0);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.setTagInfo("story_combat_model_change", IntNBT.valueOf(WeaponModels.LEIJI_STOWE_ORE.getId()));
        return super.build(stack);
    }
}
