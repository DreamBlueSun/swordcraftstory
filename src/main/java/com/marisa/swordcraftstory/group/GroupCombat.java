package com.marisa.swordcraftstory.group;

import com.marisa.swordcraftstory.item.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * @description:
 * @date: 2021/9/1 0001 23:45
 */

public class GroupCombat extends ItemGroup {

    public GroupCombat() {
        super("combat_group");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.HAMMER.get());
    }
}
