package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.WeaponMakeContainer;
import com.marisa.swordcraftstory.item.mould.Mould;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.SendPack;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

/**
 * @description: 制作武器Screen
 * @date: 2021/9/7 0007 21:08
 */

public class WeaponMakeScreen extends ContainerScreen<WeaponMakeContainer> {

    private final int textureWidth = 240;
    private final int textureHeight = 240;
    Button button;
    private final ResourceLocation OBSIDIAN_CONTAINER_RESOURCE = new ResourceLocation(Story.MOD_ID, "textures/gui/weapon_make.png");

    public WeaponMakeScreen(WeaponMakeContainer weaponMakeContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(weaponMakeContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2;
        int y = this.height / 2;
        this.button = new Button(x + 70, y - 102, 32, 20, new StringTextComponent("制作"), (button) -> {
            BlockPos blockPos = this.getContainer().getBlockPos();
            Networking.INSTANCE.sendToServer(new SendPack("smithery.weaponMake.done", blockPos));
        });
        this.addButton(button);
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
        List<Slot> slots = this.container.inventorySlots;
        ItemStack stack0 = slots.get(0).getStack();
        if (stack0.isEmpty()) {
            return;
        }
        ItemStack stack1 = slots.get(1).getStack();
        if (stack1.isEmpty()) {
            return;
        }
        if (!slots.get(2).getStack().isEmpty()) {
            return;
        }
        Mould mould = (Mould) stack0.getItem();
        AbstractOre ore = (AbstractOre) stack1.getItem();
        //显示偏移
        int xs = 76;
        int ys = 44;
        //RANK
        int rank = ore.rank();
        drawCenteredString(matrixStack, this.font, String.valueOf(rank), xs, ys, 0xFFD700);
        //ATK
        int atk = ore.atk(mould.type());
        drawCenteredString(matrixStack, this.font, String.valueOf(atk), xs, ys + 8, 0x8B4513);
        //DEF
        int def = ore.def(mould.type());
        drawCenteredString(matrixStack, this.font, String.valueOf(def), xs, ys + 16, 0x8B4513);
        //AGL
        int agl = ore.agl(mould.type());
        drawCenteredString(matrixStack, this.font, String.valueOf(agl), xs, ys + 24, 0x8B4513);
        //DUR
        int dur = ore.dur(mould.type());
        drawCenteredString(matrixStack, this.font, String.valueOf(dur), xs, ys + 32, 0x8B4513);
        //ATK
        int mouldAtk = mould.getAtk(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(mouldAtk), xs + 36, ys + 8, 0x1E90FF);
        //DEF
        int mouldDef = mould.getDef(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(mouldDef), xs + 36, ys + 16, 0x1E90FF);
        //AGL
        int mouldAgl = mould.getAgl(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(mouldAgl), xs + 36, ys + 24, 0x1E90FF);
        //DUR
        int mouldDur = mould.getDurMax(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(mouldDur), xs + 36, ys + 32, 0x1E90FF);
    }

}
