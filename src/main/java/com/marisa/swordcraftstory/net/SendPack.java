package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.item.combat.Combat;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


/**
 * @description:
 * @date: 2021/9/5 0005 12:42
 */

public class SendPack {
    private String message;
//    private static final Logger LOGGER = LogManager.getLogger();

    public SendPack(PacketBuffer buffer) {
        message = buffer.readString(Short.MAX_VALUE);
    }

    public SendPack(String message) {
        this.message = message;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeString(this.message);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender != null && "smithery.repairAll".equals(this.message)) {
                PlayerInventory inv = sender.inventory;
                for (int i = 0; i < inv.mainInventory.size(); i++) {
                    ItemStack stack = inv.mainInventory.get(i);
                    if (stack.getItem() instanceof Combat) {
                        stack.setDamage(0);
                        inv.setInventorySlotContents(i,stack);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
