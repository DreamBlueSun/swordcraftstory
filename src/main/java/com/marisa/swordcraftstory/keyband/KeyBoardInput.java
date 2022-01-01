package com.marisa.swordcraftstory.keyband;

import com.marisa.swordcraftstory.friend.net.pack.FriendsDataPack;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.PlayerDataPack;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
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

    public static final KeyMapping STORY_STATUS_KEY = new KeyMapping("个人状态",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_BIND_TITLE);

    public static final KeyMapping ONLINE_FRIENDS_KEY = new KeyMapping("好友列表",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, KEY_BIND_TITLE);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (STORY_STATUS_KEY.consumeClick()) {
            Networking.PLAYER_DATA.sendToServer(new PlayerDataPack("story.player.status.open"));
        } else if (ONLINE_FRIENDS_KEY.consumeClick()) {
            Networking.FRIENDS_DATA.sendToServer(new FriendsDataPack("online.friends.screen.open"));
        }
    }
}
