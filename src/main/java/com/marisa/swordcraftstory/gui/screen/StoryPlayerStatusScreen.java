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
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

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
        int x = this.width / 2 - 102;
        int y = this.height / 2 - 60;
        //等级
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        int XP = data.getXp();
        int LV = StoryPlayerDataManager.getLv(XP);
        IFormattableTextComponent text = new TranslationTextComponent("Lv").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(LV)).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, x, y, 0x8B4513);
        //经验值
        int xAttr = this.width / 2 + 10;
        String exp = XP + "/" + StoryPlayerDataManager.getLvNextXpPoint(LV);
        text = new TranslationTextComponent("Exp").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(exp).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, y, 0x8B4513);
        int yAttr = this.height / 2 - 30;
        //名字
        text = new TranslationTextComponent(player.getDisplayName().getString()).mergeStyle(TextFormatting.BLUE);
        drawString(matrixStack, this.font, text, x, yAttr, 0x8B4513);
        //HP
        String HP = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
        text = new TranslationTextComponent("血量").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(HP).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, yAttr, 0x8B4513);
        //ATK
        Damage damage = new Damage();
        DamageCountUtils.playerDamage(player, damage);
        int ATK = (int) DamageCountUtils.count(damage, new Defense());
        text = new TranslationTextComponent("攻击").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(ATK)).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, yAttr + 20, 0x8B4513);
        //DEF
        int DEF = player.getTotalArmorValue();
        text = new TranslationTextComponent("防御").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(DEF)).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, yAttr + 40, 0x8B4513);
        //PHY
        int PHY = (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        text = new TranslationTextComponent("魔抗").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(PHY)).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, yAttr + 60, 0x8B4513);
        //AGL
        int AGL = 0;
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
            Weapon weapon = (Weapon) stack.getItem();
            AGL = weapon.getAgl(stack);
        }
        text = new TranslationTextComponent("敏捷").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(AGL)).mergeStyle(TextFormatting.GREEN));
        drawString(matrixStack, this.font, text, xAttr, yAttr + 80, 0x8B4513);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(ClientPlayerEntity player) {
        Minecraft.getInstance().displayGuiScreen(new StoryPlayerStatusScreen(player));
    }

}
