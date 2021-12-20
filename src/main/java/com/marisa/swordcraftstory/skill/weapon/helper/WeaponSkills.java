package com.marisa.swordcraftstory.skill.weapon.helper;

import com.marisa.swordcraftstory.skill.weapon.*;

/**
 *
 */

public enum WeaponSkills {

    UNKNOWN("0", "无", null),

    //共通武技
    HP_UP_SMALL(new HpUpSmall()),
    HP_UP_MEDIUM(new HpUpMedium()),
    HP_UP_BIG(new HpUpBig()),
    HP_UP_SUPPER(new HpUpSupper()),
    
    ATK_UP_SMALL(new AtkUpSmall()),
    ATK_UP_MEDIUM(new AtkUpMedium()),
    ATK_UP_BIG(new AtkUpBig()),
    ATK_UP_SUPPER(new AtkUpSupper()),

    DEF_UP_SMALL(new DefUpSmall()),
    DEF_UP_MEDIUM(new DefUpMedium()),
    DEF_UP_BIG(new DefUpBig()),
    
    CRI_UP_SMALL(new CriUpSmall()),
    CRI_UP_MEDIUM(new CriUpMedium()),
    CRI_UP_SUPPER(new CriUpSupper()),

    HEAL_USE_FAST_BLUE(new HealUseFastBlue()),
    HEAL_USE_FAST_YELLOW(new HealUseFastYellow()),
    HEAL_USE_FAST_RED(new HealUseFastRed()),
    //剑武技
    SWORD_TEST_DEMO4("1004", "无", null),
    SWORD_TEST_DEMO6("1006", "无", null),
    SWORD_TEST_DEMO11("1011", "无", null),
    SWORD_MASTERY_FIRST("1013", "无", null),
    SWORD_TEST_DEMO18("1024", "无", null),
    SWORD_TEST_DEMO19("1025", "无", null),
    SWORD_MASTERY_SECOND("1026", "无", null),
    SWORD_TEST_DEMO21("1028", "无", null),
    SWORD_TEST_DEMO22("1030", "无", null),
    SWORD_TEST_DEMO25("1040", "无", null),
    //弓武技
    BOW_TEST_DEMO4("2004", "无", null),
    BOW_TEST_DEMO6("2006", "无", null),
    BOW_TEST_DEMO11("2011", "无", null),
    BOW_MASTERY_FIRST("2013", "无", null),
    BOW_TEST_DEMO18("2024", "无", null),
    BOW_TEST_DEMO19("2025", "无", null),
    BOW_MASTERY_SECOND("2026", "无", null),
    BOW_TEST_DEMO21("2028", "无", null),
    BOW_TEST_DEMO22("2030", "无", null),
    BOW_TEST_DEMO25("2040", "无", null),
    //斧武技
    AXE_TEST_DEMO4("3004", "无", null),
    AXE_TEST_DEMO6("3006", "无", null),
    AXE_TEST_DEMO11("3011", "无", null),
    AXE_TEST_DEMO13("3013", "无", null),
    AXE_TEST_DEMO18("3024", "无", null),
    AXE_TEST_DEMO19("3025", "无", null),
    AXE_TEST_DEMO20("3026", "无", null),
    AXE_TEST_DEMO21("3028", "无", null),
    AXE_TEST_DEMO22("3030", "无", null),
    AXE_TEST_DEMO25("3040", "无", null);

    private final String id;
    private final String name;
    private final AbstractWeaponSkill skill;

    WeaponSkills(String id, String name, AbstractWeaponSkill skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    WeaponSkills(AbstractWeaponSkill skill) {
        this.id = skill.getStringId();
        this.name = skill.getName();
        this.skill = skill;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AbstractWeaponSkill getSkill() {
        return skill;
    }

    public static WeaponSkills getById(String id) {
        for (WeaponSkills value : WeaponSkills.values()) {
            if (value.id.equals(id)) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
