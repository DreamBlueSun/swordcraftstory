package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.smith.util.pojo.AbstractQualityAttr;
import com.marisa.swordcraftstory.smith.util.pojo.SwordQualityAttr;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import java.util.Random;

/**
 * 装备品质
 */

public enum Quality implements QualityAttr {

    UNKNOWN(0, 0, "未鉴定", ChatFormatting.RED) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(0, 0, 0);
            }
            return null;
        }
    },
    INFERIOR(1, 10, "劣质", ChatFormatting.DARK_GRAY) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(0, -0.1D, 0);
            }
            return null;
        }
    },
    ORDINARY(2, 64, "普通", ChatFormatting.WHITE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(0, 0, 0);
            }
            return null;
        }
    },
    GRADE(3, 15, "优良", ChatFormatting.GREEN) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(1, 0.05D, 0);
            }
            return null;
        }
    },
    RARE(4, 7, "稀有", ChatFormatting.BLUE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(2, 0.1D, 0);
            }
            return null;
        }
    },
    EPIC(5, 3, "史诗", ChatFormatting.DARK_PURPLE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(3, 0.15D, 5);
            }
            return null;
        }
    },
    FABULOUS(6, 1, "传说", ChatFormatting.GOLD) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (item instanceof SwordItem) {
                return new SwordQualityAttr(4, 0.25D, 5);
            }
            return null;
        }
    };

    private final int id;
    private final int weight;
    private final String name;
    private final ChatFormatting chatFormatting;

    Quality(int id, int weight, String name, ChatFormatting chatFormatting) {
        this.id = id;
        this.weight = weight;
        this.name = name;
        this.chatFormatting = chatFormatting;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public ChatFormatting getChatFormatting() {
        return chatFormatting;
    }

    public static Quality getById(int id) {
        for (Quality value : Quality.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public static Quality randomOne() {
        int total = 0;
        for (Quality value : Quality.values()) {
            total += value.getWeight();
        }
        int r = new Random().nextInt(total) + 1;
        if (r > (total -= FABULOUS.weight)) {
            return FABULOUS;
        }
        if (r > (total -= EPIC.weight)) {
            return EPIC;
        }
        if (r > (total -= RARE.weight)) {
            return RARE;
        }
        if (r > (total -= GRADE.weight)) {
            return GRADE;
        }
        if (r > (total - INFERIOR.weight)) {
            return INFERIOR;
        }
        return ORDINARY;
    }
}
