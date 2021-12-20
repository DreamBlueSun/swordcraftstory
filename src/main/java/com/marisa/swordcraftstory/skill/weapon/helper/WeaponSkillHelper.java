package com.marisa.swordcraftstory.skill.weapon.helper;

import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 武技工具类
 */

public class WeaponSkillHelper {

    public static final List<String> LIST_HP_UP_ID;
    public static final List<String> LIST_ATK_UP_ID;
    public static final List<String> LIST_DEF_UP_ID;
    public static final List<String> LIST_CRI_UP_ID;
    public static final List<String> LIST_HEAL_USE_FAST_ID;

    static {
        //体力提升武技
        LIST_HP_UP_ID = new ArrayList<>();
        LIST_HP_UP_ID.add(WeaponSkills.HP_UP_SMALL.getId());
        LIST_HP_UP_ID.add(WeaponSkills.HP_UP_MEDIUM.getId());
        LIST_HP_UP_ID.add(WeaponSkills.HP_UP_BIG.getId());
        LIST_HP_UP_ID.add(WeaponSkills.HP_UP_SUPPER.getId());
        //攻击提升武技
        LIST_ATK_UP_ID = new ArrayList<>();
        LIST_ATK_UP_ID.add(WeaponSkills.ATK_UP_SMALL.getId());
        LIST_ATK_UP_ID.add(WeaponSkills.ATK_UP_MEDIUM.getId());
        LIST_ATK_UP_ID.add(WeaponSkills.ATK_UP_BIG.getId());
        LIST_ATK_UP_ID.add(WeaponSkills.ATK_UP_SUPPER.getId());
        //防御提升武技
        LIST_DEF_UP_ID = new ArrayList<>();
        LIST_DEF_UP_ID.add(WeaponSkills.DEF_UP_SMALL.getId());
        LIST_DEF_UP_ID.add(WeaponSkills.DEF_UP_MEDIUM.getId());
        LIST_DEF_UP_ID.add(WeaponSkills.DEF_UP_BIG.getId());
        //会心提升武技
        LIST_CRI_UP_ID = new ArrayList<>();
        LIST_CRI_UP_ID.add(WeaponSkills.CRI_UP_SMALL.getId());
        LIST_CRI_UP_ID.add(WeaponSkills.CRI_UP_MEDIUM.getId());
        LIST_CRI_UP_ID.add(WeaponSkills.CRI_UP_SUPPER.getId());
        //快吃提升武技
        LIST_HEAL_USE_FAST_ID = new ArrayList<>();
        LIST_HEAL_USE_FAST_ID.add(WeaponSkills.HEAL_USE_FAST_BLUE.getId());
        LIST_HEAL_USE_FAST_ID.add(WeaponSkills.HEAL_USE_FAST_YELLOW.getId());
        LIST_HEAL_USE_FAST_ID.add(WeaponSkills.HEAL_USE_FAST_RED.getId());
    }

    public static int fixedUp(Player player, List<String> list) {
        int v = 0;
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getStringUUID());
        List<String> hasId = storyPlayerData.getListConfigWeaponSkillId();
        if (hasId != null && hasId.size() > 0) {
            List<String> collect = hasId.stream().filter(list::contains).collect(Collectors.toList());
            for (String id : collect) {
                AbstractWeaponSkill skill = WeaponSkills.getById(id).getSkill();
                if (skill instanceof FixedValueWeaponSkill) {
                    v += ((FixedValueWeaponSkill) skill).getAmount();
                }
            }
        }
        return v;
    }

    /**
     * 获取玩家武技学习进度（已习得返回-1）
     */
    public static int learnProgress(Player player, String skillId) {
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(player.getStringUUID());
        if (storyPlayerData.getListHaveWeaponSkillId() != null && storyPlayerData.getListHaveWeaponSkillId().contains(skillId)) {
            return -1;
        }
        return storyPlayerData.getMapLearnWeaponSkillId().getOrDefault(skillId, 0);
    }

}
