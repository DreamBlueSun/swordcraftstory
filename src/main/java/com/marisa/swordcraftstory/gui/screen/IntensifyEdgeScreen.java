package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IntensifyEdgeContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * @description: 强刃Screen
 * @date: 2021/9/7 0007 21:08
 */

public class IntensifyEdgeScreen extends ContainerScreen<IntensifyEdgeContainer> {

    private final int textureWidth = 179;
    private final int textureHeight = 194;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE = new ResourceLocation(Story.MOD_ID, "textures/gui/intensify_edge_container.png");

    public IntensifyEdgeScreen(IntensifyEdgeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2 - 79;
        int y = this.height / 2 - 58;
        this.button1 = new Button(x, y, 32, 20, new StringTextComponent("攻击"), (button) -> {
        });
        this.button2 = new Button(x + 85, y, 32, 20, new StringTextComponent("防御"), (button) -> {
        });
        this.button3 = new Button(x, y + 21, 32, 20, new StringTextComponent("敏捷"), (button) -> {
        });
        this.button4 = new Button(x + 85, y + 21, 32, 20, new StringTextComponent("耐久"), (button) -> {
        });
        this.button5 = new Button(x + 127, y + 44, 32, 20, new StringTextComponent("确定"), (button) -> {
        });
        this.addButton(button1);
        this.addButton(button2);
        this.addButton(button3);
        this.addButton(button4);
        this.addButton(button5);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindTexture(OBSIDIAN_CONTAINER_RESOURCE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        //ATK
        drawCenteredString(matrixStack, this.font, "2", 64, 47, 0xeb0505);
        //DEF
        drawCenteredString(matrixStack, this.font, "3", 149, 47, 0xeb0505);
        //AGL
        drawCenteredString(matrixStack, this.font, "1", 64, 68, 0xeb0505);
        //DUR
        drawCenteredString(matrixStack, this.font, "5", 149, 68, 0xeb0505);
        //POINT
        drawCenteredString(matrixStack, this.font, "1", 64, 89, 0xeb0505);
    }

}
