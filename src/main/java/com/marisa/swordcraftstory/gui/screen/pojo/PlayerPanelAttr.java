package com.marisa.swordcraftstory.gui.screen.pojo;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.save.PlayerData;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

/**
 * 玩家面板属性
 */

public class PlayerPanelAttr {

    private final Component lv;
    private final Component exp;
    private final Component name;
    private final Component hp;
    private final Component atk;
    private final Component agl;
    private final Component def;
    private final Component phy;


    public PlayerPanelAttr(LocalPlayer player) {
        //等级
        PlayerData data = PlayerDataManager.get(player.getStringUUID());
        int XP = data.getXp();
        int LV = PlayerDataManager.getLv(XP);
        this.lv = new TranslatableComponent("Lv").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(LV)).withStyle(ChatFormatting.GREEN));
        //经验
        String exp;
        //限制最高等级
        if (LV < Story.LV_MAX) {
            exp = XP + "/" + PlayerDataManager.getLvNextXpPoint(LV);
        } else {
            exp = XP + "/∞";
        }
        this.exp = new TranslatableComponent("Exp").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(exp).withStyle(ChatFormatting.GREEN));
        //名字
        this.name = new TranslatableComponent(player.getDisplayName().getString()).withStyle(ChatFormatting.BLUE);
        //HP
        String HP = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
        this.hp = new TranslatableComponent("血量").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(HP).withStyle(ChatFormatting.GREEN));
        //ATK
        int ATK = SmithNbtUtils.isMeleeWeapon(player.getMainHandItem().getItem()) ? (int) player.getAttributeValue(Attributes.ATTACK_DAMAGE) : 0;
        ATK += SmithNbtUtils.getAtk(player.getMainHandItem());
        if (player.getMainHandItem().getItem() instanceof SwordItem swordItem) {
            ATK += (int) swordItem.getDamage();
        } else if (player.getMainHandItem().getItem() instanceof DiggerItem diggerItem) {
            ATK += (int) diggerItem.getAttackDamage();
        }
        this.atk = new TranslatableComponent("攻击").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(ATK)).withStyle(ChatFormatting.GREEN));
        //DEF
        int DEF = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            DEF += SmithNbtUtils.getDef(stack);
        }
        DEF += player.getArmorValue();
        this.def = new TranslatableComponent("护甲").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(DEF)).withStyle(ChatFormatting.GREEN));
        //PHY
        int PHY = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            PHY += SmithNbtUtils.getPhy(stack);
        }
        PHY += (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        this.phy = new TranslatableComponent("韧性").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(PHY)).withStyle(ChatFormatting.GREEN));
        //AGL
        int AGL = SmithNbtUtils.getAgl(player.getMainHandItem());
        this.agl = new TranslatableComponent("敏捷").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(AGL)).withStyle(ChatFormatting.GREEN));
    }

    public Component getLv() {
        return lv;
    }

    public Component getExp() {
        return exp;
    }

    public Component getName() {
        return name;
    }

    public Component getHp() {
        return hp;
    }

    public Component getAtk() {
        return atk;
    }

    public Component getAgl() {
        return agl;
    }

    public Component getDef() {
        return def;
    }

    public Component getPhy() {
        return phy;
    }
}
