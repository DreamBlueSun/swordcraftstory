package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.IntensifyEdgePointInt;
import com.marisa.swordcraftstory.gui.container.IntensifyEdgeContainer;
import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.SendPack;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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

    private int pointUsed;
    private int atkTime;
    private int defTime;
    private int aglTime;
    private int durTime;

    public IntensifyEdgeScreen(IntensifyEdgeContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
        this.atkTime = this.defTime = this.aglTime = this.durTime = this.pointUsed = 0;
    }

    private int getPointMax() {
        return this.getContainer().getPointMax().get(0);
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2 - 79;
        int y = this.height / 2 - 58;
        this.button1 = new Button(x, y, 32, 20, new StringTextComponent("攻击"), (button) -> {
            if (getPointMax() > this.pointUsed) {
                this.atkTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.atkTime;
                this.atkTime = 0;
            }
        });
        this.button2 = new Button(x + 85, y, 32, 20, new StringTextComponent("防御"), (button) -> {
            if (getPointMax() > this.pointUsed) {
                this.defTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.defTime;
                this.defTime = 0;
            }
        });
        this.button3 = new Button(x, y + 21, 32, 20, new StringTextComponent("敏捷"), (button) -> {
            if (getPointMax() > this.pointUsed) {
                this.aglTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.aglTime;
                this.aglTime = 0;
            }
        });
        this.button4 = new Button(x + 85, y + 21, 32, 20, new StringTextComponent("耐久"), (button) -> {
            if (getPointMax() > this.pointUsed) {
                this.durTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.durTime;
                this.durTime = 0;
            }
        });
        this.button5 = new Button(x + 127, y + 44, 32, 20, new StringTextComponent("确定"), (button) -> {
            IntensifyEdgePointInt intensifyEdgeInt = this.getContainer().getPointMax();
            BlockPos pos = new BlockPos(intensifyEdgeInt.get(1), intensifyEdgeInt.get(2), intensifyEdgeInt.get(3));
            Networking.INSTANCE.sendToServer(new SendPack("smithery.intensifyEdge.done", pos, this.atkTime, this.defTime, this.aglTime, this.durTime));
            this.atkTime = this.defTime = this.aglTime = this.durTime = this.pointUsed = 0;
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
        drawCenteredString(matrixStack, this.font, String.valueOf(Combat.INTENSIFY_EDGE_ONCE_NUM_ATK * this.atkTime), 64, 47, 0x1E90FF);
        //DEF
        drawCenteredString(matrixStack, this.font, String.valueOf(Combat.INTENSIFY_EDGE_ONCE_NUM_DEF * this.defTime), 149, 47, 0x1E90FF);
        //AGL
        drawCenteredString(matrixStack, this.font, String.valueOf(Combat.INTENSIFY_EDGE_ONCE_NUM_AGL * this.aglTime), 64, 68, 0x1E90FF);
        //DUR
        drawCenteredString(matrixStack, this.font, String.valueOf(Combat.INTENSIFY_EDGE_ONCE_NUM_DUR * this.durTime), 149, 68, 0x1E90FF);
        //POINT
        int point = getPointMax() - this.pointUsed;
        int color;
        if (point > 0) {
            color = 0x00FF7F;
        } else {
            color = 0xFF0000;
        }
        drawCenteredString(matrixStack, this.font, String.valueOf(getPointMax() - this.pointUsed), 64, 89, color);
    }

}
