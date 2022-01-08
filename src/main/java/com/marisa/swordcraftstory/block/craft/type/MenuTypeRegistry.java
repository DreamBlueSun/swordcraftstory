package com.marisa.swordcraftstory.block.craft.type;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemCollapseMenu;
import com.marisa.swordcraftstory.block.craft.menu.ItemEdgeMenu;
import com.marisa.swordcraftstory.block.craft.menu.ItemMakeMenu;
import com.marisa.swordcraftstory.block.craft.menu.RankUpMenu;
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

    public static final RegistryObject<MenuType<RankUpMenu>> TYPE_RANK_UP = MENU_TYPES.register("rank_up_menu_type", () -> IForgeMenuType.create(RankUpMenu::new));
    public static final RegistryObject<MenuType<ItemMakeMenu>> TYPE_ITEM_MAKE = MENU_TYPES.register("item_make_menu_type", () -> IForgeMenuType.create(ItemMakeMenu::new));
    public static final RegistryObject<MenuType<ItemCollapseMenu>> TYPE_ITEM_COLLAPSE = MENU_TYPES.register("item_collapse_menu_type", () -> IForgeMenuType.create(ItemCollapseMenu::new));
    public static final RegistryObject<MenuType<ItemEdgeMenu>> TYPE_ITEM_EDGE = MENU_TYPES.register("item_edge_menu_type", () -> IForgeMenuType.create(ItemEdgeMenu::new));
}
