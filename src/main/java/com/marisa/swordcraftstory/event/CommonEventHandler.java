package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.net.Networking;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * 公共事件监听处理器
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {

    /**
     * 注册通信
     */
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Networking::register1);
        event.enqueueWork(Networking::register2);
        event.enqueueWork(Networking::register3);
    }

    /**
     * 监听实体生成，注入属性
     */
//    @SubscribeEvent
//    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
//        event.put(EntityTypeRegistry.ROCK_LIZARD.get(), RockLizard.getAttributes().create());
//    }
}
