package com.marisa.swordcraftstory.item.intensify.helper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * 强化工具类
 */

public class IntensifyHelper {

    /**
     * 判断可以进行强化武器（3次上限）
     */
    public static boolean canIntensifyAttr(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            ListTag listNBT = (ListTag) tag.get("story_combat_intensify");
            return listNBT.size() < 3;
        }
        return true;
    }

    /**
     * 强化武器属性
     */
    public static void intensifyAttr(ItemStack stack, IntensifyAttr attr) {
        intensifyEdgeAtk(stack, attr.getAtk());
        intensifyEdgeDef(stack, attr.getDef());
        intensifyEdgeAgl(stack, attr.getAgl());
        intensifyEdgeDur(stack, attr.getDur());
        //添加强化NBT
        CompoundTag tag = stack.getOrCreateTag();
        ListTag listNBT;
        if (tag.contains("story_combat_intensify")) {
            listNBT = (ListTag) tag.get("story_combat_intensify").copy();
        } else {
            listNBT = new ListTag();
        }
        listNBT.add(IntTag.valueOf(attr.getId()));
        tag.put("story_combat_intensify", listNBT);
    }

    /**
     * 获取强化ID列表
     */
    public static List<Integer> getIntensifyIds(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<Integer> list = new ArrayList<>();
            ListTag listNBT = (ListTag) tag.get("story_combat_intensify").copy();
            for (int i = 0; i < listNBT.size(); i++) {
                list.add(Intensifys.getById(listNBT.getInt(i)).getId());
            }
            return list;
        }
        return null;
    }

    /**
     * 获取强化展示名称列表
     */
    public static List<String> getIntensifyName(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<String> list = new ArrayList<>();
            ListTag listNBT = (ListTag) tag.get("story_combat_intensify").copy();
            for (int i = 0; i < listNBT.size(); i++) {
                list.add(Intensifys.getById(listNBT.getInt(i)).getShow());
            }
            return list;
        }
        return null;
    }

    public static void intensifyEdgeAtk(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_atk");
        tag.put("story_combat_atk", IntTag.valueOf(v + amount));
    }

    public static void intensifyEdgeDef(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_def");
        tag.put("story_combat_def", IntTag.valueOf(v + amount));
    }

    public static void intensifyEdgeAgl(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_agl");
        tag.put("story_combat_agl", IntTag.valueOf(v + amount));
    }

    public static void intensifyEdgeDur(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_dur_max");
        tag.put("story_combat_dur_max", IntTag.valueOf(v + amount));
    }
}
