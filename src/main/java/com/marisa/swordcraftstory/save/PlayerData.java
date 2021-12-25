package com.marisa.swordcraftstory.save;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.net.pack.PlayerDataPack;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;

import java.io.*;

/**
 * 玩家信息
 */

public class PlayerData implements Serializable {

    @Serial
    private static final long serialVersionUID = -5809782578272943999L;

    private final String playerUUID;
    private int xpLast;
    private int xp;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public int getXpLast() {
        return xpLast;
    }

    public int getXp() {
        return xp;
    }

    public PlayerData(String playerUUID) {
        this.playerUUID = playerUUID;
        this.xpLast = 0;
        this.xp = 0;
    }

    public PlayerData(PlayerDataPack pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.xpLast = pack.getXpLast();
        this.xp = pack.getXp();
    }

    /**
     * 获取默认
     *
     * @param playerUUID 玩家UUID
     */
    private static PlayerData defaultData(String playerUUID) {
        return new PlayerData(playerUUID);
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
        int lvPoint = PlayerDataManager.getLvNextXpPoint(Story.LV_MAX - 1);
        if (lvPoint != 0 && this.xp >= lvPoint) {
            this.xp = lvPoint - 1;
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
            PlayerData data = PlayerDataManager.get(playerUUID);
            if (data != null) {
                stream.writeObject(data);
            }
        } catch (IOException e) {
            Story.LOG.error("PlayerData 序列化异常：" + playerUUID);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Story.LOG.error("PlayerData 序列化流关闭异常：" + playerUUID);
                }
            }
        }
    }

    //反序列化
    public static PlayerData deserialize(String playerDataPath, String playerUUID) {
        PlayerData data = PlayerDataManager.get(playerUUID);
        if (data != null) {
            return data;
        }
        try {
            String file = file(playerDataPath, playerUUID);
            if (new File(file).exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                data = (PlayerData) ois.readObject();
            }
        } catch (Exception e) {
            Story.LOG.error("PlayerData 反序列化异常：" + playerUUID, e);
        }
        if (data == null) {
            data = defaultData(playerUUID);
        }
        PlayerDataManager.put(data);
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
