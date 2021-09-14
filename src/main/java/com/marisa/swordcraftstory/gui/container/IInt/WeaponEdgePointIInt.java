package com.marisa.swordcraftstory.gui.container.IInt;

import net.minecraft.util.IIntArray;

/**
 * 强刃点数以及方块坐标int数组
 */

public class WeaponEdgePointIInt implements IIntArray {

    int pointMax = 0;
    int posX = 0;
    int posy = 0;
    int posZ = 0;

    @Override
    public int get(int index) {
        switch (index) {
            case 0:
                return pointMax;
            case 1:
                return posX;
            case 2:
                return posy;
            case 3:
                return posZ;
            default:
                return 0;
        }
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0:
                pointMax = value;
                break;
            case 1:
                posX = value;
                break;
            case 2:
                posy = value;
                break;
            case 3:
                posZ = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int size() {
        return 4;
    }
}
