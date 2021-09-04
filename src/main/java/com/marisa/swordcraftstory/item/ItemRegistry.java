package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.special.SwordGuake;
import com.marisa.swordcraftstory.item.combat.close.NoviceSword;
import com.marisa.swordcraftstory.item.combat.ranged.NoviceBow;
import com.marisa.swordcraftstory.item.mould.close.SwordMould;
import com.marisa.swordcraftstory.item.mould.ranged.BowMould;
import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.item.special.Hammer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description: 物品注册
 * @date: 2021/9/1 0001 1:27
 */

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "swordcraftstory");
    //锻冶
    public static RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);
    public static RegistryObject<Item> IRON_ORE = ITEMS.register("iron_ore", IronOre::new);
    //模具
    public static RegistryObject<Item> SWORD_MOULD = ITEMS.register("sword_mould", SwordMould::new);
    public static RegistryObject<Item> BOW_MOULD = ITEMS.register("bow_mould", BowMould::new);
    //武器
    public static RegistryObject<Item> NOVICE_SWORD = ITEMS.register("novice_sword", NoviceSword::new);
    public static RegistryObject<Item> NOVICE_BOW = ITEMS.register("novice_bow", NoviceBow::new);
    public static RegistryObject<Item> SWORD_GUAKE = ITEMS.register("sword_guake", SwordGuake::new);
    //方块物品
    public static RegistryObject<Item> SMITHING_BLOCK = ITEMS.register("smithing_block", () -> new BlockItem(BlockRegistry.SMITHING_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));

}
