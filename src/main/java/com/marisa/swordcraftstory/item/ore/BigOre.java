package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyAttr;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.model.WeaponModels;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import net.minecraft.item.Items;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 大矿石
 */

public class BigOre extends AbstractOre {

    public BigOre() {
        super(new Properties().tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("非常大的矿石看来可以做出大型武器").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public ItemStack weaponMake(ItemStack mouldStack) {
        ItemStack stack;
        switch (((Mould) mouldStack.getItem()).type()) {
            case BOW:
                stack = ItemRegistry.IRON_BOW.get().getDefaultInstance();
                break;
            case SWORD:
                stack = ItemRegistry.IRON_SWORD.get().getDefaultInstance();
                break;
            case AXE:
                stack = ItemRegistry.IRON_AXE.get().getDefaultInstance();
                break;
            default:
                stack = Items.AIR.getDefaultInstance();
        }
        addMouldProperties(stack, mouldStack);
        return stack;
    }

    @Override
    public Item asSword() {
        return ItemRegistry.IRON_SWORD.get();
    }

    @Override
    public Item asBow() {
        return ItemRegistry.IRON_BOW.get();
    }

    @Override
    public Item asAxe() {
        return ItemRegistry.IRON_AXE.get();
    }

    @Override
    public int rank() {
        return 2;
    }

    @Override
    public int atk(WeaponType type) {
        switch (type) {
            case BOW:
                return 23;
            case SWORD:
                return 21;
            case AXE:
                return 29;
            default:
                return 0;
        }
    }

    @Override
    public int def(WeaponType type) {
        switch (type) {
            case BOW:
                return 6;
            case SWORD:
                return 15;
            case AXE:
                return 18;
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
                return 50;
            case SWORD:
                return 70;
            case AXE:
                return 85;
            default:
                return 0;
        }
    }

    @Override
    public IntensifyAttr getIntensifyAttr() {
        return new IntensifyAttr(Intensifys.BIG_ORE.getId(), Intensifys.BIG_ORE.getShow(), 0, 5, 0, 5);
    }

    @Override
    public boolean build(ItemStack stack) {
        stack.getOrCreateTag().put("story_combat_model_change", IntTag.valueOf(WeaponModels.BIG_ORE.getId()));
        return super.build(stack);
    }
}
