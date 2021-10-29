package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.item.ore.LeijiStoweOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractSwordWeapon;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 雷齐斯托合金剑
 */

public class LeijiStoweSword extends AbstractSwordWeapon {

    public LeijiStoweSword() {
        super(new LeijiStoweOre(), WeaponSkills.SWORD_TEST_DEMO21.getId());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipLeijiStowe(tooltip);
    }
}