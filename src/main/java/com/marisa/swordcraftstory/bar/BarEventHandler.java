package com.marisa.swordcraftstory.bar;

import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.marisa.swordcraftstory.smith.util.StoryUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class BarEventHandler {

    @SubscribeEvent
    public void renderBars(RenderGameOverlayEvent.PreLayer event) {
        OverlayRegistry.OverlayEntry overlayEntry = OverlayRegistry.getEntry(event.getOverlay());
        if (overlayEntry == null) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || !(mc.getCameraEntity() instanceof Player)) return;
        if (mc.gameMode == null || !mc.gameMode.canHurtPlayer()) return;
        event.setCanceled(true);
        switch (overlayEntry.getDisplayName()) {
            case "Player Health" -> {
                new PlayerArmor().renderAll((Player) mc.getCameraEntity(), event.getMatrixStack());
                new PlayerAbsorb().renderAll((Player) mc.getCameraEntity(), event.getMatrixStack());
            }
            case "Armor Level" -> {
                new PlayerHealth().renderAll((Player) mc.getCameraEntity(), event.getMatrixStack());
                new PlayerToughness().renderAll((Player) mc.getCameraEntity(), event.getMatrixStack());
            }
            default -> event.setCanceled(false);
        }
    }

    @SubscribeEvent
    public void renderHotBarsDur(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.noRender || minecraft.level == null) return;
        if (minecraft.options.hideGui || minecraft.screen != null) return;
        if (minecraft.gameMode == null || minecraft.gameMode.isAlwaysFlying()) return;
        if (minecraft.getCameraEntity() instanceof Player player) {
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();
            int i = minecraft.getWindow().getGuiScaledWidth() / 2;
            for (int j1 = 0; j1 < 9; ++j1) {
                int k1 = i - 90 + j1 * 20 + 2;
                int l1 = screenHeight - 16 - 3;
                this.renderSlotDur(k1, l1, player.getInventory().items.get(j1));
            }
            ItemStack stack = player.getOffhandItem();
            if (!stack.isEmpty()) {
                int j2 = screenHeight - 16 - 3;
                if (player.getMainArm().getOpposite() == HumanoidArm.LEFT) {
                    this.renderSlotDur(i - 91 - 26, j2, stack);
                } else {
                    this.renderSlotDur(i + 91 + 10, j2, stack);
                }
            }
        }
    }

    private void renderSlotDur(int x, int y, ItemStack stack) {
        if (stack.isEmpty()) return;
        if (!StoryUtils.isWeapon(stack.getItem()) || SmithHelper.getDur(stack) >= SmithHelper.getDurMax(stack)) return;
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.disableBlend();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        int i = getBarWidth(stack);
        int j = getBarColor(stack);
        this.fillRect(bufferbuilder, x + 2, y + 13, 13, 2, 0, 0, 0);
        this.fillRect(bufferbuilder, x + 2, y + 13, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255);
        RenderSystem.enableBlend();
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
    }

    private int getBarWidth(ItemStack stack) {
        return Math.round(13.0F * SmithHelper.getDur(stack) / SmithHelper.getDurMax(stack));
    }

    private int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, ((float) SmithHelper.getDur(stack)) / (float) SmithHelper.getDurMax(stack));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    private void fillRect(BufferBuilder builder, int p_115154_, int p_115155_, int p_115156_, int p_115157_, int p_115158_, int p_115159_, int p_115160_) {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        builder.vertex(p_115154_, p_115155_, 0.0D).color(p_115158_, p_115159_, p_115160_, 255).endVertex();
        builder.vertex(p_115154_, p_115155_ + p_115157_, 0.0D).color(p_115158_, p_115159_, p_115160_, 255).endVertex();
        builder.vertex(p_115154_ + p_115156_, p_115155_ + p_115157_, 0.0D).color(p_115158_, p_115159_, p_115160_, 255).endVertex();
        builder.vertex(p_115154_ + p_115156_, p_115155_, 0.0D).color(p_115158_, p_115159_, p_115160_, 255).endVertex();
        builder.end();
        BufferUploader.end(builder);
    }

}
