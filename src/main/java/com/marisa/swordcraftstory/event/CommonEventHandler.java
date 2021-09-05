package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.net.Networking;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @description: 注册通信
 * @date: 2021/9/5 0005 12:59
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        Networking.registerMessage();
    }
}
