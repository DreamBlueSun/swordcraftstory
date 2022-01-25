package com.marisa.swordcraftstory.save.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.save.screen.pojo.PlayerPanelAttr;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * 个人信息Screen
 */

public class PlayerStatusScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/story_player_status.png");

    private final LocalPlayer player;

    protected PlayerStatusScreen(LocalPlayer player) {
        super(new TextComponent("story_player_status_main"));
        this.player = player;
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float particleTick) {
        this.renderBackground(matrixStack);
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, resourceLocation);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        minecraft.getProfiler().push("playerStatus");
        blit(matrixStack, this.width / 2 - 120, this.height / 2 - 80, 0, 0, 240, 160, 240, 160);
        int x = this.width / 2 - 102;
        int y = this.height / 2 - 60;
        PlayerPanelAttr panelAttr = new PlayerPanelAttr(player);
        //等级
        drawString(matrixStack, this.font, panelAttr.getLv(), x, y, 0x8B4513);
        //经验值
        int xAttr = this.width / 2 + 10;
        drawString(matrixStack, this.font, panelAttr.getExp(), xAttr, y, 0x8B4513);
        int yAttr = this.height / 2 - 30;
        //名字
        drawString(matrixStack, this.font, panelAttr.getName(), x, yAttr, 0x8B4513);
        //HP
        drawString(matrixStack, this.font, panelAttr.getHp(), xAttr, yAttr, 0x8B4513);
        //制作等级
        drawString(matrixStack, this.font, panelAttr.getMakeRank(), xAttr, yAttr + 20, 0x8B4513);
        //强化等级
        drawString(matrixStack, this.font, panelAttr.getStrengthenRank(), xAttr, yAttr + 40, 0x8B4513);
        minecraft.getProfiler().pop();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(LocalPlayer player) {
        Minecraft.getInstance().setScreen(new PlayerStatusScreen(player));
    }

}
