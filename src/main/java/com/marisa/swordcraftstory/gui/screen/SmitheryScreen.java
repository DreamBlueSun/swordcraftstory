package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

/**
 * 旧版锻冶菜单Screen
 */

public class SmitheryScreen extends Screen {

    private final BlockPos blockPos;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/smithing.png");

    protected SmitheryScreen(BlockPos pos) {
        super(new StringTextComponent("smithery_gui_main"));
        this.blockPos = pos;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2 - 25;
        int y = this.height / 2 - 58;
        this.button1 = new Button(x, y, 50, 20, new StringTextComponent("制作"), (button) -> {
        });
        this.button2 = new Button(x, y + 25, 50, 20, new StringTextComponent("强化"), (button) -> {
        });
        this.button3 = new Button(x, y + 50, 50, 20, new StringTextComponent("强刃"), (button) -> {
        });
        this.button4 = new Button(x, y + 75, 50, 20, new StringTextComponent("解体"), (button) -> {
        });
        this.button5 = new Button(x, y + 100, 50, 20, new StringTextComponent("修理"), (button) -> {
        });
        this.addButton(button1);
        this.addButton(button2);
        this.addButton(button3);
        this.addButton(button4);
        this.addButton(button5);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float particleTick) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(resourceLocation);
        }
        blit(matrixStack, this.width / 2 - 50, this.height / 2 - 75, 0, 0, 100, 150, 100, 150);
        this.button1.render(matrixStack, mouseX, mouseY, particleTick);
        this.button2.render(matrixStack, mouseX, mouseY, particleTick);
        this.button3.render(matrixStack, mouseX, mouseY, particleTick);
        this.button4.render(matrixStack, mouseX, mouseY, particleTick);
        this.button5.render(matrixStack, mouseX, mouseY, particleTick);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(BlockPos pos) {
        Minecraft.getInstance().displayGuiScreen(new SmitheryScreen(pos));
    }

}
