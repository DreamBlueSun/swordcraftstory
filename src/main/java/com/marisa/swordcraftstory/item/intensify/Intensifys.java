package com.marisa.swordcraftstory.item.intensify;

/**
 * 强化列表枚举
 */

public enum Intensifys {

    UNKNOWN(0, "未知"),
    IRON_ORE(1, "铁矿石"),
    BIG_ORE(2, "大矿石"),
    ARGENIR_ORE(3, "阿吉尼尔矿"),
    WINGS_FOSSIL_ORE(4, "翅膀化石"),
    UKA_ORE(5, "尤佳矿石"),
    PRETTY_ORE(6, "漂亮原石");

    private int id;
    private String show;

    Intensifys(int id, String show) {
        this.id = id;
        this.show = show;
    }

    public int getId() {
        return id;
    }

    public String getShow() {
        return show;
    }

    public static Intensifys getById(int id) {
        for (Intensifys value : Intensifys.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
