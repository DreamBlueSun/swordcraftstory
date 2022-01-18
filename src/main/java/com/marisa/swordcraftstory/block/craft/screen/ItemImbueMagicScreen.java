package com.marisa.swordcraftstory.block.craft.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.OneAddThreeGetOneMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * 注魔锻冶台screen
 */

public class ItemImbueMagicScreen extends OneAddThreeGetOneScreen<OneAddThreeGetOneMenu> {
    private static final ResourceLocation LOCATION = new ResourceLocation(Story.MOD_ID, "textures/gui/container/item_imbue_magic.png");

    public ItemImbueMagicScreen(OneAddThreeGetOneMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(@NotNull PoseStack stack, int p_99295_, int p_99296_) {
        RenderSystem.disableBlend();
        super.renderLabels(stack, p_99295_, p_99296_);
    }
}