package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.gui.container.ContainerTypeRegistry;
import com.marisa.swordcraftstory.gui.screen.IntensifyEdgeScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @description:
 * @date: 2021/9/7 0007 21:40
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.INTENSIFY_EDGE_CONTAINER.get(), IntensifyEdgeScreen::new));
    }

}
