package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.util.PlayerAttributesUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.io.*;

/**
 * 玩家信息
 */

public class StoryPlayerData implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private final String playerUUID;
    private int xpLast;
    private int xp;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public int getXp() {
        return xp;
    }

    public StoryPlayerData(String playerUUID, int xp) {
        this.playerUUID = playerUUID;
        this.xp = xp;
    }

    /**
     * 获取默认
     *
     * @param playerUUID 玩家UUID
     */
    private static StoryPlayerData defaultData(String playerUUID) {
        return new StoryPlayerData(playerUUID, 0);
    }

    /**
     * 获得xp
     *
     * @param amount 值
     */
    public void addXp(int amount) {
        this.xpLast = this.xp;
        this.xp += amount;
        //目前限制最高为19级
        int lv15Point = StoryPlayerDataManager.getLvNextXpPoint(19);
        if (this.xp >= lv15Point) {
            this.xp = lv15Point - 1;
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
     * @param playerDataPath MC玩家数据保存目录
     * @param playerUUID     玩家UUID
     * @return java.lang.String
     * @description 获取Story玩家数据保存目录
     * @date 2021/9/11 0011 13:11
     **/
    private static String file(String playerDataPath, String playerUUID) {
        return playerDataPath + "\\storyplayerdata\\" + playerUUID + ".dat";
    }

}
