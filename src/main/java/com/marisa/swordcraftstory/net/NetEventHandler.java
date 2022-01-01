package com.marisa.swordcraftstory.net;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * 事件监听处理器
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetEventHandler {

    /**
     * 注册通信
     */
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Networking::registerPlayerData);
        event.enqueueWork(Networking::registerQualityIdentification);
        event.enqueueWork(Networking::registerFriendsData);
    }
}
