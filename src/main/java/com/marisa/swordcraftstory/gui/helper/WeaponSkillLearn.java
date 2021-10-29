package com.marisa.swordcraftstory.gui.helper;

import com.marisa.swordcraftstory.skill.weapon.helper.AbstractWeaponSkill;

/**
 * 武技学习进度VO
 */

public class WeaponSkillLearn {

    private AbstractWeaponSkill skill;
    private int progress;

    public WeaponSkillLearn(AbstractWeaponSkill skill, int progress) {
        this.skill = skill;
        this.progress = progress;
    }

    public AbstractWeaponSkill getSkill() {
        return skill;
    }

    public void setSkill(AbstractWeaponSkill skill) {
        this.skill = skill;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean nonNull() {
        return skill != null;
    }
}
