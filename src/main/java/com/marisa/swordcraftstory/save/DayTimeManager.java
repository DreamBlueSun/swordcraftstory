package com.marisa.swordcraftstory.save;

/**
 * 天时间管理
 */

public class DayTimeManager {

    private static final int DAY_LONG = 24000;

    private static int WORLD_DAY;

    static {
        WORLD_DAY = -1;
    }

    public static int getWorldDay() {
        return WORLD_DAY;
    }

    public static void load(ManualLotteryMachineSaveData saveData){
        WORLD_DAY = saveData.getWorldDay();
    }

    public static boolean isNextDayAndToNext(long dayTime) {
        if (dayTime % DAY_LONG == 1) {
            int next = (int) (dayTime - 1) / DAY_LONG;
            if (next > WORLD_DAY) {
                WORLD_DAY = next;
                return true;
            }
        }
        return false;
    }

}
