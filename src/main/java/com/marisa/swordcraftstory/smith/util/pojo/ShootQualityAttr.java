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

    @Override
    public void modifyBase(ItemStack itemStack) {
        SmithNbtUtils.QualityUtils.setQualityAttr(itemStack, new int[]{this.atk, this.agl});
    }
}
