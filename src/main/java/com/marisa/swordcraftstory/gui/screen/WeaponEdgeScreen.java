package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.WeaponEdgePointIInt;
import com.marisa.swordcraftstory.gui.container.WeaponEdgeContainer;
import com.marisa.swordcraftstory.item.weapon.Weapon;
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
 * 强刃Screen
 */

public class WeaponEdgeScreen extends ContainerScreen<WeaponEdgeContainer> {

    private final int textureWidth = 179;
    private final int textureHeight = 194;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    private final ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/intensify_edge_container.png");

    private int pointUsed;
    private int atkTime;
    private int defTime;
    private int aglTime;
    private int durTime;

    public WeaponEdgeScreen(WeaponEdgeContainer weaponEdgeContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(weaponEdgeContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
        this.atkTime = this.defTime = this.aglTime = this.durTime = this.pointUsed = 0;
    }

    private int getPoint() {
        return this.getContainer().getPoint().get(0);
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2 - 79;
        int y = this.height / 2 - 58;
        this.button1 = new Button(x, y, 32, 20, new StringTextComponent("攻击"), (button) -> {
            if (getPoint() > this.pointUsed) {
                this.atkTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.atkTime;
                this.atkTime = 0;
            }
        });
        this.button2 = new Button(x + 85, y, 32, 20, new StringTextComponent("防御"), (button) -> {
            if (getPoint() > this.pointUsed) {
                this.defTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.defTime;
                this.defTime = 0;
            }
        });
        this.button3 = new Button(x, y + 21, 32, 20, new StringTextComponent("敏捷"), (button) -> {
            if (getPoint() > this.pointUsed) {
                this.aglTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.aglTime;
                this.aglTime = 0;
            }
        });
        this.button4 = new Button(x + 85, y + 21, 32, 20, new StringTextComponent("耐久"), (button) -> {
            if (getPoint() > this.pointUsed) {
                this.durTime++;
                this.pointUsed++;
            } else {
                this.pointUsed -= this.durTime;
                this.durTime = 0;
            }
        });
        this.button5 = new Button(x + 127, y + 44, 32, 20, new StringTextComponent("强刃"), (button) -> {
            WeaponEdgePointIInt point = this.getContainer().getPoint();
            BlockPos pos = new BlockPos(point.get(1), point.get(2), point.get(3));
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
        this.minecraft.getTextureManager().bindTexture(resourceLocation);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        //ATK
        drawCenteredString(matrixStack, this.font, String.valueOf(Weapon.INTENSIFY_EDGE_ONCE_NUM_ATK * this.atkTime), 64, 47, 0x1E90FF);
        //DEF
        drawCenteredString(matrixStack, this.font, String.valueOf(Weapon.INTENSIFY_EDGE_ONCE_NUM_DEF * this.defTime), 149, 47, 0x1E90FF);
        //AGL
        drawCenteredString(matrixStack, this.font, String.valueOf(Weapon.INTENSIFY_EDGE_ONCE_NUM_AGL * this.aglTime), 64, 68, 0x1E90FF);
        //DUR
        drawCenteredString(matrixStack, this.font, String.valueOf(Weapon.INTENSIFY_EDGE_ONCE_NUM_DUR * this.durTime), 149, 68, 0x1E90FF);
        //POINT
        int point = getPoint() - this.pointUsed;
        int color;
        if (point > 0) {
            color = 0x00FF7F;
        } else {
            color = 0xFF0000;
        }
        drawCenteredString(matrixStack, this.font, String.valueOf(getPoint() - this.pointUsed), 64, 89, color);
    }

}
