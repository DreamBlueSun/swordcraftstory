package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.block.tile.*;
import com.marisa.swordcraftstory.item.intensify.helper.Intensify;
import com.marisa.swordcraftstory.item.intensify.helper.IntensifyHelper;
import com.marisa.swordcraftstory.item.model.WeaponModel;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.skill.effect.helper.EffectHelper;
import com.marisa.swordcraftstory.skill.effect.helper.Effects;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.function.Supplier;


/**
 * 锻冶数据包
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
        this.blockPos = BlockPos.ZERO;
    }

    public SendPack(String message, BlockPos blockPos) {
        this.message = message;
        this.blockPos = blockPos;
    }

    public SendPack(String message, BlockPos blockPos, int atkTime, int defTime, int aglTime, int durTime) {
        this.message = message;
        this.blockPos = blockPos;
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
                    case "smithery.weaponMake":
                        //打开制作GUI
                        WeaponMakeTileEntity weaponMakeTile = (WeaponMakeTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, weaponMakeTile, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(weaponMakeTile.getPos()));
                        break;
                    case "smithery.weaponMake.done":
                        //制作确定
                        Inventory inventoryMake = ((WeaponMakeTileEntity) sender.world.getTileEntity(this.blockPos)).getInventory();
                        ItemStack makeStack0 = inventoryMake.getStackInSlot(0);
                        ItemStack makeStack1 = inventoryMake.getStackInSlot(1);
                        if (makeStack0.isEmpty() || makeStack1.isEmpty()) {
                            return;
                        }
                        ItemStack makeStack2 = inventoryMake.getStackInSlot(2);
                        if (!makeStack2.isEmpty()) {
                            return;
                        }
                        ItemStack make = ((AbstractOre) makeStack1.getItem()).weaponMake(makeStack0);
                        if (!make.isEmpty()) {
                            CombatPropertiesUtils.copyEnchantmentTag(makeStack0, make);
                            makeStack0.shrink(1);
                            if (makeStack0.isEmpty()) {
                                inventoryMake.removeStackFromSlot(0);
                            }
                            makeStack1.shrink(1);
                            if (makeStack1.isEmpty()) {
                                inventoryMake.removeStackFromSlot(1);
                            }
                            inventoryMake.setInventorySlotContents(2, make);
                        }
                        break;
                    case "smithery.weaponIntensify":
                        //打开强化GUI
                        WeaponIntensifyTileEntity weaponIntensifyTile = (WeaponIntensifyTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, weaponIntensifyTile, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(weaponIntensifyTile.getPos()));
                        break;
                    case "smithery.weaponIntensify.done":
                        //强化确定
                        Inventory inventoryIntensify = ((WeaponIntensifyTileEntity) sender.world.getTileEntity(this.blockPos)).getInventory();
                        ItemStack intensifyStack0 = inventoryIntensify.getStackInSlot(0);
                        ItemStack intensifyStack1 = inventoryIntensify.getStackInSlot(1);
                        if (intensifyStack0.isEmpty() || intensifyStack1.isEmpty()) {
                            return;
                        }
                        ItemStack intensifyStack2 = inventoryIntensify.getStackInSlot(2);
                        if (!intensifyStack2.isEmpty()) {
                            return;
                        }
                        if (IntensifyHelper.canIntensifyAttr(intensifyStack0)) {
                            IntensifyHelper.intensifyAttr(intensifyStack0, ((Intensify) intensifyStack1.getItem()).getIntensifyAttr());
                            //强化特殊攻击
                            SpecialAttacks specialAttack = SpecialAttacks.checkReach(intensifyStack0);
                            if (specialAttack != null) {
                                SpecialAttackHelper.set(intensifyStack0, specialAttack);
                            }
                            //强化效果
                            Effects effects = Effects.checkReach(intensifyStack0);
                            if (effects != null) {
                                EffectHelper.set(intensifyStack0, effects);
                            }
                            inventoryIntensify.removeStackFromSlot(0);
                            intensifyStack1.shrink(1);
                            if (intensifyStack1.isEmpty()) {
                                inventoryIntensify.removeStackFromSlot(1);
                            }
                            inventoryIntensify.setInventorySlotContents(2, intensifyStack0);
                        }
                        break;
                    case "smithery.intensifyEdge":
                        //打开强刃GUI
                        WeaponEdgeBlockTileEntity weaponEdgeBlockTileEntity = (WeaponEdgeBlockTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, weaponEdgeBlockTileEntity, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(weaponEdgeBlockTileEntity.getPos()));
                        break;
                    case "smithery.intensifyEdge.done":
                        //强刃确定
                        ItemStack inStack = ((WeaponEdgeBlockTileEntity) sender.world.getTileEntity(this.blockPos)).getInventory().getStackInSlot(0);
                        if (inStack.isEmpty() || !(inStack.getItem() instanceof Weapon)) {
                            return;
                        }
                        if (((Weapon) inStack.getItem()).getTec(inStack) == Weapon.MAX_TEC) {
                            //判定点数正常范围
                            if (this.atkTime + this.defTime + this.aglTime + this.durTime == 1) {
                                //执行强刃
                                if (this.atkTime > 0) {
                                    IntensifyHelper.intensifyEdgeAtk(inStack, Weapon.INTENSIFY_EDGE_ONCE_NUM_ATK);
                                }
                                if (this.defTime > 0) {
                                    IntensifyHelper.intensifyEdgeDef(inStack, Weapon.INTENSIFY_EDGE_ONCE_NUM_DEF);
                                }
                                if (this.aglTime > 0) {
                                    IntensifyHelper.intensifyEdgeAgl(inStack, Weapon.INTENSIFY_EDGE_ONCE_NUM_AGL);
                                }
                                if (this.durTime > 0) {
                                    IntensifyHelper.intensifyEdgeDur(inStack, Weapon.INTENSIFY_EDGE_ONCE_NUM_DUR);
                                }
                                CombatPropertiesUtils.clearTec(inStack);
                            }
                        }
                        break;
                    case "smithery.weaponCollapse":
                        //打开解体GUI
                        WeaponCollapseTileEntity weaponCollapseTile = (WeaponCollapseTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, weaponCollapseTile, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(weaponCollapseTile.getPos()));
                        break;
                    case "smithery.weaponCollapse.done":
                        //解体确定
                        World world4 = sender.world;
                        Inventory inventory4 = ((WeaponCollapseTileEntity) world4.getTileEntity(this.blockPos)).getInventory();
                        ItemStack collapseStack0 = inventory4.getStackInSlot(0);
                        if (collapseStack0.isEmpty() || !(collapseStack0.getItem() instanceof Weapon)) {
                            return;
                        }
                        ItemStack collapseStack1 = inventory4.getStackInSlot(1);
                        if (!collapseStack1.isEmpty()) {
                            return;
                        }
                        ItemStack mould = ((Weapon) collapseStack0.getItem()).collapse(collapseStack0);
                        if (!mould.isEmpty()) {
                            inventory4.removeStackFromSlot(0);
                            inventory4.setInventorySlotContents(1, mould);
                        }
                        break;
                    case "smithery.repairAll":
                        //修理
                        if (!sender.isCreative()) {
                            Vector3d vector3d = Vector3d.copyCenteredHorizontally(this.blockPos);
                            List<MonsterEntity> list = sender.world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(vector3d.getX() - 8.0D, vector3d.getY() - 5.0D, vector3d.getZ() - 8.0D, vector3d.getX() + 8.0D, vector3d.getY() + 5.0D, vector3d.getZ() + 8.0D), (p_241146_1_) -> p_241146_1_.func_230292_f_(sender));
                            if (!list.isEmpty()) {
                                sender.sendStatusMessage(new TranslationTextComponent("msg.swordcraftstory.smithery.repairAll.no_safe").mergeStyle(TextFormatting.RED), true);
                                return;
                            }
                        }
                        //执行逻辑
                        PlayerInventory inv = sender.inventory;
                        for (int i = 0; i < inv.mainInventory.size(); i++) {
                            ItemStack stack = inv.mainInventory.get(i);
                            if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
                                stack.removeChildTag("story_combat_broken");
                                stack.setDamage(0);
                                CombatPropertiesUtils.setDur(stack, ((Weapon) stack.getItem()).getDurMaxAfterEffect(stack));
                            }
                        }
                        sender.sendStatusMessage(new TranslationTextComponent("msg.swordcraftstory.smithery.repairAll.ok").mergeStyle(TextFormatting.GREEN), true);
                        break;
                    case "smithery.weaponModelChange":
                        //打开幻化GUI
                        WeaponModelChangeTileEntity weaponModelChangeTile = (WeaponModelChangeTileEntity) sender.world.getTileEntity(this.blockPos);
                        NetworkHooks.openGui(sender, weaponModelChangeTile, (PacketBuffer packerBuffer) ->
                                packerBuffer.writeBlockPos(weaponModelChangeTile.getPos()));
                        break;
                    case "smithery.weaponModelChange.done":
                        //幻化确定
                        Inventory inventoryModelChange = ((WeaponModelChangeTileEntity) sender.world.getTileEntity(this.blockPos)).getInventory();
                        ItemStack modelChangeStack0 = inventoryModelChange.getStackInSlot(0);
                        ItemStack modelChangeStack1 = inventoryModelChange.getStackInSlot(1);
                        if (modelChangeStack0.isEmpty() || modelChangeStack1.isEmpty()) {
                            return;
                        }
                        ItemStack modelChangeStack2 = inventoryModelChange.getStackInSlot(2);
                        if (!modelChangeStack2.isEmpty()) {
                            return;
                        }
                        //执行幻化
                        ((WeaponModel) modelChangeStack1.getItem()).build(modelChangeStack0);
                        //结果
                        inventoryModelChange.removeStackFromSlot(0);
                        modelChangeStack1.shrink(1);
                        if (modelChangeStack1.isEmpty()) {
                            inventoryModelChange.removeStackFromSlot(1);
                        }
                        inventoryModelChange.setInventorySlotContents(2, modelChangeStack0);
                        break;
                    default:
                        break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
