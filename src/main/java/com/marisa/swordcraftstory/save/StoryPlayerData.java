package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.skill.weapon.helper.AbstractWeaponSkill;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import net.minecraft.world.entity.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 玩家信息
 */

public class StoryPlayerData implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private final String playerUUID;
    private int xpLast;
    private int xp;

    private int hpStory;
    private int atkStory;
    private int defStory;

    private List<String> listConfigWeaponSkillId;
    private List<String> listHaveWeaponSkillId;
    private Map<String, Integer> mapLearnWeaponSkillId;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public int getXpLast() {
        return xpLast;
    }

    public int getXp() {
        return xp;
    }

    public int getHpStory() {
        return hpStory;
    }

    public int getAtkStory() {
        return atkStory;
    }

    public int getDefStory() {
        return defStory;
    }

    public List<String> getListConfigWeaponSkillId() {
        return this.listConfigWeaponSkillId != null ? this.listConfigWeaponSkillId : new ArrayList<>();
    }

    public List<String> getListHaveWeaponSkillId() {
        return this.listHaveWeaponSkillId != null ? this.listHaveWeaponSkillId : new ArrayList<>();
    }

    public Map<String, Integer> getMapLearnWeaponSkillId() {
        return this.mapLearnWeaponSkillId != null ? this.mapLearnWeaponSkillId : new HashMap<>(1);
    }

    public StoryPlayerData(String playerUUID) {
        this.playerUUID = playerUUID;
        this.xpLast = 0;
        this.xp = 0;
        this.hpStory = 0;
        this.atkStory = 0;
        this.defStory = 0;
        this.listConfigWeaponSkillId = new ArrayList<>();
        this.listHaveWeaponSkillId = new ArrayList<>();
        this.mapLearnWeaponSkillId = new HashMap<>(1);
    }

