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
 * 甲壳化石
 */

public class CarapaceFossilOre extends AbstractOre {

    public CarapaceFossilOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("优质的甲壳变成的化石").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.CARAPACE_FOSSIL_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.CARAPACE_FOSSIL_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.CARAPACE_FOSSIL_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.CARAPACE_FOSSIL_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.CARAPACE_FOSSIL_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.CARAPACE_FOSSIL_AXE.get();
    }

    @Override
    public int rank() {
        return 4;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 44;
            case SWORD:
                return 42;
            case AXE:
                return 56;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 12;
            case SWORD:
                return 30;
            case AXE:
                return 36;
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
            default:
                return 0;
        }
    }

    @Override
    public int dur(WeaponType type) {
        switch (type) {
            case BOW:
                return 75;
            case SWORD:
                return 85;
            case AXE:
                return 100;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.CARAPACE_FOSSIL_ORE.getId(), Intensifys.CARAPACE_FOSSIL_ORE.getShow(), 0, 10, 0, 0);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.CARAPACE_FOSSIL_ORE.getId()));
        return super.build(stack);
    }
}
