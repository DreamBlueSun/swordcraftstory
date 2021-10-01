package com.marisa.swordcraftstory.group;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * 创建创造物品栏分组
 */

public class StoryGroup {

    public static ItemGroup COMBAT_GROUP = new GroupCombat();
    
    public static ItemGroup MODEL_CHANGE_GROUP = new ItemGroup("model_change_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.DRAGON_SLAVE_SWORD.get());
        }
    };

}
