package com.marisa.swordcraftstory.gui.container.IInt;

import net.minecraft.util.IIntArray;

/**
 * @date: 2021/9/8 12:38
 */

public class IntensifyEdgePointInt implements IIntArray {

    int pointMax = 0;

    @Override
    public int get(int index) {
        return pointMax;
    }

    @Override
    public void set(int index, int value) {
        pointMax = value;
    }

    @Override
    public int size() {
        return 1;
    }
}
