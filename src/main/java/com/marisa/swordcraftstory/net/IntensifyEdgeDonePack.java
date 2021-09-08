//package com.marisa.swordcraftstory.net;
//
//import com.marisa.swordcraftstory.block.tile.SmithingBlockTileEntity;
//import com.marisa.swordcraftstory.item.combat.Combat;
//import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraftforge.fml.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
///**
// * @description:
// * @date: 2021/9/8 0008 19:51
// */
//
//public class IntensifyEdgeDonePack {
//
//    /**
//     * 读取的锻冶台方块坐标信息
//     */
//    private BlockPos blockPos;
//
//    /**
//     * 读取的强刃的分配点数信息
//     */
//    private int atkTime;
//    private int defTime;
//    private int aglTime;
//    private int durTime;
//
//    public IntensifyEdgeDonePack(String message, int atkTime, int defTime, int aglTime, int durTime) {
//        this.blockPos = blockPos;
//        this.atkTime = atkTime;
//        this.defTime = defTime;
//        this.aglTime = aglTime;
//        this.durTime = durTime;
//    }
//
//    public IntensifyEdgeDonePack(PacketBuffer buffer) {
//        this.atkTime = buffer.readInt();
//        this.defTime = buffer.readInt();
//        this.aglTime = buffer.readInt();
//        this.durTime = buffer.readInt();
//    }
//
//    public void toBytes(PacketBuffer buffer) {
//        buffer.writeInt(this.atkTime);
//        buffer.writeInt(this.defTime);
//        buffer.writeInt(this.aglTime);
//        buffer.writeInt(this.durTime);
//    }
//
//    public void handler(Supplier<NetworkEvent.Context> ctx) {
//        ServerPlayerEntity sender = ctx.get().getSender();
//        ctx.get().enqueueWork(() -> {
//            if (sender != null) {
//                //强刃确定
//                SmithingBlockTileEntity tileEntity = (SmithingBlockTileEntity) sender.world.getTileEntity(this.blockPos);
//                ItemStack inStack = tileEntity.getInventory().getStackInSlot(0);
//                if (((Combat) inStack.getItem()).getTec(inStack) == Combat.MAX_TEC) {
//                    //判定点数正常范围
//                    if (this.atkTime + this.defTime + this.aglTime + this.durTime == 1) {
//                        //执行强刃
//                        if (this.atkTime > 0) {
//                            CombatPropertiesUtils.intensifyEdgeAtk(inStack);
//                        }
//                        if (this.defTime > 0) {
//                            CombatPropertiesUtils.intensifyEdgeDef(inStack);
//                        }
//                        if (this.aglTime > 0) {
//                            CombatPropertiesUtils.intensifyEdgeAgl(inStack);
//                        }
//                        if (this.durTime > 0) {
//                            CombatPropertiesUtils.intensifyEdgeDur(inStack);
//                        }
//                    }
//                }
//            }
//        });
//        ctx.get().setPacketHandled(true);
//    }
//}
