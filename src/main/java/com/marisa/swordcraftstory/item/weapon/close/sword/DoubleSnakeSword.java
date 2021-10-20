package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.item.ore.DoubleSnakeOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 双蛇之刃
 */

public class DoubleSnakeSword extends AbstractSwordWeapon {

    public DoubleSnakeSword() {
        super(new DoubleSnakeOre());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipDoubleSnake(tooltip);
    }
}
