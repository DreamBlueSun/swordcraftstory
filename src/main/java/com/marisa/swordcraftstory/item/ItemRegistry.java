package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.block.ore.drop.*;
import com.marisa.swordcraftstory.group.StoryGroup;
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
    public static RegistryObject<Item> RANK_UP_BLOCK = ITEMS.register("rank_up_block", () -> new BlockItem(BlockRegistry.RANK_UP_BLOCK, new Item.Properties().tab(StoryGroup.MAIN)));
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
