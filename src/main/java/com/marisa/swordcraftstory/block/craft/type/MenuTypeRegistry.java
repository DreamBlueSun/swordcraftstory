package com.marisa.swordcraftstory.block.craft.type;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * MenuTypeRegistry
 */

public class MenuTypeRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Story.MOD_ID);

    public static final RegistryObject<MenuType<ItemMakeMenu>> TYPE_ITEM_MAKE = MENU_TYPES.register("item_make_menu_type", () -> IForgeMenuType.create(ItemMakeMenu::new));
    public static final RegistryObject<MenuType<ItemStrengthenMenu>> TYPE_ITEM_STRENGTHEN = MENU_TYPES.register("item_strengthen_menu_type", () -> IForgeMenuType.create(ItemStrengthenMenu::new));
    public static final RegistryObject<MenuType<ItemEdgeMenu>> TYPE_ITEM_EDGE = MENU_TYPES.register("item_edge_menu_type", () -> IForgeMenuType.create(ItemEdgeMenu::new));
    public static final RegistryObject<MenuType<ItemCollapseMenu>> TYPE_ITEM_COLLAPSE = MENU_TYPES.register("item_collapse_menu_type", () -> IForgeMenuType.create(ItemCollapseMenu::new));
    public static final RegistryObject<MenuType<ItemImbueMagicMenu>> TYPE_ITEM_IMBUE_MAGIC = MENU_TYPES.register("item_imbue_magic_menu_type", () -> IForgeMenuType.create(ItemImbueMagicMenu::new));
}
