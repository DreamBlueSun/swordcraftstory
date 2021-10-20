package com.marisa.swordcraftstory.item.intensify.helper;

/**
 * 强化列表枚举
 */

public enum Intensifys {

    UNKNOWN(0, "未知"),

    //矿石
    IRON_ORE(1, "铁矿石"),
    SLANDER_ORE(2, "斯兰德原石"),
    HARD_ORE(3, "加德英矿石"),
    ARGENIR_ORE(4, "阿吉尼尔矿"),
    BIG_ORE(5, "大矿石"),
    ELPHUS_ORE(6, "艾尔弗伊斯原石"),
    WINGS_FOSSIL_ORE(7, "翅膀化石"),
    CUT_IRON_ORE(8, "斩铁矿"),
    BIG_IRON_ORE(9, "大铁矿石"),
    HEAVY_GOLD_ORE(10, "重金矿"),
    REGIA_ORE(11, "雷吉亚矿"),
    UKA_ORE(12, "尤佳矿石"),
    PRETTY_ORE(13, "漂亮原石"),
    CARAPACE_FOSSIL_ORE(14, "甲壳化石"),
    FANTASY_ORE(16, "幻想矿"),
    GALIA_ORE(20, "加利亚矿石"),
    CUISINE_ORE(21, "料理矿"),
    DOUBLE_SNAKE_ORE(24, "双蛇矿石"),
    SPRINT_ORE(25, "斯普林特矿石"),
    XCEL_ORE(26, "艾克赛尔矿石"),
    LEIJI_STOWE_ORE(28, "雷齐斯托原石"),
    DEW_FIREFLY_ORE(30, "露萤密石"),
    STEEL_TEETH_ORE(33, "钢牙原石"),
    CATS_PAW_ORE(36, "肉球的化石"),
    LUMINOSITY_ORE(40, "光辉之石"),

    //强化素材
    LEGEND_IRON_BUNCH(1001, "传说级的铁串"),
    HEART_GLASSES(1002, "心眼眼镜"),
    MOMENTUM_HEAD_BAND(1003, "气势头巾"),
    POWER_CERTIFICATE(1004, "怪力之证"),
    TALENT_CERTIFICATE(1005, "达人之证");

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
