package com.marisa.swordcraftstory.net.pack;

import com.marisa.swordcraftstory.smith.util.Quality;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
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
                    if (SmithNbtUtils.getQuality(stack) == Quality.UNKNOWN) {
                        SmithNbtUtils.setQuality(stack, Quality.randomOne());
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
