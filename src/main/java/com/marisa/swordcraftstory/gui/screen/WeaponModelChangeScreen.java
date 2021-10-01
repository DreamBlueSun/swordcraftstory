package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.WeaponModelChangeContainer;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyHelper;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.SendPack;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.skill.effect.helper.EffectHelper;
import com.marisa.swordcraftstory.skill.effect.helper.Effects;
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
 * 幻化Screen
 */

public class WeaponModelChangeScreen extends ContainerScreen<WeaponModelChangeContainer> {

    private final int textureWidth = 240;
    private final int textureHeight = 240;
    Button button;
    private final ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/weapon_intensify.png");

    public WeaponModelChangeScreen(WeaponModelChangeContainer container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2;
        int y = this.height / 2;
        this.button = new Button(x + 70, y - 102, 32, 20, new StringTextComponent("幻化"), (button) -> {
            BlockPos blockPos = this.getContainer().getBlockPos();
            Networking.INSTANCE.sendToServer(new SendPack("smithery.weaponModelChange.done", blockPos));
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
        if (!IntensifyHelper.canIntensifyAttr(stack0)) {
            return;
        }
        //显示偏移
        int xs = 76;
        int ys = 44;
        //RANK
        Weapon weapon = (Weapon) stack0.getItem();
        int rank = weapon.getRank();
        drawCenteredString(matrixStack, this.font, String.valueOf(rank), xs, ys, 0xFFD700);
        //ATK
        int atk = weapon.getAtk(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(atk), xs, ys + 8, 0x8B4513);
        //DEF
        int def = weapon.getDef(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(def), xs, ys + 16, 0x8B4513);
        //AGL
        int agl = weapon.getAgl(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(agl), xs, ys + 24, 0x8B4513);
        //DUR
        int dur = weapon.getDurMax(stack0);
        drawCenteredString(matrixStack, this.font, String.valueOf(dur), xs, ys + 32, 0x8B4513);
        //技能
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack0);
        if (specialAttacks != null) {
            drawCenteredString(matrixStack, this.font, specialAttacks.getShow(), xs + 36, ys + 60, 0x1E90FF);
        }
        //效果
        Effects effects = EffectHelper.get(stack0);
        if (effects != null) {
            drawCenteredString(matrixStack, this.font, effects.getShow(), xs + 36, ys + 84, 0x4169E1);
        }
    }

}
