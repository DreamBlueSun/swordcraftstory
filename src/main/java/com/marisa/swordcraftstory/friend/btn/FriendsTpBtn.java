package com.marisa.swordcraftstory.friend.btn;

import com.marisa.swordcraftstory.friend.net.pack.FriendsDataPack;
import com.marisa.swordcraftstory.net.Networking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

/**
 * 传送到好友按钮
 */

public class FriendsTpBtn extends Button {

    public FriendsTpBtn(int x, int y, int width, int height, Component component, String targetUUID) {
        super(x, y, width, height, component, (btn) -> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && !player.getStringUUID().equals(targetUUID)) {
                Networking.FRIENDS_DATA.sendToServer(new FriendsDataPack("online.players.friends.tp", targetUUID));
                
            }
        });
    }
}
