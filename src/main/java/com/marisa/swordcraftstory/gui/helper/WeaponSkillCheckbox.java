package com.marisa.swordcraftstory.gui.helper;

import com.marisa.swordcraftstory.skill.weapon.helper.AbstractWeaponSkill;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.text.ITextComponent;

/**
 * 武技checkbox
 */

public class WeaponSkillCheckbox extends CheckboxButton {

    private static int AMOUNT;
    private final AbstractWeaponSkill skill;
    private final CheckboxOnChange able;

    public WeaponSkillCheckbox(int x, int y, int width, int height, ITextComponent title, boolean checked, int amount, AbstractWeaponSkill skill, CheckboxOnChange able) {
        super(x, y, width, height, title, checked);
        AMOUNT = amount;
        this.skill = skill;
        this.able = able;
    }

    public AbstractWeaponSkill getSkill() {
        return skill;
    }

    @Override
    public void onPress() {
        if (isChecked()) {
            super.onPress();
            able.onChange(isChecked(), skill.getCost());
            AMOUNT += skill.getCost();
        } else if (AMOUNT >= skill.getCost()) {
            super.onPress();
            able.onChange(isChecked(), skill.getCost());
            AMOUNT -= skill.getCost();
        }
    }
}
