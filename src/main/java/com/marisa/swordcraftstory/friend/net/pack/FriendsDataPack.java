package com.marisa.swordcraftstory.friend.net.pack;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marisa.swordcraftstory.friend.pojo.PlayerVO;
import com.marisa.swordcraftstory.friend.screen.OnlineFriendsScreen;
import com.marisa.swordcraftstory.friend.screen.OnlinePlayersScreen;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.save.PlayerData;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * 玩家好友信息数据包
 */

public class FriendsDataPack {

    private final static Gson GSON = new Gson();

    private final String message;
    private final String playerUUID;
    private final String targetUUID;
    private final List<String> friendsUUID;
    private final List<PlayerVO> onlinePlayer;

    public FriendsDataPack(String message) {
        this.message = message;
        this.playerUUID = "null";
        this.targetUUID = "null";
        this.friendsUUID = new ArrayList<>();
        this.onlinePlayer = new ArrayList<>();
    }

    public FriendsDataPack(String message, String targetUUID) {
        this.message = message;
        this.playerUUID = "null";
        this.targetUUID = targetUUID;
        this.friendsUUID = new ArrayList<>();
        this.onlinePlayer = new ArrayList<>();
    }

    /**
     * 用于展示在线列表、好友列表
     */
    public FriendsDataPack(String message, PlayerData data, List<PlayerVO> onlinePlayer) {
        this.message = message;
        this.playerUUID = data.getPlayerUUID();
        this.targetUUID = "null";
        this.friendsUUID = data.getFriendsUUID();
        this.onlinePlayer = onlinePlayer;
    }

    public FriendsDataPack(FriendlyByteBuf buffer) {
        this.message = buffer.readUtf(Short.MAX_VALUE);
        this.playerUUID = buffer.readUtf(Short.MAX_VALUE);
        this.targetUUID = buffer.readUtf(Short.MAX_VALUE);
        this.friendsUUID = GSON.fromJson(buffer.readUtf(Short.MAX_VALUE), new TypeToken<ArrayList<String>>() {}.getType());
        this.onlinePlayer = GSON.fromJson(buffer.readUtf(Short.MAX_VALUE), new TypeToken<ArrayList<PlayerVO>>() {}.getType());
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.message);
        buffer.writeUtf(this.playerUUID);
        buffer.writeUtf(this.targetUUID);
        buffer.writeUtf(GSON.toJson(this.friendsUUID));
        buffer.writeUtf(GSON.toJson(this.onlinePlayer));
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                PlayerDataManager.update(new PlayerData(this));
                switch (this.message) {
                    case "online.players.screen.client" -> OnlinePlayersScreen.open(this.onlinePlayer);
                    case "online.friends.screen.client" -> OnlineFriendsScreen.open(this.onlinePlayer);
                }
            } else {
                switch (this.message) {
                    case "online.players.screen.open" -> {
                        List<PlayerVO> collect = sender.getLevel().players().stream().map(PlayerVO::of).collect(Collectors.toList());
                        FriendsDataPack pack = new FriendsDataPack("online.players.screen.client", PlayerDataManager.get(sender.getStringUUID()), collect);
                        Networking.FRIENDS_DATA.send(PacketDistributor.PLAYER.with(() -> sender), pack);
                    }
                    case "online.players.friends.add" -> {
                        PlayerData data = PlayerDataManager.get(sender.getStringUUID());
                        if (!data.getFriendsUUID().contains(this.targetUUID)) {
                            data.getFriendsUUID().add(this.targetUUID);
                        }
                    }
                    case "online.friends.screen.open" -> {
                        List<PlayerVO> collect = sender.getLevel().players().stream().map(PlayerVO::of).collect(Collectors.toList());
                        FriendsDataPack pack = new FriendsDataPack("online.friends.screen.client", PlayerDataManager.get(sender.getStringUUID()), collect);
                        Networking.FRIENDS_DATA.send(PacketDistributor.PLAYER.with(() -> sender), pack);
                    }
                    case "online.players.friends.tp" -> {
                        ServerPlayer target = (ServerPlayer) sender.level.getPlayerByUUID(UUID.fromString(this.targetUUID));
                        if (target != null && GameType.SPECTATOR != target.gameMode.getGameModeForPlayer() && target.isAlive()) {
                            List<String> canUUID = PlayerDataManager.get(this.targetUUID).getFriendsUUID();
                            if (canUUID != null && canUUID.contains(sender.getStringUUID())) {
                                sender.teleportTo(target.getX(), target.getY(), target.getZ());
                            } else {
                                sender.displayClientMessage(new TranslatableComponent("msg.online.players.friends.tp.not_in_target_friends").withStyle(ChatFormatting.RED), true);
                            }
                        } else {
                            sender.displayClientMessage(new TranslatableComponent("msg.online.players.friends.tp.target_no_safe").withStyle(ChatFormatting.RED), true);
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public List<String> getFriendsUUID() {
        return friendsUUID;
    }
}
