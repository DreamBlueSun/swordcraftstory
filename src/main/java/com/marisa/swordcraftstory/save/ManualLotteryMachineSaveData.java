package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

/**
 * 天时间和抽奖机数据保存
 */

public class ManualLotteryMachineSaveData extends WorldSavedData {

    private static final String NAME = Story.MOD_ID + ".manual_lottery_machine";

    public int worldDay;
    private ItemStack RANK_1;
    private ItemStack RANK_2;
    private ItemStack RANK_3;
    private ItemStack RANK_4;
    private ItemStack RANK_5;

    public ManualLotteryMachineSaveData() {
        super(NAME);
        this.worldDay = -1;
        this.RANK_1 = Items.AIR.getDefaultInstance();
        this.RANK_2 = Items.AIR.getDefaultInstance();
        this.RANK_3 = Items.AIR.getDefaultInstance();
        this.RANK_4 = Items.AIR.getDefaultInstance();
        this.RANK_5 = Items.AIR.getDefaultInstance();
    }

    public int getWorldDay() {
        return worldDay;
    }

    public ItemStack getRANK_1() {
        return RANK_1.copy();
    }

    public ItemStack getRANK_2() {
        return RANK_2.copy();
    }

    public ItemStack getRANK_3() {
        return RANK_3.copy();
    }

    public ItemStack getRANK_4() {
        return RANK_4.copy();
    }

    public ItemStack getRANK_5() {
        return RANK_5.copy();
    }

    public void mark() {
        this.worldDay = DayTimeManager.getWorldDay();
        this.RANK_1 = DropQualityManualLotteryMachine.getRank1();
        this.RANK_2 = DropQualityManualLotteryMachine.getRank2();
        this.RANK_3 = DropQualityManualLotteryMachine.getRank3();
        this.RANK_4 = DropQualityManualLotteryMachine.getRank4();
        this.RANK_5 = DropQualityManualLotteryMachine.getRank5();
        markDirty();
    }

    public static ManualLotteryMachineSaveData get(ServerWorld worldIn) {
        return worldIn.getSavedData().getOrCreate(ManualLotteryMachineSaveData::new, NAME);
    }

    public static ManualLotteryMachineSaveData get(ServerChunkProvider provider) {
        return provider.getSavedData().getOrCreate(ManualLotteryMachineSaveData::new, NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        ListNBT listNBT = (ListNBT) nbt.get("list");
        if (listNBT != null && listNBT.size() == 6) {
            this.worldDay = ((CompoundNBT) listNBT.get(0)).getInt("world_day");
            this.RANK_1 = ItemStack.read(((CompoundNBT) listNBT.get(1)).getCompound("items_tack"));
            this.RANK_2 = ItemStack.read(((CompoundNBT) listNBT.get(2)).getCompound("items_tack"));
            this.RANK_3 = ItemStack.read(((CompoundNBT) listNBT.get(3)).getCompound("items_tack"));
            this.RANK_4 = ItemStack.read(((CompoundNBT) listNBT.get(4)).getCompound("items_tack"));
            this.RANK_5 = ItemStack.read(((CompoundNBT) listNBT.get(5)).getCompound("items_tack"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT listNBT = new ListNBT();
        CompoundNBT c0 = new CompoundNBT();
        c0.put("world_day", IntNBT.valueOf(this.worldDay));
        listNBT.add(c0);
        CompoundNBT c1 = new CompoundNBT();
        c1.put("items_tack", this.RANK_1.serializeNBT());
        listNBT.add(c1);
        CompoundNBT c2 = new CompoundNBT();
        c2.put("items_tack", this.RANK_2.serializeNBT());
        listNBT.add(c2);
        CompoundNBT c3 = new CompoundNBT();
        c3.put("items_tack", this.RANK_3.serializeNBT());
        listNBT.add(c3);
        CompoundNBT c4 = new CompoundNBT();
        c4.put("items_tack", this.RANK_4.serializeNBT());
        listNBT.add(c4);
        CompoundNBT c5 = new CompoundNBT();
        c5.put("items_tack", this.RANK_5.serializeNBT());
        listNBT.add(c5);
        compound.put("list", listNBT);
        return compound;
    }
}
