package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.obj.MobAttr;
import net.minecraft.nbt.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;

/**
 * 怪物等级属性数据保存
 */

public class MobAttrSaveData extends WorldSavedData {

    private static final String NAME = Story.MOD_ID + ".mob_attr";

    private Map<String, MobAttr> mobAttrMap = new HashMap<>(32);

    public MobAttrSaveData() {
        super(NAME);
    }

    public MobAttr get(String uuid) {
        if (this.mobAttrMap.containsKey(uuid)) {
            return this.mobAttrMap.get(uuid);
        }
        return null;
    }

    public void rem(String uuid) {
        this.mobAttrMap.remove(uuid);
        markDirty();
    }

    public void mark(MobAttr mobAttr) {
        this.mobAttrMap.put(mobAttr.getUuid(), mobAttr);
        markDirty();
    }

    public void mark(String uuid, float hp) {
        if (this.mobAttrMap.containsKey(uuid)) {
            MobAttr mobAttr = this.mobAttrMap.get(uuid);
            mobAttr.setHp(hp);
            this.mobAttrMap.put(uuid, mobAttr);
            markDirty();
        }
    }

    public static MobAttrSaveData getInstance(ServerWorld worldIn) {
        return worldIn.getSavedData().getOrCreate(MobAttrSaveData::new, NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        ListNBT listNBT = (ListNBT) nbt.get("list");
        if (listNBT != null && listNBT.size() > 0) {
            for (net.minecraft.nbt.INBT inbt : listNBT) {
                CompoundNBT attr = (CompoundNBT) inbt;
                String uuid = attr.getString("uuid");
                int lv = attr.getInt("lv");
                int maxHpAdd = attr.getInt("max_hp_add");
                int armorAdd = attr.getInt("armor_add");
                MobAttr mobAttr = new MobAttr(uuid, lv, maxHpAdd, armorAdd);
                mobAttr.setHp(attr.getInt("hp"));
                this.mobAttrMap.put(mobAttr.getUuid(), mobAttr);
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT listNBT = new ListNBT();
        for (Map.Entry<String, MobAttr> entry : this.mobAttrMap.entrySet()) {
            MobAttr mobAttr = entry.getValue();
            CompoundNBT attr = new CompoundNBT();
            attr.put("uuid", StringNBT.valueOf(mobAttr.getUuid()));
            attr.put("lv", IntNBT.valueOf(mobAttr.getLv()));
            attr.put("max_hp_add", IntNBT.valueOf(mobAttr.getMaxHpAdd()));
            attr.put("armor_add", IntNBT.valueOf(mobAttr.getArmorAdd()));
            attr.put("hp", FloatNBT.valueOf(mobAttr.getHp()));
            listNBT.add(attr);
        }
        compound.put("list", listNBT);
        return compound;
    }
}
