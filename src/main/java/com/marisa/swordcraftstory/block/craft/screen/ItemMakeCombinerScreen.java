package com.marisa.swordcraftstory.block.craft.screen;

import com.marisa.swordcraftstory.block.craft.menu.AbstractItemMakeMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ItemMakeCombinerScreen<T extends AbstractItemMakeMenu> extends AbstractContainerScreen<T> implements ContainerListener {
    private final ResourceLocation menuResource;

    public ItemMakeCombinerScreen(T p_98901_, Inventory inventory, Component component, ResourceLocation location) {
        super(p_98901_, inventory, component);
        this.menuResource = location;
    }

    protected void subInit() {
    }

    protected void init() {
        super.init();
        this.subInit();
        this.menu.addSlotListener(this);
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }

    public void render(PoseStack stack, int p_98923_, int p_98924_, float p_98925_) {
        this.renderBackground(stack);
        super.render(stack, p_98923_, p_98924_, p_98925_);
        RenderSystem.disableBlend();
        this.renderFg(stack, p_98923_, p_98924_, p_98925_);
        this.renderTooltip(stack, p_98923_, p_98924_);
    }

    protected void renderFg(PoseStack p_98927_, int p_98928_, int p_98929_, float p_98930_) {
    }

    protected void renderBg(PoseStack stack, float p_98918_, int p_98919_, int p_98920_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.menuResource);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(stack, i + 59, j + 20, 0, this.imageHeight + (this.menu.getSlot(0).hasItem() ? 0 : 16), 110, 16);
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem() || this.menu.getSlot(2).hasItem()) && !this.menu.getSlot(3).hasItem()) {
            this.blit(stack, i + 111, j + 45, this.imageWidth, 0, 28, 21);
        }

    }

    public void dataChanged(AbstractContainerMenu containerMenu, int p_169760_, int p_169761_) {
    }

    public void slotChanged(AbstractContainerMenu containerMenu, int p_98911_, ItemStack stack) {
    }
}