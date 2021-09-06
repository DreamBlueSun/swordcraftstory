package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.net.Networking;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @description:
 * @date: 2021/9/5 0005 12:59
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {

    /**
     * 注册通信
     */
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        Networking.registerMessage();
    }

    /**
     * 监听实体生成，注入属性
     */
//    @SubscribeEvent
//    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
//        event.put(EntityTypeRegistry.ROCK_LIZARD.get(), RockLizard.getAttributes().create());
//    }
}
