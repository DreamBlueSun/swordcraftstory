package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.intensify.*;
import com.marisa.swordcraftstory.item.material.*;
import com.marisa.swordcraftstory.item.mould.close.axe.AxeMould;
import com.marisa.swordcraftstory.item.mould.close.sword.SwordMould;
import com.marisa.swordcraftstory.item.mould.ranged.bow.BowMould;
import com.marisa.swordcraftstory.item.ore.*;
import com.marisa.swordcraftstory.item.repair.HardStone;
import com.marisa.swordcraftstory.item.repair.SharpStone;
import com.marisa.swordcraftstory.item.repair.knifeStone;
import com.marisa.swordcraftstory.item.reply.JiJaMedicine;
import com.marisa.swordcraftstory.item.reply.JuYuMedicine;
import com.marisa.swordcraftstory.item.reply.Woundplast;
import com.marisa.swordcraftstory.item.special.Hammer;
import com.marisa.swordcraftstory.item.special.LuckTicket;
import com.marisa.swordcraftstory.item.special.SwordGuake;
import com.marisa.swordcraftstory.item.weapon.axe.ElementsManaAxe;
import com.marisa.swordcraftstory.item.weapon.base.taila.DragonSlaveSword;
import com.marisa.swordcraftstory.item.weapon.base.taila.ElementsManaSword;
import com.marisa.swordcraftstory.item.weapon.base.taila.TimeUmbrellaSword;
import com.marisa.swordcraftstory.item.weapon.base.taila.UnlimitedEarthSword;
import com.marisa.swordcraftstory.item.weapon.base.taila.bow.ConstantBlowersSnowBow;
import com.marisa.swordcraftstory.item.weapon.close.axe.*;
import com.marisa.swordcraftstory.item.weapon.close.sword.*;
import com.marisa.swordcraftstory.item.weapon.ranged.bow.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 物品注册
 */

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Story.MOD_ID);
    //锻冶
    public static RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);
    //特殊道具
    public static RegistryObject<Item> LUCK_TICKET = ITEMS.register("luck_ticket", LuckTicket::new);
    //模具
    public static RegistryObject<Item> SWORD_MOULD = ITEMS.register("sword_mould", SwordMould::new);
    public static RegistryObject<Item> BOW_MOULD = ITEMS.register("bow_mould", BowMould::new);
    public static RegistryObject<Item> AXE_MOULD = ITEMS.register("axe_mould", AxeMould::new);
    //武器-特殊
    public static RegistryObject<Item> SWORD_GUAKE = ITEMS.register("sword_guake", SwordGuake::new);
    //武器-剑
    public static RegistryObject<Item> NOVICE_SWORD = ITEMS.register("novice_sword", NoviceSword::new);
    public static RegistryObject<Item> THIN_SWORD = ITEMS.register("thin_sword", ThinSword::new);
    public static RegistryObject<Item> HARD_SWORD = ITEMS.register("hard_sword", HardSword::new);
    public static RegistryObject<Item> SOLEMNITY_SWORD = ITEMS.register("solemnity_sword", SolemnitySword::new);
    public static RegistryObject<Item> IRON_SWORD = ITEMS.register("iron_sword", IronSword::new);
    public static RegistryObject<Item> SYNTHESIS_SWORD = ITEMS.register("synthesis_sword", SynthesisSword::new);
    public static RegistryObject<Item> FEATHER_SWORD = ITEMS.register("feather_sword", FeatherSword::new);
    public static RegistryObject<Item> CUT_IRON_SWORD = ITEMS.register("cut_iron_sword", CutIronSword::new);
    public static RegistryObject<Item> WIDE_SWORD = ITEMS.register("wide_sword", WideSword::new);
    public static RegistryObject<Item> SEVERE_PENALTY_SWORD = ITEMS.register("severe_penalty_sword", SeverePenaltySword::new);
    public static RegistryObject<Item> RELICT_SWORD = ITEMS.register("relict_sword", RelictSword::new);
    public static RegistryObject<Item> ELEGANCE_SWORD = ITEMS.register("elegance_sword", EleganceSword::new);
    public static RegistryObject<Item> RIBBON_SWORD = ITEMS.register("ribbon_sword", RibbonSword::new);
    public static RegistryObject<Item> CARAPACE_FOSSIL_SWORD = ITEMS.register("carapace_fossil_sword", CarapaceFossilSword::new);
    public static RegistryObject<Item> FANTASY_SWORD = ITEMS.register("fantasy_sword", FantasySword::new);
    public static RegistryObject<Item> GALIA_SWORD = ITEMS.register("galia_sword", GaliaSword::new);
    public static RegistryObject<Item> CUISINE_SWORD = ITEMS.register("cuisine_sword", CuisineSword::new);
    public static RegistryObject<Item> DOUBLE_SNAKE_SWORD = ITEMS.register("double_snake_sword", DoubleSnakeSword::new);
    public static RegistryObject<Item> SPRINT_SWORD = ITEMS.register("sprint_sword", SprintSword::new);
    public static RegistryObject<Item> XCEL_SWORD = ITEMS.register("xcel_sword", XcelSword::new);
    public static RegistryObject<Item> LEIJI_STOWE_SWORD = ITEMS.register("leiji_stowe_sword", LeijiStoweSword::new);
    public static RegistryObject<Item> DEW_FIREFLY_SWORD = ITEMS.register("dew_firefly_sword", DewFireflySword::new);
    public static RegistryObject<Item> STEEL_TEETH_SWORD = ITEMS.register("steel_teeth_sword", SteelTeethSword::new);
    public static RegistryObject<Item> CATS_PAW_SWORD = ITEMS.register("cats_paw_sword", CatsPawSword::new);
    public static RegistryObject<Item> LUMINOSITY_SWORD = ITEMS.register("luminosity_sword", LuminositySword::new);
    //武器-弓
    public static RegistryObject<Item> NOVICE_BOW = ITEMS.register("novice_bow", NoviceBow::new);
    public static RegistryObject<Item> THIN_BOW = ITEMS.register("thin_bow", ThinBow::new);
    public static RegistryObject<Item> HARD_BOW = ITEMS.register("hard_bow", HardBow::new);
    public static RegistryObject<Item> SOLEMNITY_BOW = ITEMS.register("solemnity_bow", SolemnityBow::new);
    public static RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow", IronBow::new);
    public static RegistryObject<Item> SYNTHESIS_BOW = ITEMS.register("synthesis_bow", SynthesisBow::new);
    public static RegistryObject<Item> FEATHER_BOW = ITEMS.register("feather_bow", FeatherBow::new);
    public static RegistryObject<Item> CUT_IRON_BOW = ITEMS.register("cut_iron_bow", CutIronBow::new);
    public static RegistryObject<Item> HUGER_BOW = ITEMS.register("huger_bow", HugerBow::new);
    public static RegistryObject<Item> SEVERE_PENALTY_BOW = ITEMS.register("severe_penalty_bow", SeverePenaltyBow::new);
    public static RegistryObject<Item> RELICT_BOW = ITEMS.register("relict_bow", RelictBow::new);
    public static RegistryObject<Item> ELEGANCE_BOW = ITEMS.register("elegance_bow", EleganceBow::new);
    public static RegistryObject<Item> RIBBON_BOW = ITEMS.register("ribbon_bow", RibbonBow::new);
    public static RegistryObject<Item> CARAPACE_FOSSIL_BOW = ITEMS.register("carapace_fossil_bow", CarapaceFossilBow::new);
    public static RegistryObject<Item> FANTASY_BOW = ITEMS.register("fantasy_bow", FantasyBow::new);
    public static RegistryObject<Item> GALIA_BOW = ITEMS.register("galia_bow", GaliaBow::new);
    public static RegistryObject<Item> CUISINE_BOW = ITEMS.register("cuisine_bow", CuisineBow::new);
    public static RegistryObject<Item> DOUBLE_SNAKE_BOW = ITEMS.register("double_snake_bow", DoubleSnakeBow::new);
    public static RegistryObject<Item> SPRINT_BOW = ITEMS.register("sprint_bow", SprintBow::new);
    public static RegistryObject<Item> XCEL_BOW = ITEMS.register("xcel_bow", XcelBow::new);
    public static RegistryObject<Item> LEIJI_STOWE_BOW = ITEMS.register("leiji_stowe_bow", LeijiStoweBow::new);
    public static RegistryObject<Item> DEW_FIREFLY_BOW = ITEMS.register("dew_firefly_bow", DewFireflyBow::new);
    public static RegistryObject<Item> STEEL_TEETH_BOW = ITEMS.register("steel_teeth_bow", SteelTeethBow::new);
    public static RegistryObject<Item> CATS_PAW_BOW = ITEMS.register("cats_paw_bow", CatsPawBow::new);
    public static RegistryObject<Item> LUMINOSITY_BOW = ITEMS.register("luminosity_bow", LuminosityBow::new);
    //武器-斧
    public static RegistryObject<Item> NOVICE_AXE = ITEMS.register("novice_axe", NoviceAxe::new);
    public static RegistryObject<Item> THIN_AXE = ITEMS.register("thin_axe", ThinAxe::new);
    public static RegistryObject<Item> HARD_AXE = ITEMS.register("hard_axe", HardAxe::new);
    public static RegistryObject<Item> SOLEMNITY_AXE = ITEMS.register("solemnity_axe", SolemnityAxe::new);
    public static RegistryObject<Item> IRON_AXE = ITEMS.register("iron_axe", IronAxe::new);
    public static RegistryObject<Item> SYNTHESIS_AXE = ITEMS.register("synthesis_axe", SynthesisAxe::new);
    public static RegistryObject<Item> FEATHER_AXE = ITEMS.register("feather_axe", FeatherAxe::new);
    public static RegistryObject<Item> CUT_IRON_AXE = ITEMS.register("cut_iron_axe", CutIronAxe::new);
    public static RegistryObject<Item> WIDE_AXE = ITEMS.register("wide_axe", WideAxe::new);
    public static RegistryObject<Item> SEVERE_PENALTY_AXE = ITEMS.register("severe_penalty_axe", SeverePenaltyAxe::new);
    public static RegistryObject<Item> RELICT_AXE = ITEMS.register("relict_axe", RelictAxe::new);
    public static RegistryObject<Item> ELEGANCE_AXE = ITEMS.register("elegance_axe", EleganceAxe::new);
    public static RegistryObject<Item> RIBBON_AXE = ITEMS.register("ribbon_axe", RibbonAxe::new);
    public static RegistryObject<Item> CARAPACE_FOSSIL_AXE = ITEMS.register("carapace_fossil_axe", CarapaceFossilAxe::new);
    public static RegistryObject<Item> FANTASY_AXE = ITEMS.register("fantasy_axe", FantasyAxe::new);
    public static RegistryObject<Item> GALIA_AXE = ITEMS.register("galia_axe", GaliaAxe::new);
    public static RegistryObject<Item> CUISINE_AXE = ITEMS.register("cuisine_axe", CuisineAxe::new);
    public static RegistryObject<Item> DOUBLE_SNAKE_AXE = ITEMS.register("double_snake_axe", DoubleSnakeAxe::new);
    public static RegistryObject<Item> SPRINT_AXE = ITEMS.register("sprint_axe", SprintAxe::new);
    public static RegistryObject<Item> XCEL_AXE = ITEMS.register("xcel_axe", XcelAxe::new);
    public static RegistryObject<Item> LEIJI_STOWE_AXE = ITEMS.register("leiji_stowe_axe", LeijiStoweAxe::new);
    public static RegistryObject<Item> DEW_FIREFLY_AXE = ITEMS.register("dew_firefly_axe", DewFireflyAxe::new);
    public static RegistryObject<Item> STEEL_TEETH_AXE = ITEMS.register("steel_teeth_axe", SteelTeethAxe::new);
    public static RegistryObject<Item> CATS_PAW_AXE = ITEMS.register("cats_paw_axe", CatsPawAxe::new);
    public static RegistryObject<Item> LUMINOSITY_AXE = ITEMS.register("luminosity_axe", LuminosityAxe::new);
    //方块物品
    public static RegistryObject<Item> STORY_ORE_BLOCK = ITEMS.register("story_ore_block", () -> new BlockItem(BlockRegistry.STORY_ORE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_MAKE_BLOCK = ITEMS.register("weapon_make_block", () -> new BlockItem(BlockRegistry.WEAPON_MAKE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_INTENSIFY_BLOCK = ITEMS.register("weapon_intensify_block", () -> new BlockItem(BlockRegistry.WEAPON_INTENSIFY_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_EDGE_BLOCK = ITEMS.register("weapon_edge_block", () -> new BlockItem(BlockRegistry.WEAPON_EDGE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_COLLAPSE_BLOCK = ITEMS.register("weapon_collapse_block", () -> new BlockItem(BlockRegistry.WEAPON_COLLAPSE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> REPAIR_BLOCK = ITEMS.register("repair_block", () -> new BlockItem(BlockRegistry.REPAIR_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> WEAPON_MODEL_CHANGE_BLOCK = ITEMS.register("weapon_model_change_block", () -> new BlockItem(BlockRegistry.WEAPON_MODEL_CHANGE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    public static RegistryObject<Item> MANUAL_LOTTERY_MACHINE_BLOCK = ITEMS.register("manual_lottery_machine_block", () -> new BlockItem(BlockRegistry.MANUAL_LOTTERY_MACHINE_BLOCK.get(), new Item.Properties().group(StoryGroup.COMBAT_GROUP)));
    //修理道具
    public static RegistryObject<Item> SHARP_STONE = ITEMS.register("sharp_stone", SharpStone::new);
    public static RegistryObject<Item> KNIFE_STONE = ITEMS.register("knife_stone", knifeStone::new);
    public static RegistryObject<Item> HARD_STONE = ITEMS.register("hard_stone", HardStone::new);
    //回复道具
    public static RegistryObject<Item> WOUNDPLAST = ITEMS.register("woundplast", Woundplast::new);
    public static RegistryObject<Item> JI_JA_MEDICINE = ITEMS.register("ji_ja_medicine", JiJaMedicine::new);
    public static RegistryObject<Item> JU_YU_MEDICINE = ITEMS.register("ju_yu_medicine", JuYuMedicine::new);
    //素材道具
    public static RegistryObject<Item> OMEGA_STONE = ITEMS.register("omega_stone", OmegaStone::new);
    public static RegistryObject<Item> DOPATIC_STONE = ITEMS.register("dopatic_stone", DopaticStone::new);
    public static RegistryObject<Item> JI_JA_FRUIT = ITEMS.register("ji_ja_fruit", JiJaFruit::new);
    public static RegistryObject<Item> JU_YU_FRUIT = ITEMS.register("ju_yu_fruit", JuYuFruit::new);
    public static RegistryObject<Item> GRASS_LEAVES = ITEMS.register("grass_leaves", GrassLeaves::new);
    public static RegistryObject<Item> SOFT_LEAVES = ITEMS.register("soft_leaves", SoftLeaves::new);
    public static RegistryObject<Item> SWEET_HONEY = ITEMS.register("sweet_honey", SweetHoney::new);
    public static RegistryObject<Item> SOFT_LEATHER = ITEMS.register("soft_leather", SoftLeather::new);
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
    //强化道具
    public static RegistryObject<Item> LEGEND_IRON_BUNCH = ITEMS.register("legend_iron_bunch", LegendIronBunch::new);
    public static RegistryObject<Item> HEART_GLASSES = ITEMS.register("heart_glasses", HeartGlasses::new);
    public static RegistryObject<Item> MOMENTUM_HEAD_BAND = ITEMS.register("momentum_head_band", MomentumHeadband::new);
    public static RegistryObject<Item> POWER_CERTIFICATE = ITEMS.register("power_certificate", PowerCertificate::new);
    public static RegistryObject<Item> TALENT_CERTIFICATE = ITEMS.register("talent_certificate", TalentCertificate::new);
    //幻化武器-泰拉-剑
    public static RegistryObject<Item> DRAGON_SLAVE_SWORD = ITEMS.register("dragon_slave_sword", DragonSlaveSword::new);
    public static RegistryObject<Item> UNLIMITED_EARTH_SWORD = ITEMS.register("unlimited_earth_sword", UnlimitedEarthSword::new);
    public static RegistryObject<Item> ELEMENTS_MANA_SWORD = ITEMS.register("elements_mana_sword", ElementsManaSword::new);
    public static RegistryObject<Item> TIME_UMBRELLA_SWORD = ITEMS.register("time_umbrella_sword", TimeUmbrellaSword::new);
    //幻化武器-泰拉-弓
    public static RegistryObject<Item> CONSTANT_BLOWERS_SNOW_BOW = ITEMS.register("constant_blowers_snow_bow", ConstantBlowersSnowBow::new);
    //幻化武器-泰拉-斧
    public static RegistryObject<Item> ELEMENTS_MANA_AXE = ITEMS.register("elements_mana_axe", ElementsManaAxe::new);
}
