package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.WeaponCollapseContainer;
import com.marisa.swordcraftstory.item.weapon.Combat;
import com.marisa.swordcraftstory.item.mould.Mould;
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
 * 解体武器Screen
 */

public class WeaponCollapseScreen extends ContainerScreen<WeaponCollapseContainer> {

    private final int textureWidth = 240;
    private final int textureHeight = 240;
    Button button;
    private final ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/weapon_collapse.png");

    public WeaponCollapseScreen(WeaponCollapseContainer weaponCollapseContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(weaponCollapseContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2;
        int y = this.height / 2;
        this.button = new Button(x + 70, y - 102, 32, 20, new StringTextComponent("解体"), (button) -> {
            BlockPos blockPos = this.getContainer().getBlockPos();
            Networking.INSTANCE.sendToServer(new SendPack("smithery.weaponCollapse.done", blockPos));
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
        this.minecraft.getTextureManager().bindTexture(resourceLocation);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        List<Slot> slots = this.container.inventorySlots;
        ItemStack stack0 = slots.get(0).getStack();
        if (stack0.isEmpty() || stack0.getItem() instanceof Mould) {
            return;
        }
        Combat combat = (Combat) stack0.getItem();
        //显示偏移
        int xs = 76;
        int ys = 44;
        //ATK
        ItemStack collapse = combat.collapse(stack0);
        Mould mould = (Mould) collapse.getItem();
        drawCenteredString(matrixStack, this.font, String.valueOf(mould.getAtk(collapse)), xs, ys + 8, 0x8B4513);
        //DEF
        drawCenteredString(matrixStack, this.font, String.valueOf(mould.getDef(collapse)), xs, ys + 16, 0x8B4513);
        //AGL
        drawCenteredString(matrixStack, this.font, String.valueOf(mould.getAgl(collapse)), xs, ys + 24, 0x8B4513);
        //DUR
        drawCenteredString(matrixStack, this.font, String.valueOf(mould.getDurMax(collapse)), xs, ys + 32, 0x8B4513);
    }

}
