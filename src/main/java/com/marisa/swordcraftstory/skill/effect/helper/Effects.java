package com.marisa.swordcraftstory.skill.effect.helper;

import com.marisa.swordcraftstory.item.intensify.helper.IntensifyHelper;
import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.weapon.helper.Combat;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import com.marisa.swordcraftstory.skill.effect.bow.Through;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 效果列表枚举
 */

public enum Effects {

    UNKNOWN(0, 9, null, "未知", null, Intensifys.UNKNOWN.getId()),

    //弓
    LEGEND_IRON_BUNCH(2001, 1, WeaponType.BOW, "贯穿", new Through(), Intensifys.LEGEND_IRON_BUNCH.getId());

    private int id;
    /**
     * 优先级1-9
     */
    private int priority;
    private WeaponType weaponType;
    private String show;
    private Effect effect;
    private List<Integer> intensifyIds;

    Effects(int id, int priority, WeaponType weaponType, String show, Effect effect, Integer... intensifyId) {
        this.id = id;
        this.priority = priority;
        this.weaponType = weaponType;
        this.show = show;
        this.effect = effect;
        this.intensifyIds = Arrays.asList(intensifyId);
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public String getShow() {
        return show;
    }

    public Effect getEffect() {
        return effect;
    }

    public List<Integer> getIntensifyIds() {
        return intensifyIds;
    }

    public static Effects getById(int id) {
        for (Effects value : Effects.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }

    /**
     * 检查达成效果条件并返回达成的效果
     */
    public static Effects checkReach(ItemStack stack) {
        List<Effects> collect = new ArrayList<>();
        for (Effects value : Effects.values()) {
            if (value.getWeaponType() == ((Combat) stack.getItem()).type()) {
                List<Integer> ids = IntensifyHelper.getIntensifyIds(stack);
                List<Integer> idsNeed = value.getIntensifyIds();
                boolean reach = false;
                out:
                if (ids != null && ids.size() >= idsNeed.size()) {
                    for (Integer need : idsNeed) {
                        if (!ids.contains(need)) {
                            break out;
                        }
                        ids.remove(need);
                    }
                    reach = true;
                }
                if (reach) {
                    collect.add(value);
                }
            }
        }
        if (collect.size() > 0) {
            collect = collect.stream().sorted(Comparator.comparing(Effects::getPriority)).collect(Collectors.toList());
            return collect.get(0);
        }
        return null;
    }

}
