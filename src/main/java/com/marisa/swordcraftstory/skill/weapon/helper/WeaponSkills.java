package com.marisa.swordcraftstory.skill.weapon.helper;

import com.marisa.swordcraftstory.skill.weapon.HpUpSmall;

/**
 *
 */

public enum WeaponSkills {

    UNKNOWN("0", null),
    //剑武技
    SWORD_TEST_DEMO1("1001", new HpUpSmall()),
    HP_UP_SMALL("1002", new HpUpSmall()),
    SWORD_TEST_DEMO3("1003", new HpUpSmall()),
    SWORD_TEST_DEMO4("1004", new HpUpSmall()),
    HP_UP_MEDIUM("1005", new HpUpSmall()),
    SWORD_TEST_DEMO6("1006", new HpUpSmall()),
    SWORD_TEST_DEMO7("1007", new HpUpSmall()),
    SWORD_TEST_DEMO8("1008", new HpUpSmall()),
    HP_UP_BIG("1009", new HpUpSmall()),
    SWORD_TEST_DEMO10("1010", new HpUpSmall()),
    SWORD_TEST_DEMO11("1011", new HpUpSmall()),
    SWORD_TEST_DEMO12("1012", new HpUpSmall()),
    SWORD_TEST_DEMO13("1013", new HpUpSmall()),
    HP_UP_SUPPER("1014", new HpUpSmall()),
    SWORD_TEST_DEMO15("1016", new HpUpSmall()),
    SWORD_TEST_DEMO16("1020", new HpUpSmall()),
    SWORD_TEST_DEMO17("1021", new HpUpSmall()),
    SWORD_TEST_DEMO18("1024", new HpUpSmall()),
    SWORD_TEST_DEMO19("1025", new HpUpSmall()),
    SWORD_TEST_DEMO20("1026", new HpUpSmall()),
    SWORD_TEST_DEMO21("1028", new HpUpSmall()),
    SWORD_TEST_DEMO22("1030", new HpUpSmall()),
    SWORD_TEST_DEMO23("1033", new HpUpSmall()),
    SWORD_TEST_DEMO24("1036", new HpUpSmall()),
    SWORD_TEST_DEMO25("1040", new HpUpSmall()),
    //弓武技
    ATK_UP_SMALL("2001", new HpUpSmall()),
    BOW_TEST_DEMO2("2002", new HpUpSmall()),
    BOW_TEST_DEMO3("2003", new HpUpSmall()),
    BOW_TEST_DEMO4("2004", new HpUpSmall()),
    BOW_TEST_DEMO5("2005", new HpUpSmall()),
    BOW_TEST_DEMO6("2006", new HpUpSmall()),
    BOW_TEST_DEMO7("2007", new HpUpSmall()),
    ATK_UP_MEDIUM("2008", new HpUpSmall()),
    BOW_TEST_DEMO9("2009", new HpUpSmall()),
    BOW_TEST_DEMO10("2010", new HpUpSmall()),
    BOW_TEST_DEMO11("2011", new HpUpSmall()),
    BOW_TEST_DEMO12("2012", new HpUpSmall()),
    BOW_TEST_DEMO13("2013", new HpUpSmall()),
    BOW_TEST_DEMO14("2014", new HpUpSmall()),
    BOW_TEST_DEMO15("2016", new HpUpSmall()),
    ATK_UP_BIG("2020", new HpUpSmall()),
    BOW_TEST_DEMO17("2021", new HpUpSmall()),
    BOW_TEST_DEMO18("2024", new HpUpSmall()),
    BOW_TEST_DEMO19("2025", new HpUpSmall()),
    BOW_TEST_DEMO20("2026", new HpUpSmall()),
    BOW_TEST_DEMO21("2028", new HpUpSmall()),
    ATK_UP_SUPPER("2030", new HpUpSmall()),
    BOW_TEST_DEMO23("2033", new HpUpSmall()),
    BOW_TEST_DEMO24("2036", new HpUpSmall()),
    BOW_TEST_DEMO25("2040", new HpUpSmall()),
    //斧武技
    AXE_TEST_DEMO1("3001", new HpUpSmall()),
    DEF_UP_SMALL("3002", new HpUpSmall()),
    AXE_TEST_DEMO3("3003", new HpUpSmall()),
    AXE_TEST_DEMO4("3004", new HpUpSmall()),
    DEF_UP_MEDIUM("3005", new HpUpSmall()),
    AXE_TEST_DEMO6("3006", new HpUpSmall()),
    AXE_TEST_DEMO7("3007", new HpUpSmall()),
    AXE_TEST_DEMO8("3008", new HpUpSmall()),
    DEF_UP_BIG("3009", new HpUpSmall()),
    AXE_TEST_DEMO10("3010", new HpUpSmall()),
    AXE_TEST_DEMO11("3011", new HpUpSmall()),
    AXE_TEST_DEMO12("3012", new HpUpSmall()),
    AXE_TEST_DEMO13("3013", new HpUpSmall()),
    DEF_UP_SUPPER("3014", new HpUpSmall()),
    AXE_TEST_DEMO15("3016", new HpUpSmall()),
    AXE_TEST_DEMO16("3020", new HpUpSmall()),
    AXE_TEST_DEMO17("3021", new HpUpSmall()),
    AXE_TEST_DEMO18("3024", new HpUpSmall()),
    AXE_TEST_DEMO19("3025", new HpUpSmall()),
    AXE_TEST_DEMO20("3026", new HpUpSmall()),
    AXE_TEST_DEMO21("3028", new HpUpSmall()),
    AXE_TEST_DEMO22("3030", new HpUpSmall()),
    AXE_TEST_DEMO23("3033", new HpUpSmall()),
    AXE_TEST_DEMO24("3036", new HpUpSmall()),
    AXE_TEST_DEMO25("3040", new HpUpSmall());

    private String id;

    private AbstractWeaponSkill skill;

    WeaponSkills(String id, AbstractWeaponSkill skill) {
        this.id = id;
        this.skill = skill;
    }

    public String getId() {
        return id;
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
