package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

/**
 * 手摇抽奖机物品信息Screen
 */

public class ManualLotteryItemInfoScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/story_player_status.png");

    private final ClientPlayerEntity player;
    private final ManualLotteryItemInfoScreenData data;

    protected ManualLotteryItemInfoScreen(ClientPlayerEntity player, ManualLotteryItemInfoScreenData data) {
        super(new StringTextComponent("manual_lottery_item_info_screen"));
        this.player = player;
        this.data = data;
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
        drawString(matrixStack, this.font, new StringTextComponent("今日好运道具一览！").mergeStyle(TextFormatting.LIGHT_PURPLE), x, y, 0x8B4513);
        int xAttr = this.width / 2 + 10;
        int yAttr = this.height / 2 - 30;
        IFormattableTextComponent component = new StringTextComponent("一等").mergeStyle(TextFormatting.GOLD)
                .appendString("     ").appendSibling(new StringTextComponent(data.getItemStack1().getItem().getName().getString()).mergeStyle(TextFormatting.BLUE));
        drawString(matrixStack, this.font, component, xAttr, yAttr, 0x8B4513);

        component = new StringTextComponent("二等").mergeStyle(TextFormatting.DARK_PURPLE)
                .appendString("     ").appendSibling(new StringTextComponent(data.getItemStack2().getItem().getName().getString()).mergeStyle(TextFormatting.BLUE));
        drawString(matrixStack, this.font, component, xAttr, yAttr + 20, 0x8B4513);

        component = new StringTextComponent("三等").mergeStyle(TextFormatting.AQUA)
                .appendString("     ").appendSibling(new StringTextComponent(data.getItemStack3().getItem().getName().getString()).mergeStyle(TextFormatting.BLUE));
        drawString(matrixStack, this.font, component, xAttr, yAttr + 40, 0x8B4513);

        component = new StringTextComponent("四等").mergeStyle(TextFormatting.GREEN)
                .appendString("     ").appendSibling(new StringTextComponent(data.getItemStack4().getItem().getName().getString()).mergeStyle(TextFormatting.BLUE));
        drawString(matrixStack, this.font, component, xAttr, yAttr + 60, 0x8B4513);

        component = new StringTextComponent("五等").mergeStyle(TextFormatting.GRAY)
                .appendString("     ").appendSibling(new StringTextComponent(data.getItemStack5().getItem().getName().getString()).mergeStyle(TextFormatting.BLUE));
        drawString(matrixStack, this.font, component, xAttr, yAttr + 80, 0x8B4513);

        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(ClientPlayerEntity player, ManualLotteryItemInfoScreenData data) {
        Minecraft.getInstance().displayGuiScreen(new ManualLotteryItemInfoScreen(player, data));
    }

}
