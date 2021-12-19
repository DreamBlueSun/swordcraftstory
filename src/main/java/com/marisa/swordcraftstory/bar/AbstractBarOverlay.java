package com.marisa.swordcraftstory.bar;

import com.marisa.swordcraftstory.Story;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public abstract class AbstractBarOverlay implements IBarOverlay {

    public final ResourceLocation PLAYER_BAR = new ResourceLocation(Story.MOD_ID, "textures/gui/player_bar.png");

    public void renderAll(Player player, PoseStack poseStack) {
        renderBar(player, poseStack);
        renderText(player, poseStack);
        renderIcon(player, poseStack);
    }
}
