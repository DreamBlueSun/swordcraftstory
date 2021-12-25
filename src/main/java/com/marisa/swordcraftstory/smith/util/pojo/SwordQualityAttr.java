package com.marisa.swordcraftstory.smith.util.pojo;

import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.world.item.ItemStack;

/**
 * 剑类武器品质属性
 */

public class SwordQualityAttr extends AbstractQualityAttr {

    /**
     * 攻击力
     */
    private final int atk;

    /**
     * 攻速
     */
    private final double atkS;

    /**
     * 敏捷
     */
    private final int agl;

    public SwordQualityAttr(int atk, double atkS, int agl) {
        this.atk = atk;
        this.atkS = atkS;
        this.agl = agl;
    }

    public int getAtk() {
        return atk;
    }

    public double getAtkS() {
        return atkS;
    }

    public int getAgl() {
        return agl;
    }

    @Override
    public void modifyBase(ItemStack itemStack) {
        SmithNbtUtils.setAtk(itemStack, this.atk);
        SmithNbtUtils.setAtkS(itemStack, this.atkS);
        SmithNbtUtils.setAgl(itemStack, this.agl);
    }
}
