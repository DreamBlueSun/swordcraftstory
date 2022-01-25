package com.marisa.swordcraftstory.sound;

import com.marisa.swordcraftstory.Story;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

/**
 * 声音注册
 */
@Mod.EventBusSubscriber(modid = Story.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoundRegistry {

    public static final SoundEvent SMITH_BLOCK_CRAFT = new SoundEvent(new ResourceLocation(Story.MOD_ID, "smith.block.craft"));
    public static final SoundEvent SMITH_BLOCK_REPAIR = new SoundEvent(new ResourceLocation(Story.MOD_ID, "smith.block.repair"));

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<SoundEvent> event) {
        try {
            for (Field f : SoundRegistry.class.getDeclaredFields()) {
                if (f.get(null) instanceof SoundEvent soundEvent) {
                    event.getRegistry().register(soundEvent);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
