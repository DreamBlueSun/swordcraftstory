package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
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
 * 尤佳矿石
 */

public class UkaOre extends AbstractOre {

    public UkaOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("经常被当作高价的装饰使用").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.ELEGANCE_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.ELEGANCE_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.ELEGANCE_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.ELEGANCE_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.ELEGANCE_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.ELEGANCE_AXE.get();
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
            case AXE:
                return 47;
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
            case AXE:
                return 32;
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
                return 71;
            case SWORD:
                return 82;
            case AXE:
                return 96;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.UKA_ORE.getId(), Intensifys.UKA_ORE.getShow(), 3, 6, 0, 0);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.UKA_ORE.getId()));
        return super.build(stack);
    }
}
