package com.marisa.swordcraftstory.block.tile;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.BlockRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description:
 * @date: 2021/9/7 0007 22:16
 */

public class TileEntityTypeRegistry {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Story.MOD_ID);

    public static final RegistryObject<TileEntityType<SmithingBlockTileEntity>> SMITHING_BLOCK_TILE_ENTITY =
            TILE_ENTITIES.register("smithing_block_tile_entity", () ->
                    TileEntityType.Builder.create(SmithingBlockTileEntity::new, BlockRegistry.SMITHING_BLOCK.get()).build(null));

}
