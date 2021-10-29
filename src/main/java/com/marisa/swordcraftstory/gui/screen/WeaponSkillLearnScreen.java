package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.helper.WeaponSkillLearn;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 武技学习进度Screen
 */

public class WeaponSkillLearnScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/config.png");

    private final ClientPlayerEntity player;

    protected WeaponSkillLearnScreen(ClientPlayerEntity player) {
        super(new StringTextComponent("weapon_skill_learn_screen"));
        this.player = player;
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float particleTick) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(resourceLocation);
        }
        blit(matrixStack, this.width / 2 - 180, this.height / 2 - 120, 0, 0, 360, 240, 360, 240);
        //玩家名称
        IFormattableTextComponent componentName = new TranslationTextComponent(player.getDisplayName().getString()).mergeStyle(TextFormatting.BLUE);
        drawString(matrixStack, this.font, componentName, this.width / 2 - 156, this.height / 2 - 89, 0x8B4513);
        //武技进度
        int flagX = 0;
        int flagY = 0;
        int xi = this.width / 2 - 156;
        int yi = this.height / 2 - 54;
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        int[] intsId = data.getMapLearnWeaponSkillId().keySet().stream().map(Integer::parseInt).mapToInt(i -> i).toArray();
        int[] intsProgress = data.getMapLearnWeaponSkillId().values().stream().mapToInt(i -> i).toArray();
        List<WeaponSkillLearn> list = new ArrayList<>();
        for (int i = 0; i < intsId.length; i++) {
            list.add(new WeaponSkillLearn(WeaponSkills.getById(String.valueOf(intsId[i])).getSkill(), intsProgress[i]));
        }
        list = list.stream().filter(WeaponSkillLearn::nonNull).sorted(Comparator.comparing(i -> i.getSkill().getId())).collect(Collectors.toList());
        for (WeaponSkillLearn learn : list) {
            if (flagX > 2) {
                flagX = 0;
                flagY++;
            }
            IFormattableTextComponent component = new TranslationTextComponent(learn.getSkill().getName()).mergeStyle(TextFormatting.LIGHT_PURPLE)
                    .appendString(" ").appendSibling(new TranslationTextComponent(learn.getProgress() + "/" + learn.getSkill().getLearnPoint()).mergeStyle(TextFormatting.GREEN));
            drawString(matrixStack, this.font, component, xi + (flagX * 104), yi + (flagY * 20), 0x8B4513);
            flagX++;
        }
        for (int i = 0; i < intsId.length; i++) {
        }
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    public static void open(ClientPlayerEntity player) {
        Minecraft.getInstance().displayGuiScreen(new WeaponSkillLearnScreen(player));
    }

}
