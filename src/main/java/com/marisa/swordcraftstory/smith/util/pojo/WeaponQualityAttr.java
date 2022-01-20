package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 武器品质属性抽象类
 */

public abstract class WeaponQualityAttr extends AbstractQualityAttr {

    /**
     * 攻击力
     */
    private final int atk;

    /**
     * 敏捷
     */
    private final int agl;

    /**
     * 耐久
     */
    private final int dur;

    /**
     * 暴击率
     */
    protected final int cri;

    public WeaponQualityAttr(UUID uuidLuck, int luck, int atk, int agl, int dur, int cri) {
        super(uuidLuck, luck);
        this.atk = atk;
        this.agl = agl;
        this.dur = dur;
        this.cri = cri;
    }

    public int getAtk() {
        return atk;
    }

    public int getAgl() {
        return agl;
    }

    public int getDur() {
        return dur;
    }

    public int getCri() {
        return cri;
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        super.addAttributeModifier(event);
    }
}
