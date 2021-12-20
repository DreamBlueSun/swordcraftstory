package com.marisa.swordcraftstory.item.ore;

import com.marisa.swordcraftstory.item.intensify.helper.Intensify;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.Component;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 抽象矿石类
 */

public abstract class AbstractOre extends Item implements WeaponMake, Intensify, WeaponModel {

    public AbstractOre(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("制作、强化、幻化[全]").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                .append("     ").append(new TranslatableComponent(String.valueOf(rank())).withStyle(ChatFormatting.LIGHT_PURPLE)));
    }

    /**
     * 制作时累加模具属性值
     */
    protected void addMouldProperties(ItemStack stack, ItemStack mouldStack) {
        if (!stack.isEmpty()) {
            Mould mould = (Mould) mouldStack.getItem();
            CombatPropertiesUtils.setAtk(stack, mould.getAtk(mouldStack));
            CombatPropertiesUtils.setDef(stack, mould.getDef(mouldStack));
            CombatPropertiesUtils.setAgl(stack, mould.getAgl(mouldStack));
            CombatPropertiesUtils.setDur(stack, mould.getDurMax(mouldStack));
            CombatPropertiesUtils.setDurMax(stack, mould.getDurMax(mouldStack));
        }
    }

    @Override
    public boolean build(ItemStack stack) {
        return true;
    }

    @Override
    public String getModelName(ItemStack stack) {
        switch (((Weapon)stack.getItem()).type()) {
            case SWORD:
                return asSword().getName().getString();
            case BOW:
                return asBow().getName().getString();
            case AXE:
                return asAxe().getName().getString();
            default:
                return "未幻化";
        }
    }
}
