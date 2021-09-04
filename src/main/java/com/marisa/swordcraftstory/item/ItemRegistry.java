package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.item.combat.close.SwordGuake;
import com.marisa.swordcraftstory.item.combat.close.SwordNovice;
import com.marisa.swordcraftstory.item.combat.ranged.BowNovice;
import com.marisa.swordcraftstory.item.mould.close.SwordMould;
import com.marisa.swordcraftstory.item.mould.ranged.BowMould;
import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.item.special.Hammer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description:
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
    public static RegistryObject<Item> SWORD_NOVICE = ITEMS.register("sword_novice", SwordNovice::new);
    public static RegistryObject<Item> BOW_NOVICE = ITEMS.register("bow_novice", BowNovice::new);
    public static RegistryObject<Item> SWORD_GUAKE = ITEMS.register("sword_guake", SwordGuake::new);
    //GUI测试
//    public static RegistryObject<Item> GUI_TEST = ITEMS.register("gui_test", GuiTest::new);

}
