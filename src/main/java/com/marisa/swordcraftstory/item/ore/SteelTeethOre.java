package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 钢牙原石
 */

public class SteelTeethOre extends AbstractOre {

    public SteelTeethOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("很坚硬难以加工很多人都无法使用").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.STEEL_TEETH_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.STEEL_TEETH_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.STEEL_TEETH_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.STEEL_TEETH_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.STEEL_TEETH_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.STEEL_TEETH_AXE.get();
    }

    @Override
    public int rank() {
        return 8;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
            case SWORD:
                return 90;
            case AXE:
                return 114;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 26;
            case SWORD:
                return 63;
            case AXE:
                return 78;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
                return -10;
            case AXE:
                return -25;
            case SWORD:
                return -5;
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 108;
            case SWORD:
                return 118;
            case AXE:
                return 133;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.STEEL_TEETH_ORE.getId(), Intensifys.STEEL_TEETH_ORE.getShow(), 20, 20, 5, 15);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.STEEL_TEETH_ORE.getId()));
        return super.build(stack);
    }
}
