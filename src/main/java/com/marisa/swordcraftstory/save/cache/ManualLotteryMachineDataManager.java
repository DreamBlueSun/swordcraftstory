package com.marisa.swordcraftstory.save.cache;

import com.marisa.swordcraftstory.save.cache.pojo.ManualLotteryMachineCache;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ManualLotteryMachineDataManager {

    private static final Map<Level, Map<BlockPos, ManualLotteryMachineCache>> MAP = new HashMap(4);

    public static Map<BlockPos, ManualLotteryMachineCache> get(Level level) {
        if (!MAP.containsKey(level)) {
            return null;
        }
        return MAP.get(level);
    }

    public static ManualLotteryMachineCache get(Level level, BlockPos blockPos) {
        Map<BlockPos, ManualLotteryMachineCache> cacheMap = get(level);
        if (cacheMap == null) {
            return null;
        }
        if (!cacheMap.containsKey(blockPos)) {
            return null;
        }
        return cacheMap.get(blockPos);
    }

    public static void put(ManualLotteryMachineCache data) {
        ServerLevel level = data.getLevel();
        BlockPos blockPos = data.getBlockPos();
        Map<BlockPos, ManualLotteryMachineCache> cacheMap;
        if (MAP.containsKey(level)) {
            cacheMap = MAP.get(level);
            if (cacheMap.containsKey(blockPos)) {
                ManualLotteryMachineCache cache = cacheMap.get(blockPos);
                cache.setRunPower(cache.getRunPower() + data.getRunPower());
            } else {
                cacheMap.put(blockPos, data);
            }
        } else {
            cacheMap = new HashMap<>(1);
            cacheMap.put(blockPos, data);
            MAP.put(level, cacheMap);
        }
    }

    public static void rem(ManualLotteryMachineCache data) {
        ServerLevel level = data.getLevel();
        if (MAP.containsKey(level)) {
            Map<BlockPos, ManualLotteryMachineCache> cacheMap = MAP.get(level);
            if (cacheMap != null) {
                cacheMap.remove(data.getBlockPos());
            }
        }
    }

}
