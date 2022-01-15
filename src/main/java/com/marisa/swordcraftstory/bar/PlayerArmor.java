package com.marisa.swordcraftstory.bar;

import com.marisa.swordcraftstory.smith.util.SmithHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerArmor extends AbstractBarOverlay {

    @Override
    public void renderBar(Player player, PoseStack poseStack) {
    }

    @Override
    public void renderText(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        int def = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            def += SmithHelper.getSmithDef(stack);
        }
        int armor = player.getArmorValue() + def;
        GuiComponent.drawString(poseStack, Minecraft.getInstance().font, String.valueOf(armor), xStart + 39, yStart - 9, 0xC0C0C0);
    }

    @Override
    public void renderIcon(Player player, PoseStack poseStack) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, Gui.GUI_ICONS_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        minecraft.getProfiler().push("armor");
        int xStart = minecraft.getWindow().getGuiScaledWidth() / 2 - 91;
        int yStart = minecraft.getWindow().getGuiScaledHeight() - 39;
        //护甲
        GuiComponent.blit(poseStack, xStart + 30, yStart - 10, 43, 9, 9, 9, 256, 256);
        minecraft.getProfiler().pop();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
