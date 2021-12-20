package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.weapon.TestSword;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 物品注册
 */

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Story.MOD_ID);

    //方块物品
    public static RegistryObject<Item> STORY_ORE_BLOCK = ITEMS.register("story_ore_block", () -> new BlockItem(BlockRegistry.STORY_ORE_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> TEST_SWORD = ITEMS.register("test_sword", TestSword::new);
}
