package com.marisa.swordcraftstory.skill.attack.helper;

import com.marisa.swordcraftstory.item.intensify.helper.Intensifys;
import com.marisa.swordcraftstory.item.weapon.Combat;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
import com.marisa.swordcraftstory.skill.attack.sword.AirCutter;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 特殊攻击枚举
 */

public enum SpecialAttacks {

    UNKNOWN(0, 9, null, "未知", null, Intensifys.UNKNOWN.getId()),

    //剑
    AIR_CUTTER(1, 1, WeaponType.SWORD, "真空刃", new AirCutter(13), Intensifys.CUT_IRON_ORE.getId());

    private int id;
    /**
     * 优先级1-9
     */
    private int priority;
    private WeaponType weaponType;
    private String show;
    private SpecialAttack specialAttack;
    private List<Integer> intensifyIds;

    SpecialAttacks(int id, int priority, WeaponType weaponType, String show, SpecialAttack specialAttack, Integer... intensifyIds) {
        this.id = id;
        this.priority = priority;
        this.weaponType = weaponType;
        this.show = show;
        this.specialAttack = specialAttack;
        this.intensifyIds = Arrays.asList(intensifyIds);
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

    public SpecialAttack getSpecialAttack() {
        return specialAttack;
    }

    public List<Integer> getIntensifyIds() {
        return intensifyIds;
    }
    public static SpecialAttacks getById(int id) {
        for (SpecialAttacks value : SpecialAttacks.values()) {
            if (value.id == id) {
                return value;
            }
        }
        return UNKNOWN;
    }

    /**
     * 检查达成效果条件并返回达成的特殊攻击
     */
    public static SpecialAttacks checkReach(ItemStack stack) {
        List<SpecialAttacks> collect = new ArrayList<>();
        for (SpecialAttacks value : SpecialAttacks.values()) {
            if (value.getWeaponType() == ((Combat) stack.getItem()).type()) {
                List<Integer> ids = CombatPropertiesUtils.getIntensifyIds(stack);
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
            collect = collect.stream().sorted(Comparator.comparing(SpecialAttacks::getPriority)).collect(Collectors.toList());
            return collect.get(0);
        }
        return null;
    }
}
