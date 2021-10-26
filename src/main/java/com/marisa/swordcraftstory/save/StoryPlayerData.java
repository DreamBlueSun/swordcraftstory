package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.PlayerAttributesUtils;
import net.minecraft.entity.player.PlayerEntity;

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

    private List<String> listHaveWeaponSkillId;
    private Map<String, Integer> mapLearnWeaponSkillId;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public int getXp() {
        return xp;
    }

    public StoryPlayerData(String playerUUID) {
        this.playerUUID = playerUUID;
        this.xp = 0;
        this.listHaveWeaponSkillId = new ArrayList<>();
        ;
        this.mapLearnWeaponSkillId = new HashMap<>(1);
    }

    public StoryPlayerData(String playerUUID, int xp) {
        this.playerUUID = playerUUID;
        this.xp = xp;
        this.listHaveWeaponSkillId = new ArrayList<>();
        this.mapLearnWeaponSkillId = new HashMap<>(1);
    }

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
        int lvPoint = StoryPlayerDataManager.getLvNextXpPoint(Story.LV_MAX);
        if (this.xp >= lvPoint) {
            this.xp = lvPoint - 1;
        }
    }

    /**
     * 检查升级
     */
    public void checkLvUp(PlayerEntity player) {
        int lv = StoryPlayerDataManager.getLv(this.xp);
        int lvOffset = lv - StoryPlayerDataManager.getLv(this.xpLast);
        if (lvOffset > 0) {
            PlayerAttributesUtils.onLevelUp(player, lv, true);
        }
    }

    public void toLearnWeaponSkill(String weaponSkillId) {
        if (hasWeaponSkill(weaponSkillId)) {
            return;
        }
        if (isLearningWeaponSkill(weaponSkillId)) {
            int nextValue = this.mapLearnWeaponSkillId.get(weaponSkillId) + 1;
            if (nextValue < 100) {
                this.mapLearnWeaponSkillId.put(weaponSkillId, nextValue);
            } else {
                this.mapLearnWeaponSkillId.remove(weaponSkillId);
                toHaveWeaponSkill(weaponSkillId);
            }
        } else {
            this.mapLearnWeaponSkillId.put(weaponSkillId, 1);
        }
    }

    private boolean isLearningWeaponSkill(String weaponSkillId) {
        return this.mapLearnWeaponSkillId.containsKey(weaponSkillId);
    }

    private void toHaveWeaponSkill(String weaponSkillId) {
        if (!hasWeaponSkill(weaponSkillId)) {
            this.listHaveWeaponSkillId.add(weaponSkillId);
        }
    }

    private boolean hasWeaponSkill(String weaponSkillId) {
        return this.listHaveWeaponSkillId.contains(weaponSkillId);
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
