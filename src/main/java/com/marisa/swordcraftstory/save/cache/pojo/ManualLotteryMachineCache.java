package com.marisa.swordcraftstory.save.cache.pojo;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

/**
 *
 */

public class ManualLotteryMachineCache {

    private ServerLevel level;
    private BlockPos blockPos;

    private int runPower;
    private boolean isRunning;
    private int runningTick;

    public ManualLotteryMachineCache(ServerLevel level, BlockPos blockPos, int runPower, boolean isRunning, int runningTick) {
        this.level = level;
        this.blockPos = blockPos;
        this.runPower = runPower;
        this.isRunning = isRunning;
        this.runningTick = runningTick;
    }

    public int incrRunningTack() {
        return ++this.runningTick;
    }

    public void minusRunningTack() {
        this.runPower--;
    }

    public ServerLevel getLevel() {
        return level;
    }

    public void setLevel(ServerLevel level) {
        this.level = level;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public int getRunPower() {
        return runPower;
    }

    public void setRunPower(int runPower) {
        this.runPower = runPower;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getRunningTick() {
        return runningTick;
    }

    public void setRunningTick(int runningTick) {
        this.runningTick = runningTick;
    }
}
