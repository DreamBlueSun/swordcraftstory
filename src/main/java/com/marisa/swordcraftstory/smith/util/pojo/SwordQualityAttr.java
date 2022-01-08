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

    @Override
    public void modifyBase(ItemStack itemStack) {
        SmithNbtUtils.QualityUtils.setQualityAttr(itemStack, new int[]{this.atk, this.agl});
        SmithNbtUtils.setAtkS(itemStack, this.atkS);
    }
}
