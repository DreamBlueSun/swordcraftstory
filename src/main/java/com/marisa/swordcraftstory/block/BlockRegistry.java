package com.marisa.swordcraftstory.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description: 方块注册
 * @date: 2021/9/1 0001 1:27
 */

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "swordcraftstory");
    //方块
    public static RegistryObject<Block> SMITHING_BLOCK = BLOCKS.register("smithing_block", SmithingBlock::new);
}
