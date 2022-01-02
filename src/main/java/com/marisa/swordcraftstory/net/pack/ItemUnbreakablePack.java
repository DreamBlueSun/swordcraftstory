package com.marisa.swordcraftstory.net.pack;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


/**
 * 物品设置无法破坏数据包
 */

public class ItemUnbreakablePack {

    private final ItemStack itemStack;

    public ItemUnbreakablePack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemUnbreakablePack(FriendlyByteBuf buffer) {
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
                    if (stack.isDamageableItem()) {
                        stack.getOrCreateTag().putInt("HideFlags", 4);
                        stack.getOrCreateTag().putBoolean("Unbreakable", true);
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
