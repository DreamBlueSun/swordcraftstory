package com.marisa.swordcraftstory.gui.screen.pojo;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.block.craft.menu.ItemMakeMenu;
import com.marisa.swordcraftstory.save.PlayerData;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.util.StrengthenHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * 玩家面板属性
 */

public class PlayerPanelAttr {

    private final Component lv;
    private final Component exp;
    private final Component name;
    private final Component hp;
    //    private final Component atk;
//    private final Component agl;
//    private final Component def;
//    private final Component phy;
    private final Component makeRank;
    private final Component strengthenRank;


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
            exp = XP + "/" + XP;
        }
        this.exp = new TranslatableComponent("Exp").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(exp).withStyle(ChatFormatting.GREEN));
        //名字
        this.name = new TranslatableComponent(player.getDisplayName().getString()).withStyle(ChatFormatting.BLUE);
        //HP
        String HP = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
        this.hp = new TranslatableComponent("血量").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(HP).withStyle(ChatFormatting.GREEN));
        //制作等级
        int makeLv = Math.min((LV / ItemMakeMenu.RANK_LV_NEED_ONCE) + 1, 9);
        this.makeRank = new TranslatableComponent("制作").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(makeLv)).withStyle(ChatFormatting.GREEN));
        //强化等级
        int strengthenLv = StrengthenHelper.getStrengthenRank(player);
        this.strengthenRank = new TranslatableComponent("强化").withStyle(ChatFormatting.LIGHT_PURPLE)
                .append(" ").append(new TranslatableComponent(String.valueOf(strengthenLv)).withStyle(ChatFormatting.GREEN));
//        //ATK
//        int ATK = SmithHelper.getDamageAtk(player.getMainHandItem());
//        this.atk = new TranslatableComponent("攻击").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(ATK)).withStyle(ChatFormatting.GREEN));
//        //DEF
//        int DEF = 0;
//        for (ItemStack armor : player.getArmorSlots()) {
//            DEF += SmithHelper.getSmithDef(armor);
//        }
//        DEF += player.getArmorValue();
//        this.def = new TranslatableComponent("护甲").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(DEF)).withStyle(ChatFormatting.GREEN));
//        //PHY
//        int PHY = 0;
//        for (ItemStack stack : player.getArmorSlots()) {
//            PHY += SmithHelper.getSmithPhy(stack);
//        }
//        PHY += (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
//        this.phy = new TranslatableComponent("韧性").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(PHY)).withStyle(ChatFormatting.GREEN));
//        //AGL
//        int AGL = SmithHelper.getSmithAgl(player.getMainHandItem());
//        this.agl = new TranslatableComponent("敏捷").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(AGL)).withStyle(ChatFormatting.GREEN));
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

    public Component getMakeRank() {
        return makeRank;
    }

    public Component getStrengthenRank() {
        return strengthenRank;
    }

    //    public Component getAtk() {
//        return atk;
//    }
//
//    public Component getAgl() {
//        return agl;
//    }
//
//    public Component getDef() {
//        return def;
//    }
//
//    public Component getPhy() {
//        return phy;
//    }
}
