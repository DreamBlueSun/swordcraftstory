package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 玩家信息管理
 */

public class StoryPlayerDataManager {

    private static final Map<String, StoryPlayerData> MAP = new HashMap(8);

    public static StoryPlayerData get(String playerUUID) {
        return MAP.get(playerUUID);
    }

    public static StoryPlayerData put(StoryPlayerData data) {
        return MAP.put(data.getPlayerUUID(), data);
    }


    public static final int LV_NEED_XP_BASE = 202191;
    public static final int LV_NEED_XP_OFFSET = 1126;
    public static final List<Integer> LV_NEED_XP;

    static {
        LV_NEED_XP = new ArrayList<>();
        LV_NEED_XP.add(LV_NEED_XP_BASE / LV_NEED_XP_OFFSET);
        for (int i = 1; i <= Story.LV_MAX; i++) {
            LV_NEED_XP.add(LV_NEED_XP.get(i - 1) + LV_NEED_XP_BASE * i / LV_NEED_XP_OFFSET);
        }
    }

    /**
     * 获取当前等级
     *
     * @param xp 当前经验值
     */
    public static int getLv(int xp) {
        int lv = 0;
        for (int i = 0; i < LV_NEED_XP.size(); i++) {
            if (xp >= LV_NEED_XP.get(i)) {
                lv = i;
            } else {
                break;
            }
        }
        return lv;
    }

    /**
     * 获取升级所需要达到的经验值点
     *
     * @param lv 当前等级
     */
    public static int getLvNextXpPoint(int lv) {
        if (40 > lv && lv > -1) {
            return LV_NEED_XP.get(lv + 1);
        } else {
            return 0;
        }
    }

}
