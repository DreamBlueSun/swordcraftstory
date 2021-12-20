package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.obj.DropQualityManualLotteryMachine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * 天时间和抽奖机数据保存
 */

public class ManualLotteryMachineSaveData extends SavedData {

    private static final String NAME = Story.MOD_ID + ".manual_lottery_machine";

    private int worldDay;
    private ItemStack RANK_1;
    private ItemStack RANK_2;
    private ItemStack RANK_3;
    private ItemStack RANK_4;
    private ItemStack RANK_5;

    public ManualLotteryMachineSaveData() {
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
        setDirty();
    }

    public static ManualLotteryMachineSaveData get(ServerLevel worldIn) {
        return worldIn.getDataStorage().computeIfAbsent(ManualLotteryMachineSaveData::read, ManualLotteryMachineSaveData::new, NAME);
    }

//    public static ManualLotteryMachineSaveData get(ServerChunkProvider provider) {
//        return provider.get.getOrCreate(ManualLotteryMachineSaveData::new, NAME);
//    }

    public static ManualLotteryMachineSaveData read(CompoundTag nbt) {
        ListTag ListTag = (ListTag) nbt.get("list");
        ManualLotteryMachineSaveData saveData = new ManualLotteryMachineSaveData();
        if (ListTag != null && ListTag.size() == 6) {
            saveData.worldDay = ((CompoundTag) ListTag.get(0)).getInt("world_day");
            saveData.RANK_1 = ItemStack.of(((CompoundTag) ListTag.get(1)).getCompound("items_tack"));
            saveData.RANK_2 = ItemStack.of(((CompoundTag) ListTag.get(2)).getCompound("items_tack"));
            saveData.RANK_3 = ItemStack.of(((CompoundTag) ListTag.get(3)).getCompound("items_tack"));
            saveData.RANK_4 = ItemStack.of(((CompoundTag) ListTag.get(4)).getCompound("items_tack"));
            saveData.RANK_5 = ItemStack.of(((CompoundTag) ListTag.get(5)).getCompound("items_tack"));
        }
        return saveData;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        ListTag ListTag = new ListTag();
        CompoundTag c0 = new CompoundTag();
        c0.put("world_day", IntTag.valueOf(this.worldDay));
        ListTag.add(c0);
        CompoundTag c1 = new CompoundTag();
        c1.put("items_tack", this.RANK_1.serializeNBT());
        ListTag.add(c1);
        CompoundTag c2 = new CompoundTag();
        c2.put("items_tack", this.RANK_2.serializeNBT());
        ListTag.add(c2);
        CompoundTag c3 = new CompoundTag();
        c3.put("items_tack", this.RANK_3.serializeNBT());
        ListTag.add(c3);
        CompoundTag c4 = new CompoundTag();
        c4.put("items_tack", this.RANK_4.serializeNBT());
        ListTag.add(c4);
        CompoundTag c5 = new CompoundTag();
        c5.put("items_tack", this.RANK_5.serializeNBT());
        ListTag.add(c5);
        compound.put("list", ListTag);
        return compound;
    }
}
