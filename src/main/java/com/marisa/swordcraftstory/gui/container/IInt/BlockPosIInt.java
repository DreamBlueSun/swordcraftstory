package com.marisa.swordcraftstory.gui.container.IInt;

import net.minecraft.util.IIntArray;

/**
 * 方块坐标int数组
 */

public class BlockPosIInt implements IIntArray {

    int posX;
    int posy;
    int posZ;

    public BlockPosIInt() {
        this.posX = 0;
        this.posy = 0;
        this.posZ = 0;
    }

    @Override
    public int get(int index) {
        switch (index) {
            case 0:
                return posX;
            case 1:
                return posy;
            case 2:
                return posZ;
            default:
                return 0;
        }
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0:
                posX = value;
                break;
            case 1:
                posy = value;
                break;
            case 2:
                posZ = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int size() {
        return 3;
    }
}
