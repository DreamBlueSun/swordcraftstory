package com.marisa.swordcraftstory.net;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @description: 通信
 * @date: 2021/9/5 0005 12:41
 */

public class Networking {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("swordcraftstory" + ":first_networking"),
                () -> "1.0", (s) -> true, (s) -> true);
        INSTANCE.registerMessage(nextID(), SendPack.class, SendPack::toBytes, SendPack::new, SendPack::handler);
    }

}
