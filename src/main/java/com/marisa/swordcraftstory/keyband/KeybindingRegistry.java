package com.marisa.swordcraftstory.keyband;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * 按键绑定注册
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegistry {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBoardInput.STORY_STATUS_KEY));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBoardInput.WEAPON_SKILL_CONFIG_KEY));
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyBoardInput.WEAPON_SKILL_LEARN_KEY));
    }
}
