package com.marisa.swordcraftstory.item.intensify.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;

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
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify");
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
        CompoundNBT tag = stack.getOrCreateTag();
        ListNBT listNBT;
        if (tag.contains("story_combat_intensify")) {
            listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
        } else {
            listNBT = new ListNBT();
        }
        listNBT.add(IntNBT.valueOf(attr.getId()));
        tag.put("story_combat_intensify", listNBT);
    }

    /**
     * 获取强化ID列表
     */
    public static List<Integer> getIntensifyIds(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<Integer> list = new ArrayList<>();
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
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
        CompoundNBT tag = stack.getOrCreateTag();
        if (tag.contains("story_combat_intensify")) {
            List<String> list = new ArrayList<>();
            ListNBT listNBT = (ListNBT) tag.get("story_combat_intensify").copy();
            for (int i = 0; i < listNBT.size(); i++) {
                list.add(Intensifys.getById(listNBT.getInt(i)).getShow());
            }
            return list;
        }
        return null;
    }

    public static void intensifyEdgeAtk(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_atk");
        stack.setTagInfo("story_combat_atk", IntNBT.valueOf(v + amount));
    }

    public static void intensifyEdgeDef(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_def");
        stack.setTagInfo("story_combat_def", IntNBT.valueOf(v + amount));
    }

    public static void intensifyEdgeAgl(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_agl");
        stack.setTagInfo("story_combat_agl", IntNBT.valueOf(v + amount));
    }

    public static void intensifyEdgeDur(ItemStack stack, int amount) {
        CompoundNBT tag = stack.getOrCreateTag();
        int v = tag.getInt("story_combat_dur_max");
        stack.setTagInfo("story_combat_dur_max", IntNBT.valueOf(v + amount));
    }
}
