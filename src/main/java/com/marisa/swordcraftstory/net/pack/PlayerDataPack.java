package com.marisa.swordcraftstory.net.pack;


import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.save.PlayerData;
import com.marisa.swordcraftstory.save.screen.PlayerStatusScreen;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;


/**
 * 玩家信息数据包
 */

public class PlayerDataPack {

    private final String message;
    private final String playerUUID;
    private final int xpLast;
    private final int xp;

    public PlayerDataPack(String message) {
        this.message = message;
        this.playerUUID = "null";
        this.xpLast = 0;
        this.xp = 0;
    }

    public PlayerDataPack(String message, PlayerData data) {
        this.message = message;
        this.playerUUID = data.getPlayerUUID();
        this.xpLast = data.getXpLast();
        this.xp = data.getXp();
    }

    public PlayerDataPack(FriendlyByteBuf buffer) {
        this.message = buffer.readUtf(Short.MAX_VALUE);
        this.playerUUID = buffer.readUtf(Short.MAX_VALUE);
        this.xpLast = buffer.readInt();
        this.xp = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.message);
        buffer.writeUtf(this.playerUUID);
        buffer.writeInt(this.xpLast);
        buffer.writeInt(this.xp);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                switch (this.message) {
                    case "story.player.status.async" -> PlayerDataManager.put(new PlayerData(this));
                    case "story.player.status.client" -> {
                        PlayerDataManager.put(new PlayerData(this));
                        PlayerStatusScreen.open(Minecraft.getInstance().player);
                    }
                }
            } else {
                switch (this.message) {
                    case "story.player.status.open" -> {
                        PlayerDataPack pack = new PlayerDataPack("story.player.status.client", PlayerDataManager.get(sender.getStringUUID()));
                        Networking.PLAYER_DATA.send(PacketDistributor.PLAYER.with(() -> sender), pack);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public int getXpLast() {
        return xpLast;
    }

    public int getXp() {
        return xp;
    }
}
