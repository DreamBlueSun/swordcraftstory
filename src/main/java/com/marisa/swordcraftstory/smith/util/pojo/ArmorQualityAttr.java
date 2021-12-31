package com.marisa.swordcraftstory.smith.util.pojo;

import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.world.item.ItemStack;

/**
 * 盔甲类品质属性
 */

public class ArmorQualityAttr extends AbstractQualityAttr {

    /**
     * 护甲
     */
    private final int def;

    /**
     * 韧性
     */
    private final int phy;

    public ArmorQualityAttr(int def, int phy) {
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
    public void modifyBase(ItemStack itemStack) {
        SmithNbtUtils.setDef(itemStack, this.def);
        SmithNbtUtils.setPhy(itemStack, this.phy);
        if (itemStack.isDamageableItem()) {
            itemStack.getOrCreateTag().putInt("HideFlags", 4);
            itemStack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
    }
}
