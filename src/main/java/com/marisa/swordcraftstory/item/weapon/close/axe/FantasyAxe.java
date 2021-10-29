package com.marisa.swordcraftstory.item.weapon.close.axe;

import com.marisa.swordcraftstory.item.ore.FantasyOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractAxeWeapon;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 幻象长斧
 */

public class FantasyAxe extends AbstractAxeWeapon {

    public FantasyAxe() {
        super(new FantasyOre(), WeaponSkills.HP_UP_SUPPER.getId());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipFantasy(tooltip);
    }
}