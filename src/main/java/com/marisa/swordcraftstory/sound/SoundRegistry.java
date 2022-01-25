package com.marisa.swordcraftstory.sound;

import com.marisa.swordcraftstory.Story;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 声音注册
 */
public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Story.MOD_ID);

    public static final RegistryObject<SoundEvent> SMITH_BLOCK_CRAFT = registerSound(("smith.block.craft"));
    public static final RegistryObject<SoundEvent> SMITH_BLOCK_REPAIR = registerSound(("smith.block.repair"));
    public static final RegistryObject<SoundEvent> SMITH_ITEM_REPAIR = registerSound(("smith.item.repair"));
    public static final RegistryObject<SoundEvent> RUNNING_BLOCK_MANUAL_LOTTERY_MACHINE = registerSound(("running.block.manual_lottery_machine"));
    public static final RegistryObject<SoundEvent> DONE_BLOCK_MANUAL_LOTTERY_MACHINE = registerSound(("done.block.manual_lottery_machine"));

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Story.MOD_ID, name)));
    }

}
