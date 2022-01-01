package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.friend.net.pack.FriendsDataPack;
import com.marisa.swordcraftstory.net.pack.PlayerDataPack;
import com.marisa.swordcraftstory.net.pack.QualityIdentificationPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * 数据通信
 */

public class Networking {

    private static int ID = 0;
    public static int nextID() {
        return ID++;
    }

    public static SimpleChannel PLAYER_DATA;
    public static SimpleChannel QUALITY_IDENTIFICATION;
    public static SimpleChannel FRIENDS_DATA;

    public static void registerPlayerData() {
        PLAYER_DATA = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Story.MOD_ID + ":networking_player_data"),
                () -> "1.0", (s) -> true, (s) -> true);
        PLAYER_DATA.registerMessage(nextID(), PlayerDataPack.class, PlayerDataPack::toBytes, PlayerDataPack::new, PlayerDataPack::handler);
    }

    public static void registerQualityIdentification() {
        QUALITY_IDENTIFICATION = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Story.MOD_ID + ":networking_quality_identification"),
                () -> "1.0", (s) -> true, (s) -> true);
        QUALITY_IDENTIFICATION.registerMessage(nextID(), QualityIdentificationPack.class, QualityIdentificationPack::toBytes, QualityIdentificationPack::new, QualityIdentificationPack::handler);
    }

    public static void registerFriendsData() {
        FRIENDS_DATA = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Story.MOD_ID + ":networking_friends_data"),
                () -> "1.0", (s) -> true, (s) -> true);
        FRIENDS_DATA.registerMessage(nextID(), FriendsDataPack.class, FriendsDataPack::toBytes, FriendsDataPack::new, FriendsDataPack::handler);
    }

}
