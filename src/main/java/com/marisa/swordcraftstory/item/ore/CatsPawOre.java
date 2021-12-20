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
 * 肉球的化石
 */

public class CatsPawOre extends AbstractOre {

    public CatsPawOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("明明是石头却很柔软手感非常好").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.CATS_PAW_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.CATS_PAW_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.CATS_PAW_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.CATS_PAW_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.CATS_PAW_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.CATS_PAW_AXE.get();
    }

    @Override
    public int rank() {
        return 9;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 85;
            case SWORD:
                return 90;
            case AXE:
                return 100;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 20;
            case SWORD:
                return 66;
            case AXE:
                return 75;
            default:
                return 0;
        }
    }

    @Override
    public int agl(WeaponType type) {
        switch (type) {
            case BOW:
            case AXE:
                return 5;
            case SWORD:
                return 15;
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
            case SWORD:
            case AXE:
                return 100;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.CATS_PAW_ORE.getId(), Intensifys.CATS_PAW_ORE.getShow(), -10, -10, 20, -10);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.CATS_PAW_ORE.getId()));
        return super.build(stack);
    }
}
