package com.marisa.swordcraftstory.block.craft.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemEdgeMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * 强刃锻冶台screen
 */

public class ItemEdgeScreen extends ItemCombinerScreen<ItemEdgeMenu> {
    private static final ResourceLocation LOCATION = new ResourceLocation(Story.MOD_ID, "textures/gui/container/item_edge.png");

    public ItemEdgeScreen(ItemEdgeMenu p_99290_, Inventory p_99291_, Component p_99292_) {
        super(p_99290_, p_99291_, p_99292_, LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(PoseStack p_99294_, int p_99295_, int p_99296_) {
        RenderSystem.disableBlend();
        super.renderLabels(p_99294_, p_99295_, p_99296_);
    }
}