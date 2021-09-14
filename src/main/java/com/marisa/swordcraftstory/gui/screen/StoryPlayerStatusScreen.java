package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.DamageCountUtils;
import com.marisa.swordcraftstory.util.obj.Damage;
import com.marisa.swordcraftstory.util.obj.Defense;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
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
        int x = this.width / 2 - 148;
        int y = this.height / 2 - 60;
        //等级
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        int XP = data.getXp();
        int LV = StoryPlayerDataManager.getLv(XP);
        String font = "Lv  " + LV;
        drawCenteredString(matrixStack, this.font, font, x + offset(font), y, 0x8B4513);
        //经验值
        int xAttr = this.width / 2 - 30;
        int nextXpPoint = StoryPlayerDataManager.getLvNextXpPoint(LV);
        font = "Exp " + XP + " / " + nextXpPoint;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), y, 0x8B4513);
        int yAttr = this.height / 2 - 30;
        //名字
        font = player.getDisplayName().getString();
        drawCenteredString(matrixStack, this.font, font, x + offset(font), yAttr, 0x8B4513);
        //HP
        String HP = (int) player.getHealth() + " / " + (int) player.getMaxHealth();
        font = "血量  " + HP;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), yAttr, 0x8B4513);
        //ATK
        Damage damage = new Damage();
        DamageCountUtils.playerDamage(player, damage);
        int ATK = (int) DamageCountUtils.count(damage, new Defense());
        font = "攻击  " + ATK;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), yAttr + 20, 0x8B4513);
        //DEF
        int DEF = player.getTotalArmorValue();
        font = "防御  " + DEF;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), yAttr + 40, 0x8B4513);
        //PHY
        int PHY = (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        font = "魔抗  " + PHY;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), yAttr + 60, 0x8B4513);
        //AGL
        int AGL = 0;
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
            Weapon weapon = (Weapon) stack.getItem();
            AGL = weapon.getAgl(stack);
        }
        font = "敏捷  " + AGL;
        drawCenteredString(matrixStack, this.font, font, xAttr + offset(font), yAttr + 80, 0x8B4513);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(ClientPlayerEntity player) {
        Minecraft.getInstance().displayGuiScreen(new StoryPlayerStatusScreen(player));
    }

    private static int offset(String font) {
        return (font.length() + 16) * 28 / 10;
    }

}
