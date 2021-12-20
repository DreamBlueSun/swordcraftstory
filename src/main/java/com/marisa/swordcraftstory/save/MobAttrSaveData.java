package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.obj.MobAttr;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

/**
 * 怪物等级属性数据保存
 */

public class MobAttrSaveData extends SavedData {

    private static final String NAME = Story.MOD_ID + ".mob_attr";

    private Map<String, MobAttr> mobAttrMap = new HashMap<>(32);

    public MobAttr get(String uuid) {
        if (this.mobAttrMap.containsKey(uuid)) {
            return this.mobAttrMap.get(uuid);
        }
        return null;
    }

    public void rem(String uuid) {
        this.mobAttrMap.remove(uuid);
        setDirty();
    }

    public void mark(MobAttr mobAttr) {
        this.mobAttrMap.put(mobAttr.getUuid(), mobAttr);
        setDirty();
    }

    public void mark(String uuid, float hp) {
        if (this.mobAttrMap.containsKey(uuid)) {
            MobAttr mobAttr = this.mobAttrMap.get(uuid);
            mobAttr.setHp(hp);
            this.mobAttrMap.put(uuid, mobAttr);
            setDirty();
        }
    }

    public static MobAttrSaveData getInstance(ServerLevel worldIn) {
        return worldIn.getDataStorage().computeIfAbsent(MobAttrSaveData::read, MobAttrSaveData::new, NAME);
    }

    public static MobAttrSaveData read(CompoundTag nbt) {
        ListTag ListTag = (ListTag) nbt.get("list");
        MobAttrSaveData saveData = new MobAttrSaveData();
        if (ListTag != null && ListTag.size() > 0) {
            for (Tag inbt : ListTag) {
                CompoundTag attr = (CompoundTag) inbt;
                String uuid = attr.getString("uuid");
                int lv = attr.getInt("lv");
                int maxHpAdd = attr.getInt("max_hp_add");
                int armorAdd = attr.getInt("armor_add");
                MobAttr mobAttr = new MobAttr(uuid, lv, maxHpAdd, armorAdd);
                mobAttr.setHp(attr.getInt("hp"));
                saveData.mobAttrMap.put(mobAttr.getUuid(), mobAttr);
            }
        }
        return saveData;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        ListTag ListTag = new ListTag();
        for (Map.Entry<String, MobAttr> entry : this.mobAttrMap.entrySet()) {
            MobAttr mobAttr = entry.getValue();
            CompoundTag attr = new CompoundTag();
            attr.put("uuid", StringTag.valueOf(mobAttr.getUuid()));
            attr.put("lv", IntTag.valueOf(mobAttr.getLv()));
            attr.put("max_hp_add", IntTag.valueOf(mobAttr.getMaxHpAdd()));
            attr.put("armor_add", IntTag.valueOf(mobAttr.getArmorAdd()));
            attr.put("hp", FloatTag.valueOf(mobAttr.getHp()));
            ListTag.add(attr);
        }
        compound.put("list", ListTag);
        return compound;
    }
}
