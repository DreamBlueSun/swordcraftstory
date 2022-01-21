package com.marisa.swordcraftstory.smith.util;

import com.marisa.swordcraftstory.event.pojo.Damage;
import com.marisa.swordcraftstory.smith.util.pojo.*;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.util.Random;

/**
 * 装备品质
 */

public enum EQuality implements IQuality {

    UNKNOWN(0, 0, "未鉴定", ChatFormatting.RED) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 0, 0, 0, 0, 0);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, 0, 0, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 0, 0, 0);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 0, 0, 0);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 0, 0, 0);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 0, 0, 0);
                    }
                }
            }
            return null;
        }
    },
    INFERIOR(1, 10, "劣质", ChatFormatting.DARK_GRAY) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 0, 0, 0, 0, -0.1D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE - 2, -5, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 0, 0, 0);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 1, 0, 0);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 0, 0, 0);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 0, 0, -0.02D);
                    }
                }
            }
            return null;
        }
    },
    ORDINARY(2, 64, "普通", ChatFormatting.WHITE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 0, 0, 0, 0, 0);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE, 0, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 0, 0, 10);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 2, 0, 0);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 1, 0, 0);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 0, 0, 0.01D);
                    }
                }
            }
            return null;
        }
    },
    GRADE(3, 15, "优良", ChatFormatting.GREEN) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 1, 0, 5, 0, 0.03D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE + 1, 0, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 1, 0, 20);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 3, 0, 10);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 2, 0, 0.01D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 0, 0, 0.03D);
                    }
                }
            }
            return null;
        }
    },
    RARE(4, 7, "稀有", ChatFormatting.BLUE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 2, 0, 10, 0, 0.05D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE + 2, 5, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 2, 1, 30);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 4, 1, 20);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 3, 1, 0.02D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 1, 1, 0.05D);
                    }
                }
            }
            return null;
        }
    },
    EPIC(5, 3, "史诗", ChatFormatting.LIGHT_PURPLE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 3, 5, 15, 0, 0.07D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE + 3, 5, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 3, 2, 40);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 5, 2, 30);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 4, 2, 0.03D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 2, 2, 0.07D);
                    }
                }
            }
            return null;
        }
    },
    FABULOUS(6, 1, "传说", ChatFormatting.GOLD) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(0, 4, 5, 20, 0, 0.15D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(0, (int) Damage.ARROW_BASE_DAMAGE + 5, 7, 0, 0);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(0, 5, 3, 50);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(0, 7, 3, 40);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(0, 6, 3, 0.05D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(0, 4, 3, 0.1D);
                    }
                }
            }
            return null;
        }
    },
    ROTTEN(7, 12, "腐朽", ChatFormatting.DARK_GREEN) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(3, -3, 0, -30, 30, 0);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(3, (int) Damage.ARROW_BASE_DAMAGE - 5, 0, 0, 20);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(1, -7, 0, -80);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(1, -5, 0, -120);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(1, -6, 0, -0.07D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(1, -8, 0, -0.03D);
                    }
                }
            }
            return null;
        }
    },
    BROKEN(8, 6, "残破", ChatFormatting.DARK_BLUE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(4, -2, 0, -20, 50, 0.03D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(4, (int) Damage.ARROW_BASE_DAMAGE - 2, 0, 0, 50);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(2, -5, 0, -60);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(2, -3, 0, -90);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(2, -4, 0, -0.05D);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(2, -6, 0, -0.02D);
                    }
                }
            }
            return null;
        }
    },
    OLD(9, 2, "古老", ChatFormatting.DARK_PURPLE) {
        @Override
        public AbstractQualityAttr getAttr(Item item) {
            if (StoryUtils.isMeleeWeapon(item)) {
                return new SwordQualityAttr(5, -1, 0, -10, 70, 0.1D);
            } else if (StoryUtils.isRangedWeapon(item)) {
                return new ShootQualityAttr(5, (int) Damage.ARROW_BASE_DAMAGE, 0, 0, 100);
            } else if (item instanceof ArmorItem armor) {
                switch (armor.getSlot()) {
                    case HEAD -> {
                        return new HeadQualityAttr(3, -3, 1, -40);
                    }
                    case CHEST -> {
                        return new ChestQualityAttr(3, -1, 1, -60);
                    }
                    case LEGS -> {
                        return new LegsQualityAttr(3, -2, 1, 0);
                    }
                    case FEET -> {
                        return new FeetQualityAttr(3, -4, 1, 0);
                    }
                }
            }
            return null;
        }
    };

    private final int id;
    private final int weight;
    private final String name;
    private final ChatFormatting chatFormatting;

    EQuality(int id, int weight, String name, ChatFormatting chatFormatting) {
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

    public static EQuality getById(int id) {
        for (EQuality value : EQuality.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public static EQuality randomOne(boolean isLoot) {
        int total = 0;
        for (EQuality value : EQuality.values()) {
            if (value.id == UNKNOWN.id) {
                continue;
            }
            if (value.id > FABULOUS.id && !isLoot) {
                break;
            }
            total += value.getWeight();
        }
        int r = new Random().nextInt(total) + 1;
        if (isLoot) {
            if (r > (total -= OLD.weight)) {
                return OLD;
            }
            if (r > (total -= BROKEN.weight)) {
                return BROKEN;
            }
            if (r > (total -= ROTTEN.weight)) {
                return ROTTEN;
            }
        }
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
        if (r > (total - ORDINARY.weight)) {
            return ORDINARY;
        }
        return INFERIOR;
    }
}
