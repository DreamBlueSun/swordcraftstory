package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.combat.close.sword.*;
import com.marisa.swordcraftstory.item.combat.ranged.bow.*;
import com.marisa.swordcraftstory.item.material.DopaticStone;
import com.marisa.swordcraftstory.item.material.GrassLeaves;
import com.marisa.swordcraftstory.item.material.OmegaStone;
import com.marisa.swordcraftstory.item.material.SoftLeather;
import com.marisa.swordcraftstory.item.mould.close.sword.SwordMould;
import com.marisa.swordcraftstory.item.mould.ranged.bow.BowMould;
import com.marisa.swordcraftstory.item.ore.*;
import com.marisa.swordcraftstory.item.repair.HardStone;
import com.marisa.swordcraftstory.item.repair.SharpStone;
import com.marisa.swordcraftstory.item.repair.knifeStone;
import com.marisa.swordcraftstory.item.reply.Woundplast;
import com.marisa.swordcraftstory.item.special.Hammer;
import com.marisa.swordcraftstory.item.special.SwordGuake;
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
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Story.MOD_ID);
    //锻冶
    public static RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);
    //模具
    public static RegistryObject<Item> SWORD_MOULD = ITEMS.register("sword_mould", SwordMould::new);
    public static RegistryObject<Item> BOW_MOULD = ITEMS.register("bow_mould", BowMould::new);
    //武器-特殊
    public static RegistryObject<Item> SWORD_GUAKE = ITEMS.register("sword_guake", SwordGuake::new);
    //武器-剑
    public static RegistryObject<Item> NOVICE_SWORD = ITEMS.register("novice_sword", NoviceSword::new);
    public static RegistryObject<Item> THIN_SWORD = ITEMS.register("thin_sword", ThinSword::new);
    public static RegistryObject<Item> SOLEMNITY_SWORD = ITEMS.register("solemnity_sword", SolemnitySword::new);
    public static RegistryObject<Item> IRON_SWORD = ITEMS.register("iron_sword", IronSword::new);
    public static RegistryObject<Item> SYNTHESIS_SWORD = ITEMS.register("synthesis_sword", SynthesisSword::new);
    public static RegistryObject<Item> FEATHER_SWORD = ITEMS.register("feather_sword", FeatherSword::new);
    public static RegistryObject<Item> ELEGANCE_SWORD = ITEMS.register("elegance_sword", EleganceSword::new);
    public static RegistryObject<Item> RIBBON_SWORD = ITEMS.register("ribbon_sword", RibbonSword::new);
    //武器-弓
    public static RegistryObject<Item> NOVICE_BOW = ITEMS.register("novice_bow", NoviceBow::new);
    public static RegistryObject<Item> THIN_BOW = ITEMS.register("thin_bow", ThinBow::new);
    public static RegistryObject<Item> SOLEMNITY_BOW = ITEMS.register("solemnity_bow", SolemnityBow::new);
    public static RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow", IronBow::new);
    public static RegistryObject<Item> SYNTHESIS_BOW = ITEMS.register("synthesis_bow", SynthesisBow::new);
    public static RegistryObject<Item> FEATHER_BOW = ITEMS.register("feather_bow", FeatherBow::new);
    public static RegistryObject<Item> ELEGANCE_BOW = ITEMS.register("elegance_bow", EleganceBow::new);
    public static RegistryObject<Item> RIBBON_BOW = ITEMS.register("ribbon_bow", RibbonBow::new);
    //方块物品
    public static RegistryObject<Item> WEAPON_MAKE_BLOCK = ITEMS.register("weapon_make_block", () -> new BlockItem(BlockRegistry.WEAPON_MAKE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> SMITHING_BLOCK = ITEMS.register("smithing_block", () -> new BlockItem(BlockRegistry.SMITHING_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_COLLAPSE_BLOCK = ITEMS.register("weapon_collapse_block", () -> new BlockItem(BlockRegistry.WEAPON_COLLAPSE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> REPAIR_BLOCK = ITEMS.register("repair_block", () -> new BlockItem(BlockRegistry.REPAIR_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    //修理道具
    public static RegistryObject<Item> SHARP_STONE = ITEMS.register("sharp_stone", SharpStone::new);
    public static RegistryObject<Item> KNIFE_STONE = ITEMS.register("knife_stone", knifeStone::new);
    public static RegistryObject<Item> HARD_STONE = ITEMS.register("hard_stone", HardStone::new);
    //回复道具
    public static RegistryObject<Item> WOUNDPLAST = ITEMS.register("woundplast", Woundplast::new);
    //素材道具
    public static RegistryObject<Item> OMEGA_STONE = ITEMS.register("omega_stone", OmegaStone::new);
    public static RegistryObject<Item> DOPATIC_STONE = ITEMS.register("dopatic_stone", DopaticStone::new);
    public static RegistryObject<Item> GRASS_LEAVES = ITEMS.register("grass_leaves", GrassLeaves::new);
    public static RegistryObject<Item> SOFT_LEATHER = ITEMS.register("soft_leather", SoftLeather::new);
    //素材矿石
    public static RegistryObject<Item> IRON_ORE = ITEMS.register("iron_ore", IronOre::new);
    public static RegistryObject<Item> SLANDER_ORE = ITEMS.register("slander_ore", SlanderOre::new);
    public static RegistryObject<Item> ARGENIR_ORE = ITEMS.register("argenir_ore", ArgenirOre::new);
    public static RegistryObject<Item> BIG_ORE = ITEMS.register("big_ore", BigOre::new);
    public static RegistryObject<Item> ELPHUS_ORE = ITEMS.register("elphus_ore", ElphusOre::new);
    public static RegistryObject<Item> WINGS_FOSSIL_ORE = ITEMS.register("wings_fossil_ore", WingsFossilOre::new);
    public static RegistryObject<Item> UKA_ORE = ITEMS.register("uka_ore", UkaOre::new);
    public static RegistryObject<Item> PRETTY_ORE = ITEMS.register("pretty_ore", PrettyOre::new);

}
