package com.marisa.swordcraftstory.bar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.player.Player;

public class PlayerHealth extends AbstractBarOverlay {

    @Override
    public void renderBar(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, PLAYER_BAR);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        minecraft.getProfiler().push("health");
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        //血槽
        GuiComponent.blit(poseStack, xStart, yStart, 0, 0, 81, 9, 128, 64);
        //血量
        int l = (int) (Math.min(player.getHealth() / player.getMaxHealth(), 1.0F) * 80);
        GuiComponent.blit(poseStack, xStart, yStart, 0, 9, l, 9, 128, 64);
        minecraft.getProfiler().pop();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderText(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        String hp = String.valueOf((int) player.getHealth());
        GuiComponent.drawString(poseStack, Minecraft.getInstance().font, hp, xStart - (6 * hp.length()), yStart + 1, 0x00FF00);
    }

    @Override
    public void renderIcon(Player player, PoseStack poseStack) {
    }
}
