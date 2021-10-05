package com.marisa.swordcraftstory.gui.screen;

/**
 * 手摇抽奖机物品信息数据
 */

public class ManualLotteryItemInfoScreenData {

    private String itemName1;
    private String itemName2;
    private String itemName3;
    private String itemName4;
    private String itemName5;

    public ManualLotteryItemInfoScreenData(String itemName1, String itemName2, String itemName3, String itemName4, String itemName5) {
        this.itemName1 = itemName1;
        this.itemName2 = itemName2;
        this.itemName3 = itemName3;
        this.itemName4 = itemName4;
        this.itemName5 = itemName5;
    }

    public String getItemName1() {
        return itemName1;
    }

    public String getItemName2() {
        return itemName2;
    }

    public String getItemName3() {
        return itemName3;
    }

    public String getItemName4() {
        return itemName4;
    }

    public String getItemName5() {
        return itemName5;
    }
}
