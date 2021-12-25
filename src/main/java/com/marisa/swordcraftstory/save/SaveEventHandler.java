package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.save.util.PlayerAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class SaveEventHandler {

    @SubscribeEvent
    public void playerPickupXpEvent(PlayerXpEvent.PickupXp event) {
        //玩家获取经验时，在物语等级系统也增加
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        playerData.addXp(event.getOrb().getValue());
        PlayerAttributesUtils.checkLvUp(player, playerData);
    }

    @SubscribeEvent
    public void SaveToPlayerFile(PlayerEvent.SaveToFile event) {
        //玩家保存数据时，物语的玩家相关数据也保存
        PlayerData.serialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
    }

    @SubscribeEvent
    public void LoadFromPlayerFile(PlayerEvent.LoadFromFile event) {
        //玩家加载数据时，物语的玩家相关数据也加载
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.deserialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
        PlayerAttributesUtils.onClone(player, playerData, false);
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {
        //玩家重生时，加载物语的玩家相关数据
        Player player = event.getPlayer();
        PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
        PlayerAttributesUtils.onClone(player, playerData, event.isWasDeath());
    }

}
