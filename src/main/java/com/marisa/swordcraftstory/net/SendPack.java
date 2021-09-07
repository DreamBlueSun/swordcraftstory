package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.block.tile.SmithingBlockTileEntity;
import com.marisa.swordcraftstory.item.combat.Combat;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;


/**
 * @description:
 * @date: 2021/9/5 0005 12:42
 */

public class SendPack {

    private String message;

    private BlockPos blockPos;

    public SendPack(String message) {
        this.message = message;
    }

    public SendPack(String message, BlockPos blockPos) {
        this.message = message;
        this.blockPos = blockPos;
    }

    public SendPack(PacketBuffer buffer) {
        this.message = buffer.readString(Short.MAX_VALUE);
        this.blockPos = buffer.readBlockPos();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeString(this.message);
        buffer.writeBlockPos(this.blockPos);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender != null && this.message != null) {
                switch (this.message) {
                    case "smithery.intensifyEdge":
                        SmithingBlockTileEntity smithingBlockTileEntity = (SmithingBlockTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, smithingBlockTileEntity, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(smithingBlockTileEntity.getPos()));
                        break;
                    case "smithery.repairAll":
                        PlayerInventory inv = sender.inventory;
                        for (int i = 0; i < inv.mainInventory.size(); i++) {
                            ItemStack stack = inv.mainInventory.get(i);
                            if (stack.getItem() instanceof Combat) {
                                stack.setDamage(0);
                                inv.setInventorySlotContents(i, stack);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
