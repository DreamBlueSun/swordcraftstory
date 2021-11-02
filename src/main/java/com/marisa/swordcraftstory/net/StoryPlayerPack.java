package com.marisa.swordcraftstory.net;

import com.marisa.swordcraftstory.gui.screen.StoryPlayerStatusScreen;
import com.marisa.swordcraftstory.gui.screen.WeaponSkillConfigScreen;
import com.marisa.swordcraftstory.gui.screen.WeaponSkillLearnScreen;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.skill.weapon.helper.WeaponSkills;
import com.marisa.swordcraftstory.util.PlayerAttributesUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * 玩家信息数据包
 */

public class StoryPlayerPack {

    private final String message;
    private final String playerUUID;
    private final int xpLast;
    private final int xp;
    private final int hpStory;
    private final int atkStory;
    private final int defStory;
    private final int[] configSkillIds;
    private final int[] haveSkillIds;
    private final int[] learnSkillIds;
    private final int[] learnSkillProgress;

    public StoryPlayerPack(String message) {
        this.message = message;
        this.playerUUID = "null";
        this.xpLast = 0;
        this.xp = 0;
        this.hpStory = 0;
        this.atkStory = 0;
        this.defStory = 0;
        this.configSkillIds = new int[]{0};
        this.haveSkillIds = new int[]{0};
        this.learnSkillIds = new int[]{0};
        this.learnSkillProgress = new int[]{0};
    }

    public StoryPlayerPack(String message, StoryPlayerData data) {
        this.message = message;
        this.playerUUID = data.getPlayerUUID();
        this.xpLast = data.getXpLast();
        this.xp = data.getXp();
        this.hpStory = data.getHpStory();
        this.atkStory = data.getAtkStory();
        this.defStory = data.getDefStory();
        this.configSkillIds = data.getListConfigWeaponSkillId().stream().map(Integer::parseInt).mapToInt(i -> i).toArray();
        this.haveSkillIds = data.getListHaveWeaponSkillId().stream().map(Integer::parseInt).mapToInt(i -> i).toArray();
        this.learnSkillIds = data.getMapLearnWeaponSkillId().keySet().stream().map(Integer::parseInt).mapToInt(i -> i).toArray();
        this.learnSkillProgress = data.getMapLearnWeaponSkillId().values().stream().mapToInt(i -> i).toArray();
    }

    public StoryPlayerPack(String message, List<Integer> configSkillIds) {
        this.message = message;
        this.playerUUID = "null";
        this.xpLast = 0;
        this.xp = 0;
        this.hpStory = 0;
        this.atkStory = 0;
        this.defStory = 0;
        this.configSkillIds = configSkillIds.stream().mapToInt(i -> i).toArray();
        this.haveSkillIds = new int[]{0};
        this.learnSkillIds = new int[]{0};
        this.learnSkillProgress = new int[]{0};
    }

    public StoryPlayerPack(PacketBuffer buffer) {
        this.message = buffer.readString(Short.MAX_VALUE);
        this.playerUUID = buffer.readString(Short.MAX_VALUE);
        this.xpLast = buffer.readInt();
        this.xp = buffer.readInt();
        this.hpStory = buffer.readInt();
        this.atkStory = buffer.readInt();
        this.defStory = buffer.readInt();
        this.configSkillIds = buffer.readVarIntArray();
        this.haveSkillIds = buffer.readVarIntArray();
        this.learnSkillIds = buffer.readVarIntArray();
        this.learnSkillProgress = buffer.readVarIntArray();
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeString(this.message);
        buffer.writeString(this.playerUUID);
        buffer.writeInt(this.xpLast);
        buffer.writeInt(this.xp);
        buffer.writeInt(this.hpStory);
        buffer.writeInt(this.atkStory);
        buffer.writeInt(this.defStory);
        buffer.writeVarIntArray(this.configSkillIds);
        buffer.writeVarIntArray(this.haveSkillIds);
        buffer.writeVarIntArray(this.learnSkillIds);
        buffer.writeVarIntArray(this.learnSkillProgress);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity sender = ctx.get().getSender();
        ctx.get().enqueueWork(() -> {
            if (sender == null) {
                switch (this.message) {
                    case "story.player.status.async":
                        StoryPlayerDataManager.put(new StoryPlayerData(this));
                        break;
                    case "story.player.status.client":
                        StoryPlayerDataManager.put(new StoryPlayerData(this));
                        StoryPlayerStatusScreen.open(Minecraft.getInstance().player);
                        break;
                    case "weapon.skill.config.client":
                        StoryPlayerDataManager.put(new StoryPlayerData(this));
                        WeaponSkillConfigScreen.open(Minecraft.getInstance().player);
                        break;
                    case "weapon.skill.learn.client":
                        StoryPlayerDataManager.put(new StoryPlayerData(this));
                        WeaponSkillLearnScreen.open(Minecraft.getInstance().player);
                        break;
                    default:
                        break;
                }
            } else {
                switch (this.message) {
                    case "story.player.status.open":
                        StoryPlayerPack pack1 = new StoryPlayerPack("story.player.status.client", StoryPlayerDataManager.get(sender.getCachedUniqueIdString()));
                        Networking.STORY_PLAYER_INFO.send(PacketDistributor.PLAYER.with(() -> sender), pack1);
                        break;
                    case "weapon.skill.config.open":
                        StoryPlayerPack pack2 = new StoryPlayerPack("weapon.skill.config.client", StoryPlayerDataManager.get(sender.getCachedUniqueIdString()));
                        Networking.STORY_PLAYER_INFO.send(PacketDistributor.PLAYER.with(() -> sender), pack2);
                        break;
                    case "weapon.skill.config.confirm":
                        StoryPlayerData data2 = StoryPlayerDataManager.get(sender.getCachedUniqueIdString());
                        List<String> collect = Arrays.stream(this.configSkillIds).mapToObj(String::valueOf).collect(Collectors.toList());
                        if (data2.getListHaveWeaponSkillId().containsAll(collect)) {
                            int count = 0;
                            for (String id : collect) {
                                count += WeaponSkills.getById(id).getSkill().getCost();
                            }
                            if (count <= StoryPlayerDataManager.getWeaponSkillAmount(StoryPlayerDataManager.getLv(data2.getXp()))) {
                                data2.configWeaponSkill(collect).save();
                                //修改影响到的属性
                                PlayerAttributesUtils.onConfigAttr(sender, false, false);
                            }
                        }
                        break;
                    case "weapon.skill.learn.open":
                        StoryPlayerPack pack3 = new StoryPlayerPack("weapon.skill.learn.client", StoryPlayerDataManager.get(sender.getCachedUniqueIdString()));
                        Networking.STORY_PLAYER_INFO.send(PacketDistributor.PLAYER.with(() -> sender), pack3);
                        break;
                    default:
                        break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

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

    public int[] getConfigSkillIds() {
        return configSkillIds;
    }

    public int[] getHaveSkillIds() {
        return haveSkillIds;
    }

    public int[] getLearnSkillIds() {
        return learnSkillIds;
    }

    public int[] getLearnSkillProgress() {
        return learnSkillProgress;
    }
}
