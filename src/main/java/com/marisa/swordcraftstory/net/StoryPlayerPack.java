package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.gui.screen.StoryPlayerStatusScreen;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;


/**
 * @description:
 * @date: 2021/9/5 0005 12:42
 */

public class StoryPlayerPack {

    private String playerUUID;
    private int xp;

    public StoryPlayerPack(String playerUUID, int xp) {
        this.playerUUID = playerUUID;
        this.xp = xp;
    }

    public StoryPlayerPack(PacketBuffer buffer) {
        this.playerUUID = buffer.readString(Short.MAX_VALUE);
        this.xp = buffer.readInt();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeString(this.playerUUID);
        buffer.writeInt(this.xp);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                //客户端把数据加入内存
                StoryPlayerDataManager.put(new StoryPlayerData(this.playerUUID, this.xp));
                //打开GUI
                StoryPlayerStatusScreen.open(Minecraft.getInstance().player);
            } else {
                //同步数据到客户端
                String playerUUID = sender.getCachedUniqueIdString();
                PacketDistributor.PacketTarget target = PacketDistributor.PLAYER.with(() -> sender);
                StoryPlayerPack msg = new StoryPlayerPack(playerUUID, StoryPlayerDataManager.get(playerUUID).getXp());
                Networking.STORY_PLAYER_STATUS.send(target, msg);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
