package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.block.tile.SmithingBlockTileEntity;
import com.marisa.swordcraftstory.item.combat.Combat;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
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

    /**
     * 读取的锻冶台方块坐标信息
     */
    private BlockPos blockPos;

    /**
     * 读取的强刃的分配点数信息
     */
    private int atkTime;
    private int defTime;
    private int aglTime;
    private int durTime;

    public SendPack(String message) {
        this.message = message;
    }

    public SendPack(String message, BlockPos blockPos) {
        this.message = message;
        this.blockPos = blockPos;
    }

    public SendPack(String message, int atkTime, int defTime, int aglTime, int durTime) {
        this.message = message;
        this.atkTime = atkTime;
        this.defTime = defTime;
        this.aglTime = aglTime;
        this.durTime = durTime;
    }

    public SendPack(PacketBuffer buffer) {
        this.message = buffer.readString(Short.MAX_VALUE);
        this.blockPos = buffer.readBlockPos();
        this.atkTime = buffer.readInt();
        this.defTime = buffer.readInt();
        this.aglTime = buffer.readInt();
        this.durTime = buffer.readInt();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeString(this.message);
        buffer.writeBlockPos(this.blockPos);
        buffer.writeInt(this.atkTime);
        buffer.writeInt(this.defTime);
        buffer.writeInt(this.aglTime);
        buffer.writeInt(this.durTime);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender != null && this.message != null) {
                switch (this.message) {
                    case "smithery.intensifyEdge":
                        //打开强刃GUI
                        SmithingBlockTileEntity smithingBlockTileEntity = (SmithingBlockTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, smithingBlockTileEntity, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(smithingBlockTileEntity.getPos()));
                        break;
                    case "smithery.intensifyEdge.done":
                        //强刃确定
                        ItemStack inStack = ((SmithingBlockTileEntity) sender.world.getTileEntity(this.blockPos)).getInventory().getStackInSlot(0);
                        if (((Combat) inStack.getItem()).getTec(inStack) == Combat.MAX_TEC) {
                            //判定点数正常范围
                            if (this.atkTime + this.defTime + this.aglTime + this.durTime == 1) {
                                //执行强刃
                                if (this.atkTime > 0) {
                                    CombatPropertiesUtils.intensifyEdgeAtk(inStack);
                                }
                                if (this.defTime > 0) {
                                    CombatPropertiesUtils.intensifyEdgeDef(inStack);
                                }
                                if (this.aglTime > 0) {
                                    CombatPropertiesUtils.intensifyEdgeAgl(inStack);
                                }
                                if (this.durTime > 0) {
                                    CombatPropertiesUtils.intensifyEdgeDur(inStack);
                                }
                            }
                        }
                        break;
                    case "smithery.repairAll":
                        //修理
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
