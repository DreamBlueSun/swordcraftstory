package com.marisa.swordcraftstory.bar;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class BarEventHandler {

    @SubscribeEvent
    public void renderBars(RenderGameOverlayEvent.PreLayer event) {
        OverlayRegistry.OverlayEntry overlayEntry = OverlayRegistry.getEntry(event.getOverlay());
        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui || !(mc.getCameraEntity() instanceof Player)) return;
        if (mc.gameMode == null || !mc.gameMode.canHurtPlayer()) return;
        if (overlayEntry == null) return;
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

}
