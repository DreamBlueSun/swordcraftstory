package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.RankUpBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

/**
 * 方块注册
 */
@Mod.EventBusSubscriber(modid = Story.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {

    public static final Block STORY_ORE_BLOCK = new StoryOreBlock();
    public static final Block RANK_UP_BLOCK = new RankUpBlock();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        try {
            for (Field f : BlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block) {
                    event.getRegistry().register((Block) obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
