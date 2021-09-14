//package com.marisa.swordcraftstory.event;
//
//import com.marisa.swordcraftstory.entity.EntityTypeRegistry;
//import com.marisa.swordcraftstory.entity.rander.RockLizardRender;
//import com.marisa.swordcraftstory.entity.rander.SlimeRender;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.client.registry.RenderingRegistry;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//
///**
// * 客户端事件监听处理器
// */
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class ClientEventHandler {
//
//    @SubscribeEvent
//    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
//        //注册实体的渲染
//        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.SLIME.get(), SlimeRender::new);
//        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.ROCK_LIZARD.get(), RockLizardRender::new);
//    }
//}
