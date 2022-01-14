package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.*;
import com.marisa.swordcraftstory.block.ore.StoryOreBlock;
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
    public static final Block ITEM_MAKE_BLOCK = new ItemMakeBlock();
    public static final Block ITEM_STRENGTHEN_BLOCK = new ItemStrengthenBlock();
    public static final Block ITEM_COLLAPSE_BLOCK = new ItemCollapseBlock();
    public static final Block ITEM_EDGE_BLOCK = new ItemEdgeBlock();
    public static final Block REPAIR_BLOCK = new RepairBlock();

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
