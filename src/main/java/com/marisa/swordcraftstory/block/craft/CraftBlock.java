package com.marisa.swordcraftstory.block.craft;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public abstract class CraftBlock extends Block {

    public CraftBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(256.0F, 256.0F));
    }

}