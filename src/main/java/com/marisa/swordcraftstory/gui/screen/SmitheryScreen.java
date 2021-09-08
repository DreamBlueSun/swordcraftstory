package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.SendPack;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

/**
 * @description:
 * @date: 2021/9/2 0002 1:00
 */

public class SmitheryScreen extends Screen {

    private final BlockPos blockPos;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ResourceLocation SMITHERY_GUI_TEXTURE = new ResourceLocation(Story.MOD_ID, "textures/gui/smithing.png");

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
            Networking.INSTANCE.sendToServer(new SendPack("smithery.intensifyEdge", this.blockPos));
        });
        this.button4 = new Button(x, y + 75, 50, 20, new StringTextComponent("解体"), (button) -> {
        });
        this.button5 = new Button(x, y + 100, 50, 20, new StringTextComponent("修理"), (button) -> {
            Networking.INSTANCE.sendToServer(new SendPack("smithery.repairAll"));
            Minecraft instance = Minecraft.getInstance();
            if (instance.player != null) {
                PlayerInventory inv = instance.player.inventory;
                for (int i = 0; i < inv.mainInventory.size(); i++) {
                    ItemStack stack = inv.mainInventory.get(i);
                    if (!stack.isEmpty() && stack.getItem() instanceof Combat) {
                        stack.setDamage(0);
                        inv.setInventorySlotContents(i, stack);
                    }
                }
                instance.displayGuiScreen(null);
            }
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
            this.minecraft.getTextureManager().bindTexture(SMITHERY_GUI_TEXTURE);
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
