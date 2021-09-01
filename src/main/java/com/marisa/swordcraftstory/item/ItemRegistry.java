package com.marisa.swordcraftstory.item;

import com.marisa.swordcraftstory.item.combat.BowMould;
import com.marisa.swordcraftstory.item.combat.SwordMould;
import com.marisa.swordcraftstory.item.combat.SwordNovice;
import com.marisa.swordcraftstory.item.special.GuiTest;
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


    public static RegistryObject<Item> HAMMER = ITEMS.register("hammer", Hammer::new);


    public static RegistryObject<Item> SWORD_MOULD = ITEMS.register("sword_mould", SwordMould::new);
    public static RegistryObject<Item> BOW_MOULD = ITEMS.register("bow_mould", BowMould::new);


    public static RegistryObject<Item> SWORD_NOVICE = ITEMS.register("sword_novice", SwordNovice::new);



    public static RegistryObject<Item> GUI_TEST = ITEMS.register("gui_test", GuiTest::new);

}
