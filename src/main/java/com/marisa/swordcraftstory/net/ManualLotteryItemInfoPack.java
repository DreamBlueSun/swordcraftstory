package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.gui.screen.ManualLotteryItemInfoScreen;
import com.marisa.swordcraftstory.gui.screen.ManualLotteryItemInfoScreenData;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;


/**
 * 手摇抽奖机物品信息数据包
 */

public class ManualLotteryItemInfoPack {

    private String itemName1;
    private String itemName2;
    private String itemName3;
    private String itemName4;
    private String itemName5;

    public ManualLotteryItemInfoPack() {
        this.itemName1 = DropQualityManualLotteryMachine.getRank1().getItem().getName().getString();
        this.itemName2 = DropQualityManualLotteryMachine.getRank2().getItem().getName().getString();
        this.itemName3 = DropQualityManualLotteryMachine.getRank3().getItem().getName().getString();
        this.itemName4 = DropQualityManualLotteryMachine.getRank4().getItem().getName().getString();
        this.itemName5 = DropQualityManualLotteryMachine.getRank5().getItem().getName().getString();
    }

    public ManualLotteryItemInfoPack(PacketBuffer buffer) {
        this.itemName1 = buffer.readString(Short.MAX_VALUE);
        this.itemName2 = buffer.readString(Short.MAX_VALUE);
        this.itemName3 = buffer.readString(Short.MAX_VALUE);
        this.itemName4 = buffer.readString(Short.MAX_VALUE);
        this.itemName5 = buffer.readString(Short.MAX_VALUE);
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeString(this.itemName1);
        buffer.writeString(this.itemName2);
        buffer.writeString(this.itemName3);
        buffer.writeString(this.itemName4);
        buffer.writeString(this.itemName5);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                //打开GUI
                ManualLotteryItemInfoScreenData data = new ManualLotteryItemInfoScreenData(this.itemName1, this.itemName2, this.itemName3, this.itemName4, this.itemName5);
                ManualLotteryItemInfoScreen.open(Minecraft.getInstance().player, data);
            } else {
                //同步数据到客户端
                PacketDistributor.PacketTarget target = PacketDistributor.PLAYER.with(() -> sender);
                Networking.MANUAL_LOTTERY_ITEM_INFO.send(target, new ManualLotteryItemInfoPack());
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
