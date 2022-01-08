package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.block.craft.screen.ItemCollapseScreen;
import com.marisa.swordcraftstory.block.craft.screen.ItemMakeScreen;
import com.marisa.swordcraftstory.block.craft.screen.RankUpScreen;
import com.marisa.swordcraftstory.block.craft.type.MenuTypeRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * 客户端事件监听处理器
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        //注册Screen
        event.enqueueWork(() -> MenuScreens.register(MenuTypeRegistry.TYPE_RANK_UP.get(), RankUpScreen::new));
        event.enqueueWork(() -> MenuScreens.register(MenuTypeRegistry.TYPE_ITEM_MAKE.get(), ItemMakeScreen::new));
        event.enqueueWork(() -> MenuScreens.register(MenuTypeRegistry.TYPE_ITEM_COLLAPSE.get(), ItemCollapseScreen::new));
    }
}
