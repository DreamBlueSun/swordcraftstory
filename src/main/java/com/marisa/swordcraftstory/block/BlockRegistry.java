package com.marisa.swordcraftstory.block;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.tile.RepairBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description: 方块注册
 * @date: 2021/9/1 0001 1:27
 */

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Story.MOD_ID);
    //方块
    public static RegistryObject<Block> WEAPON_MAKE_BLOCK = BLOCKS.register("weapon_make_block", WeaponMakeBlock::new);
    public static RegistryObject<Block> SMITHING_BLOCK = BLOCKS.register("smithing_block", SmithingBlock::new);
    public static RegistryObject<Block> WEAPON_COLLAPSE_BLOCK = BLOCKS.register("weapon_collapse_block", WeaponCollapseBlock::new);
    public static RegistryObject<Block> REPAIR_BLOCK = BLOCKS.register("repair_block", RepairBlock::new);
}
