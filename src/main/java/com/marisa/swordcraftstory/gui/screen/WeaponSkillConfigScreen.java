package com.marisa.swordcraftstory.gui.screen;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.helper.WeaponSkillCheckbox;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.StoryPlayerPack;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.skill.weapon.helper.AbstractWeaponSkill;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 武技配置Screen
 */

public class WeaponSkillConfigScreen extends Screen {

    ResourceLocation resourceLocation = new ResourceLocation(Story.MOD_ID, "textures/gui/config.png");

    private final ClientPlayerEntity player;

    private int amount;

    protected WeaponSkillConfigScreen(ClientPlayerEntity player) {
        super(new StringTextComponent("weapon_skill_config_screen"));
        this.player = player;
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        this.amount = StoryPlayerDataManager.getWeaponSkillAmount(StoryPlayerDataManager.getLv(data.getXp()));
    }

    @Override
    protected void init() {
        Minecraft.getInstance().keyboardListener.enableRepeatEvents(true);
        int x = this.width / 2;
        int y = this.height / 2;
        //确认
        this.addButton(new Button(x + 132, y - 94, 32, 20, new StringTextComponent("确认"), (button) -> {
            List<Integer> list = new ArrayList<>();
            for (Widget widget : this.buttons) {
                if (widget instanceof WeaponSkillCheckbox && ((WeaponSkillCheckbox) widget).isChecked()) {
                    list.add(((WeaponSkillCheckbox) widget).getSkill().getId());
                }
            }
            Networking.STORY_PLAYER_INFO.sendToServer(new StoryPlayerPack("weapon.skill.config.confirm", list));
        }));
        //checkbox武技
        int flagX = 0;
        int flagY = 0;
        int xi = x - 156;
        int yi = y - 54;
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        List<AbstractWeaponSkill> collect = data.getListHaveWeaponSkillId().stream()
                .map(WeaponSkills::getById).map(WeaponSkills::getSkill).filter(Objects::nonNull)
                .sorted(Comparator.comparing(AbstractWeaponSkill::getId)).collect(Collectors.toList());
        List<AbstractWeaponSkill> collect2 = data.getListConfigWeaponSkillId().stream()
                .map(WeaponSkills::getById).map(WeaponSkills::getSkill).filter(Objects::nonNull)
                .sorted(Comparator.comparing(AbstractWeaponSkill::getId)).collect(Collectors.toList());
        for (AbstractWeaponSkill skill : collect) {
            if (collect2.contains(skill)) {
                this.amount -= skill.getCost();
            }
        }
        for (AbstractWeaponSkill skill : collect) {
            if (flagX > 2) {
                flagX = 0;
                flagY++;
            }
            boolean checked = collect2.contains(skill);
            IFormattableTextComponent component = new TranslationTextComponent(String.valueOf(skill.getCost())).mergeStyle(TextFormatting.GOLD)
                    .appendSibling(new TranslationTextComponent("-").mergeStyle(TextFormatting.BLUE))
                    .appendSibling(new TranslationTextComponent(skill.getName()).mergeStyle(TextFormatting.LIGHT_PURPLE));
            WeaponSkillCheckbox checkbox = new WeaponSkillCheckbox(xi + (flagX * 104), yi + (flagY * 20), 20, 20, component, checked, this.amount, skill, this::onChange);
            this.addButton(checkbox);
            flagX++;
        }
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
        IFormattableTextComponent component1 = new TranslationTextComponent(player.getDisplayName().getString()).mergeStyle(TextFormatting.BLUE);
        drawString(matrixStack, this.font, component1, this.width / 2 - 156, this.height / 2 - 89, 0x8B4513);
        //剩余武技点数
        IFormattableTextComponent component2 = new TranslationTextComponent("武技点数：" + this.amount).mergeStyle(TextFormatting.GREEN);
        drawString(matrixStack, this.font, component2, this.width / 2 + 72, this.height / 2 - 89, 0x8B4513);
        super.render(matrixStack, mouseX, mouseY, particleTick);
    }

    private void onChange(boolean checked, int value) {
        if (checked) {
            this.amount -= value;
        } else {
            this.amount += value;
        }
    }

    public static void open(ClientPlayerEntity player) {
        Minecraft.getInstance().displayGuiScreen(new WeaponSkillConfigScreen(player));
    }

}
