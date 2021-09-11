package com.marisa.swordcraftstory.keyband;

import com.marisa.swordcraftstory.gui.screen.StoryPlayerStatusScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * @description: 按键绑定
 * @date: 2021/9/12 0012 1:20
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInput {

    public static final KeyBinding STORY_STATUS_KEY = new KeyBinding("个人状态",
            KeyConflictContext.IN_GAME, KeyModifier.CONTROL,
            InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_H,
            "铸剑物语");

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (STORY_STATUS_KEY.isPressed()) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            assert player != null;
            StoryPlayerStatusScreen.open(player);
        }
    }
}
