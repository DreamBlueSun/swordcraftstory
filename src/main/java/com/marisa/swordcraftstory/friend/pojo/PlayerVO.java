package com.marisa.swordcraftstory.friend.pojo;

import net.minecraft.server.level.ServerPlayer;

/**
 * 列表玩家信息VO
 */

public class PlayerVO {

    private String uuid;
    private String name;

    public PlayerVO(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PlayerVO of(ServerPlayer player) {
        return new PlayerVO(player.getStringUUID(), player.getDisplayName().getString());
    }
}
