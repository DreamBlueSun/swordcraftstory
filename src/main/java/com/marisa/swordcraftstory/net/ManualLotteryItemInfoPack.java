package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.gui.screen.ManualLotteryItemInfoScreen;
import com.marisa.swordcraftstory.gui.screen.ManualLotteryItemInfoScreenData;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


/**
 * 手摇抽奖机物品信息数据包
 */

public class ManualLotteryItemInfoPack {

    private ItemStack itemStack1;
    private ItemStack itemStack2;
    private ItemStack itemStack3;
    private ItemStack itemStack4;
    private ItemStack itemStack5;

    public ManualLotteryItemInfoPack() {
        this.itemStack1 = DropQualityManualLotteryMachine.getRank1();
        this.itemStack2 = DropQualityManualLotteryMachine.getRank2();
        this.itemStack3 = DropQualityManualLotteryMachine.getRank3();
        this.itemStack4 = DropQualityManualLotteryMachine.getRank4();
        this.itemStack5 = DropQualityManualLotteryMachine.getRank5();
    }

    public ManualLotteryItemInfoPack(PacketBuffer buffer) {
        this.itemStack1 = buffer.readItemStack();
        this.itemStack2 = buffer.readItemStack();
        this.itemStack3 = buffer.readItemStack();
        this.itemStack4 = buffer.readItemStack();
        this.itemStack5 = buffer.readItemStack();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeItemStack(this.itemStack1);
        buffer.writeItemStack(this.itemStack2);
        buffer.writeItemStack(this.itemStack3);
        buffer.writeItemStack(this.itemStack4);
        buffer.writeItemStack(this.itemStack5);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                //打开GUI
                ManualLotteryItemInfoScreenData data = new ManualLotteryItemInfoScreenData(this.itemStack1, this.itemStack2, this.itemStack3, this.itemStack4, this.itemStack5);
                ManualLotteryItemInfoScreen.open(Minecraft.getInstance().player, data);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
