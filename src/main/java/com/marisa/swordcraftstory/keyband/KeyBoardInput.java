package com.marisa.swordcraftstory.keyband;

import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.StoryPlayerPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * 按键绑定
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInput {

    private static final String KEY_BIND_TITLE = "铸剑物语";

    public static final KeyBinding STORY_STATUS_KEY = new KeyBinding("个人状态",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_BIND_TITLE);

    public static final KeyBinding WEAPON_SKILL_CONFIG_KEY = new KeyBinding("武技配置",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_K, KEY_BIND_TITLE);

    public static final KeyBinding WEAPON_SKILL_LEARN_KEY = new KeyBinding("武技进度",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_BIND_TITLE);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if (STORY_STATUS_KEY.isPressed()) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            assert player != null;
            Networking.STORY_PLAYER_INFO.sendToServer(new StoryPlayerPack("story.player.status.open"));
        } else if (WEAPON_SKILL_CONFIG_KEY.isPressed()) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            assert player != null;
            Networking.STORY_PLAYER_INFO.sendToServer(new StoryPlayerPack("weapon.skill.config.open"));
        } else if (WEAPON_SKILL_LEARN_KEY.isPressed()) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            assert player != null;
            Networking.STORY_PLAYER_INFO.sendToServer(new StoryPlayerPack("weapon.skill.learn.open"));
        }
    }
}
