package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.obj.PlayerPanelAttr;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**
 * 个人信息Screen
 */

public class StoryPlayerStatusScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/story_player_status.png");

    private final ClientPlayerEntity player;

    protected StoryPlayerStatusScreen(ClientPlayerEntity player) {
        super(new StringTextComponent("story_player_status_main"));
        this.player = player;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float particleTick) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(resourceLocation);
        }
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
        //ATK
        drawString(matrixStack, this.font, panelAttr.getAtk(), xAttr, yAttr + 20, 0x8B4513);
        //DEF
        drawString(matrixStack, this.font, panelAttr.getDef(), xAttr, yAttr + 40, 0x8B4513);
        //PHY
        drawString(matrixStack, this.font, panelAttr.getPhy(), xAttr, yAttr + 60, 0x8B4513);
        //AGL
        drawString(matrixStack, this.font, panelAttr.getAgl(), xAttr, yAttr + 80, 0x8B4513);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(ClientPlayerEntity player) {
        Minecraft.getInstance().displayGuiScreen(new StoryPlayerStatusScreen(player));
    }

}
