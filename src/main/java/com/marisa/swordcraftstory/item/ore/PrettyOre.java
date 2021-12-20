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
 * 漂亮原石
 */

public class PrettyOre extends AbstractOre {

    public PrettyOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("外观深受女性的青睐").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.RIBBON_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.RIBBON_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.RIBBON_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.RIBBON_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.RIBBON_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.RIBBON_AXE.get();
    }

    @Override
    public int rank() {
        return 3;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 37;
            case SWORD:
                return 39;
            case AXE:
                return 49;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 11;
            case SWORD:
                return 26;
            case AXE:
                return 34;
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
                return 72;
            case SWORD:
                return 84;
            case AXE:
                return 97;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.PRETTY_ORE.getId(), Intensifys.PRETTY_ORE.getShow(), 4, 4, 0, 8);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.PRETTY_ORE.getId()));
        return super.build(stack);
    }
}
