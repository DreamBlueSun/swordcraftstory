package com.marisa.swordcraftstory.item.weapon.close.axe;

import com.marisa.swordcraftstory.item.ore.DewFireflyOre;
import com.marisa.swordcraftstory.item.weapon.close.AbstractAxeWeapon;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 鲁恩之斧
 */

public class DewFireflyAxe extends AbstractAxeWeapon {

    public DewFireflyAxe() {
        super(new DewFireflyOre(), WeaponSkills.AXE_TEST_DEMO22.getId());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipDewFirefly(tooltip);
    }
}
