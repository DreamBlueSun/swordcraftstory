package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.edge.*;
import com.marisa.swordcraftstory.item.material.*;
import com.marisa.swordcraftstory.item.mould.*;
import com.marisa.swordcraftstory.item.ore.drop.*;
import com.marisa.swordcraftstory.item.repair.HardStone;
import com.marisa.swordcraftstory.item.repair.SharpStone;
import com.marisa.swordcraftstory.item.repair.knifeStone;
import com.marisa.swordcraftstory.item.reply.JiJaMedicine;
import com.marisa.swordcraftstory.item.reply.JuYuMedicine;
import com.marisa.swordcraftstory.item.reply.Woundplast;
import com.marisa.swordcraftstory.item.special.LuckTicket;
import com.marisa.swordcraftstory.item.special.LuckTicketMax;
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
    public static RegistryObject<Item> ITEM_MAKE_BLOCK = ITEMS.register("item_make_block", () -> new BlockItem(BlockRegistry.ITEM_MAKE_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ITEM_STRENGTHEN_BLOCK = ITEMS.register("item_strengthen_block", () -> new BlockItem(BlockRegistry.ITEM_STRENGTHEN_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ITEM_EDGE_BLOCK = ITEMS.register("item_edge_block", () -> new BlockItem(BlockRegistry.ITEM_EDGE_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ITEM_COLLAPSE_BLOCK = ITEMS.register("item_collapse_block", () -> new BlockItem(BlockRegistry.ITEM_COLLAPSE_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> REPAIR_BLOCK = ITEMS.register("repair_block", () -> new BlockItem(BlockRegistry.REPAIR_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ITEM_IMBUE_MAGIC_BLOCK = ITEMS.register("item_imbue_magic_block", () -> new BlockItem(BlockRegistry.ITEM_IMBUE_MAGIC_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> MANUAL_LOTTERY_MACHINE_BLOCK = ITEMS.register("manual_lottery_machine_block", () -> new BlockItem(BlockRegistry.MANUAL_LOTTERY_MACHINE_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
    //特殊道具
    public static RegistryObject<Item> LUCK_TICKET = ITEMS.register("luck_ticket", LuckTicket::new);
    public static RegistryObject<Item> LUCK_TICKET_MAX = ITEMS.register("luck_ticket_max", LuckTicketMax::new);
    //回复道具
    public static RegistryObject<Item> WOUNDPLAST = ITEMS.register("woundplast", Woundplast::new);
    public static RegistryObject<Item> JI_JA_MEDICINE = ITEMS.register("ji_ja_medicine", JiJaMedicine::new);
    public static RegistryObject<Item> JU_YU_MEDICINE = ITEMS.register("ju_yu_medicine", JuYuMedicine::new);
    //修理道具
    public static RegistryObject<Item> SHARP_STONE = ITEMS.register("sharp_stone", SharpStone::new);
    public static RegistryObject<Item> KNIFE_STONE = ITEMS.register("knife_stone", knifeStone::new);
    public static RegistryObject<Item> HARD_STONE = ITEMS.register("hard_stone", HardStone::new);
    //强刃道具
    public static RegistryObject<Item> ORE_EDGE_ATK = ITEMS.register("ore_edge_atk", OreEdgeAtk::new);
    public static RegistryObject<Item> ORE_EDGE_DEF = ITEMS.register("ore_edge_def", OreEdgeDef::new);
    public static RegistryObject<Item> ORE_EDGE_PHY = ITEMS.register("ore_edge_phy", OreEdgePhy::new);
    public static RegistryObject<Item> ORE_EDGE_AGL = ITEMS.register("ore_edge_agl", OreEdgeAgl::new);
    public static RegistryObject<Item> ORE_EDGE_DUR = ITEMS.register("ore_edge_dur", OreEdgeDur::new);
    //素材道具
    public static RegistryObject<Item> JI_JA_FRUIT = ITEMS.register("ji_ja_fruit", JiJaFruit::new);
    public static RegistryObject<Item> JU_YU_FRUIT = ITEMS.register("ju_yu_fruit", JuYuFruit::new);
    public static RegistryObject<Item> GRASS_LEAVES = ITEMS.register("grass_leaves", GrassLeaves::new);
    public static RegistryObject<Item> SOFT_LEAVES = ITEMS.register("soft_leaves", SoftLeaves::new);
    public static RegistryObject<Item> SWEET_HONEY = ITEMS.register("sweet_honey", SweetHoney::new);
    public static RegistryObject<Item> SOFT_LEATHER = ITEMS.register("soft_leather", SoftLeather::new);
    public static RegistryObject<Item> OMEGA_STONE = ITEMS.register("omega_stone", OmegaStone::new);
    public static RegistryObject<Item> DOPATIC_STONE = ITEMS.register("dopatic_stone", DopaticStone::new);
    //模具
    public static RegistryObject<Item> SWORD_MOULD = ITEMS.register("sword_mould", SwordMould::new);
    public static RegistryObject<Item> AXE_MOULD = ITEMS.register("axe_mould", AxeMould::new);
    public static RegistryObject<Item> PICKAXE_MOULD = ITEMS.register("pickaxe_mould", PickaxeMould::new);
    public static RegistryObject<Item> BOW_MOULD = ITEMS.register("bow_mould", BowMould::new);
    public static RegistryObject<Item> CROSSBOW_MOULD = ITEMS.register("crossbow_mould", CrossbowMould::new);
    public static RegistryObject<Item> TRIDENT_MOULD = ITEMS.register("trident_mould", TridentMould::new);
    public static RegistryObject<Item> HEAD_MOULD = ITEMS.register("head_mould", HeadMould::new);
    public static RegistryObject<Item> CHEST_MOULD = ITEMS.register("chest_mould", ChestMould::new);
    public static RegistryObject<Item> LEGS_MOULD = ITEMS.register("legs_mould", LegsMould::new);
    public static RegistryObject<Item> FEET_MOULD = ITEMS.register("feet_mould", FeetMould::new);
    //素材矿石
    public static RegistryObject<Item> IRON_ORE = ITEMS.register("iron_ore", IronOre::new);
    public static RegistryObject<Item> SLANDER_ORE = ITEMS.register("slander_ore", SlanderOre::new);
    public static RegistryObject<Item> HARD_ORE = ITEMS.register("hard_ore", HardOre::new);
    public static RegistryObject<Item> ARGENIR_ORE = ITEMS.register("argenir_ore", ArgenirOre::new);
    public static RegistryObject<Item> BIG_ORE = ITEMS.register("big_ore", BigOre::new);
    public static RegistryObject<Item> ELPHUS_ORE = ITEMS.register("elphus_ore", ElphusOre::new);
    public static RegistryObject<Item> WINGS_FOSSIL_ORE = ITEMS.register("wings_fossil_ore", WingsFossilOre::new);
    public static RegistryObject<Item> CUT_IRON_ORE = ITEMS.register("cut_iron_ore", CutIronOre::new);
    public static RegistryObject<Item> BIG_IRON_ORE = ITEMS.register("big_iron_ore", BigIronOre::new);
    public static RegistryObject<Item> HEAVY_GOLD_ORE = ITEMS.register("heavy_gold_ore", HeavyGoldOre::new);
    public static RegistryObject<Item> REGIA_ORE = ITEMS.register("regia_ore", RegiaOre::new);
    public static RegistryObject<Item> UKA_ORE = ITEMS.register("uka_ore", UkaOre::new);
    public static RegistryObject<Item> PRETTY_ORE = ITEMS.register("pretty_ore", PrettyOre::new);
    public static RegistryObject<Item> CARAPACE_FOSSIL_ORE = ITEMS.register("carapace_fossil_ore", CarapaceFossilOre::new);
    public static RegistryObject<Item> FANTASY_ORE = ITEMS.register("fantasy_ore", FantasyOre::new);
    public static RegistryObject<Item> GALIA_ORE = ITEMS.register("galia_ore", GaliaOre::new);
    public static RegistryObject<Item> CUISINE_ORE = ITEMS.register("cuisine_ore", CuisineOre::new);
    public static RegistryObject<Item> DOUBLE_SNAKE_ORE = ITEMS.register("double_snake_ore", DoubleSnakeOre::new);
    public static RegistryObject<Item> SPRINT_ORE = ITEMS.register("sprint_ore", SprintOre::new);
    public static RegistryObject<Item> XCEL_ORE = ITEMS.register("xcel_ore", XcelOre::new);
    public static RegistryObject<Item> LEIJI_STOWE_ORE = ITEMS.register("leiji_stowe_ore", LeijiStoweOre::new);
    public static RegistryObject<Item> DEW_FIREFLY_ORE = ITEMS.register("dew_firefly_ore", DewFireflyOre::new);
    public static RegistryObject<Item> STEEL_TEETH_ORE = ITEMS.register("steel_teeth_ore", SteelTeethOre::new);
    public static RegistryObject<Item> CATS_PAW_ORE = ITEMS.register("cats_paw_ore", CatsPawOre::new);
    public static RegistryObject<Item> LUMINOSITY_ORE = ITEMS.register("luminosity_ore", LuminosityOre::new);
}
