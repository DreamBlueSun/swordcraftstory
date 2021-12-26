package com.marisa.swordcraftstory.smith.util.pojo;

import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.world.item.ItemStack;

/**
 * 射击类武器品质属性
 */

public class ShootQualityAttr extends AbstractQualityAttr {

    /**
     * 攻击力
     */
    private final int atk;

    /**
     * 敏捷
     */
    private final int agl;

    public ShootQualityAttr(int atk, int agl) {
        this.atk = atk;
        this.agl = agl;
    }

    public int getAtk() {
        return atk;
    }

    public int getAgl() {
        return agl;
    }

    @Override
    public void modifyBase(ItemStack itemStack) {
        SmithNbtUtils.setAtk(itemStack, this.atk);
        SmithNbtUtils.setAgl(itemStack, this.agl);
    }
}
