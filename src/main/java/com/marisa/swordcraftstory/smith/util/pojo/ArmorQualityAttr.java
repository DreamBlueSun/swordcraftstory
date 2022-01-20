package com.marisa.swordcraftstory.smith.util.pojo;

import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

/**
 * 盔甲类品质属性
 */

public abstract class ArmorQualityAttr extends AbstractQualityAttr {

    /**
     * 护甲
     */
    protected final int def;

    /**
     * 韧性
     */
    protected final int phy;

    public ArmorQualityAttr(UUID uuidLuck, int luck, int def, int phy) {
        super(uuidLuck, luck);
        this.def = def;
        this.phy = phy;
    }

    public int getDef() {
        return def;
    }

    public int getPhy() {
        return phy;
    }

    @Override
    public void addAttributeModifier(ItemAttributeModifierEvent event) {
        super.addAttributeModifier(event);
    }
}