//    public StoryPlayerData(StoryPlayerPack pack) {
//        this.playerUUID = pack.getPlayerUUID();
//        this.xpLast = pack.getXpLast();
//        this.xp = pack.getXp();
//        this.hpStory = pack.getHpStory();
//        this.atkStory = pack.getAtkStory();
//        this.defStory = pack.getDefStory();
//        this.listConfigWeaponSkillId = Arrays.stream(pack.getConfigSkillIds()).mapToObj(String::valueOf).collect(Collectors.toList());
//        this.listHaveWeaponSkillId = Arrays.stream(pack.getHaveSkillIds()).mapToObj(String::valueOf).collect(Collectors.toList());
//        List<String> learnIds = Arrays.stream(pack.getLearnSkillIds()).mapToObj(String::valueOf).collect(Collectors.toList());
//        List<String> progressIds = Arrays.stream(pack.getLearnSkillProgress()).mapToObj(String::valueOf).collect(Collectors.toList());
//        Map<String, Integer> map = new HashMap<>(1);
//        for (int i = 0; i < learnIds.size(); i++) {
//            map.put(learnIds.get(i), Integer.parseInt(progressIds.get(i)));
//        }
//        this.mapLearnWeaponSkillId = map;
//    }

    /**
     * 获取默认
     *
     * @param playerUUID 玩家UUID
     */
    private static StoryPlayerData defaultData(String playerUUID) {
        return new StoryPlayerData(playerUUID);
    }

    /**
     * 获得xp
     *
     * @param amount 值
     */
    public void addXp(int amount) {
        this.xpLast = this.xp;
        this.xp += amount;
        //限制最高等级
        int lvPoint = StoryPlayerDataManager.getLvNextXpPoint(Story.LV_MAX - 1);
        if (lvPoint != 0 && this.xp >= lvPoint) {
            this.xp = lvPoint - 1;
        }
    }

    /**
     * 检查升级
     */
    public void checkLvUp(Player player) {
        int lv = StoryPlayerDataManager.getLv(this.xp);
        int lvOffset = lv - StoryPlayerDataManager.getLv(this.xpLast);
        if (lvOffset > 0) {
//            PlayerAttributesUtils.onLevelUp(player, lv, true);
        }
    }

    public void setHpStory(int hpStory) {
        this.hpStory = hpStory;
    }

    public void setAtkStory(int atkStory) {
        this.atkStory = atkStory;
    }

    public void setDefStory(int defStory) {
        this.defStory = defStory;
    }

    public StoryPlayerData toLearnWeaponSkill(String weaponSkillId) {
        AbstractWeaponSkill skill = WeaponSkills.getById(weaponSkillId).getSkill();
        if (skill == null) {
            return this;
        }
        if (hasWeaponSkill(weaponSkillId)) {
            return this;
        }
        if (isLearningWeaponSkill(weaponSkillId)) {
            int nextValue = this.mapLearnWeaponSkillId.get(weaponSkillId) + 1;
            if (nextValue < skill.getLearnPoint()) {
                this.mapLearnWeaponSkillId.put(weaponSkillId, nextValue);
            } else {
                this.mapLearnWeaponSkillId.remove(weaponSkillId);
                toHaveWeaponSkill(weaponSkillId);
            }
        } else {
            if (this.mapLearnWeaponSkillId == null) {
                this.mapLearnWeaponSkillId = new HashMap<>(1);
            }
            this.mapLearnWeaponSkillId.put(weaponSkillId, 1);
        }
        return this;
    }

    private boolean isLearningWeaponSkill(String weaponSkillId) {
        return this.mapLearnWeaponSkillId != null && this.mapLearnWeaponSkillId.containsKey(weaponSkillId);
    }

    private void toHaveWeaponSkill(String weaponSkillId) {
        if (!hasWeaponSkill(weaponSkillId)) {
            if (this.listHaveWeaponSkillId == null) {
                this.listHaveWeaponSkillId = new ArrayList<>();
            }
            this.listHaveWeaponSkillId.add(weaponSkillId);
        }
    }

    private boolean hasWeaponSkill(String weaponSkillId) {
        return this.listHaveWeaponSkillId != null && this.listHaveWeaponSkillId.contains(weaponSkillId);
    }

    public StoryPlayerData configWeaponSkill(List<String> listWeaponSkillId) {
        this.listConfigWeaponSkillId = listWeaponSkillId;
        return this;
    }

    public void save() {
        StoryPlayerDataManager.put(this);
    }

    //序列化
    public static void serialize(String playerDataPath, String playerUUID) {
        ObjectOutputStream stream = null;
        try {
            String filePath = file(playerDataPath, playerUUID);
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            stream = new ObjectOutputStream(new FileOutputStream(filePath));
            StoryPlayerData data = StoryPlayerDataManager.get(playerUUID);
            if (data != null) {
                stream.writeObject(data);
            }
        } catch (IOException e) {
            Story.LOG.error("StoryPlayerData 序列化异常：" + playerUUID);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Story.LOG.error("StoryPlayerData 序列化流关闭异常：" + playerUUID);
                }
            }
        }
    }

    //反序列化
    public static StoryPlayerData deserialize(String playerDataPath, String playerUUID) {
        StoryPlayerData data = StoryPlayerDataManager.get(playerUUID);
        if (data != null) {
            return data;
        }
        try {
            String file = file(playerDataPath, playerUUID);
            if (new File(file).exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                data = (StoryPlayerData) ois.readObject();
            }
        } catch (Exception e) {
            Story.LOG.error("StoryPlayerData 反序列化异常：" + playerUUID, e);
        }
        if (data == null) {
            data = defaultData(playerUUID);
        }
        StoryPlayerDataManager.put(data);
        return data;
    }

    /**
     * 获取Story玩家数据保存目录
     *
     * @param playerDataPath MC玩家数据保存目录
     * @param playerUUID     玩家UUID
     */
    private static String file(String playerDataPath, String playerUUID) {
        return playerDataPath + "\\storyplayerdata\\" + playerUUID + ".dat";
    }

}
