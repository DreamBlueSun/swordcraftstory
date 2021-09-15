package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.ore.StoryOreBlock;
import com.marisa.swordcraftstory.block.tile.RepairBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 方块注册
 */

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Story.MOD_ID);

    public static RegistryObject<Block> STORY_ORE_BLOCK = BLOCKS.register("story_ore_block", StoryOreBlock::new);
    public static RegistryObject<Block> WEAPON_MAKE_BLOCK = BLOCKS.register("weapon_make_block", WeaponMakeBlock::new);
    public static RegistryObject<Block> WEAPON_EDGE_BLOCK = BLOCKS.register("weapon_edge_block", WeaponEdgeBlock::new);
    public static RegistryObject<Block> WEAPON_COLLAPSE_BLOCK = BLOCKS.register("weapon_collapse_block", WeaponCollapseBlock::new);
    public static RegistryObject<Block> REPAIR_BLOCK = BLOCKS.register("repair_block", RepairBlock::new);
}
