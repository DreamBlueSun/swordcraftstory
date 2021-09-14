package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.Story;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * 数据通信
 */

public class Networking {

    public static SimpleChannel INSTANCE;
    public static SimpleChannel STORY_PLAYER_STATUS;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void register1() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Story.MOD_ID + ":networking1"),
                () -> "1.0", (s) -> true, (s) -> true);
        INSTANCE.registerMessage(nextID(), SendPack.class, SendPack::toBytes, SendPack::new, SendPack::handler);
    }

    public static void register2() {
        STORY_PLAYER_STATUS = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Story.MOD_ID + ":networking2"),
                () -> "1.0", (s) -> true, (s) -> true);
        STORY_PLAYER_STATUS.registerMessage(nextID(), StoryPlayerPack.class, StoryPlayerPack::toBytes, StoryPlayerPack::new, StoryPlayerPack::handler);
    }

}
