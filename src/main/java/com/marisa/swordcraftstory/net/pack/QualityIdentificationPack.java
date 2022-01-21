package com.marisa.swordcraftstory.net.pack;

import com.marisa.swordcraftstory.smith.util.EQuality;
import com.marisa.swordcraftstory.smith.util.QualityHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


/**
 * 品质鉴定数据包
 */

public class QualityIdentificationPack {

    private final ItemStack itemStack;

    public QualityIdentificationPack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public QualityIdentificationPack(FriendlyByteBuf buffer) {
        this.itemStack = buffer.readItem();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeItem(this.itemStack);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender != null) {
                Inventory inventory = sender.getInventory();
                int slot = inventory.findSlotMatchingItem(this.itemStack);
                if (slot >= 0) {
                    ItemStack stack = inventory.getItem(slot);
                    if (QualityHelper.getQuality(stack) == EQuality.UNKNOWN) {
                        QualityHelper.setQuality(stack, EQuality.randomOne(false));
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
