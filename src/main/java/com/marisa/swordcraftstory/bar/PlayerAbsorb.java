package com.marisa.swordcraftstory.bar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.player.Player;

public class PlayerAbsorb extends AbstractBarOverlay {

    @Override
    public void renderBar(Player player, PoseStack poseStack) {
    }

    @Override
    public void renderText(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        int absorb = (int) player.getAbsorptionAmount();
        GuiComponent.drawString(poseStack, Minecraft.getInstance().font, String.valueOf(absorb), xStart + 9, yStart - 9, 0x00FF00);
    }

    @Override
    public void renderIcon(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, Gui.GUI_ICONS_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        minecraft.getProfiler().push("absorb");
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        //伤害吸收
        GuiComponent.blit(poseStack, xStart, yStart - 10, 52, 0, 9, 9, 256, 256);
        minecraft.getProfiler().pop();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
