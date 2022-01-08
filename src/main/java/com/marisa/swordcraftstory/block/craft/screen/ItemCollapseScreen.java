package com.marisa.swordcraftstory.block.craft.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemCollapseMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * 解体锻冶台screen
 */

public class ItemCollapseScreen extends ItemCollapseCombinerScreen<ItemCollapseMenu> {
    private static final ResourceLocation LOCATION = new ResourceLocation(Story.MOD_ID, "textures/gui/container/item_collapse.png");

    public ItemCollapseScreen(ItemCollapseMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(PoseStack stack, int p_99295_, int p_99296_) {
        RenderSystem.disableBlend();
        super.renderLabels(stack, p_99295_, p_99296_);
    }
}