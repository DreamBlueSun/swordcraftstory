package com.marisa.swordcraftstory.item.weapon.ranged.bow;

import com.marisa.swordcraftstory.item.ore.LeijiStoweOre;
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
 * 雷齐斯托合金弓
 */

public class LeijiStoweBow extends AbstractBowWeapon {

    public LeijiStoweBow() {
        super(new LeijiStoweOre(), WeaponSkills.BOW_TEST_DEMO21.getId());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        WeaponInformationUtils.tipLeijiStowe(tooltip);
    }
}
