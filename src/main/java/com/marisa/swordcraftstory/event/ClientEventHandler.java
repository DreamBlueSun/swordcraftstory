package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.entity.EntityTypeRegistry;
import com.marisa.swordcraftstory.entity.projectile.rander.AirCutterRender;
import com.marisa.swordcraftstory.gui.container.ContainerTypeRegistry;
import com.marisa.swordcraftstory.gui.screen.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.WEAPON_MAKE_CONTAINER.get(), WeaponMakeScreen::new));
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.WEAPON_INTENSIFY_CONTAINER.get(), WeaponIntensifyScreen::new));
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.WEAPON_EDGE_CONTAINER.get(), WeaponEdgeScreen::new));
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.WEAPON_COLLAPSE_CONTAINER.get(), WeaponCollapseScreen::new));
        event.enqueueWork(() -> ScreenManager.registerFactory(ContainerTypeRegistry.WEAPON_MODEL_CHANGE_CONTAINER.get(), WeaponModelChangeScreen::new));
    }

    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        //注册实体的渲染
//        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.SLIME.get(), SlimeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.AIR_CUTTER.get(), AirCutterRender::new);
    }
}
