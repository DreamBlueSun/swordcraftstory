package com.marisa.swordcraftstory.util.obj;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.util.DamageCountUtils;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * 玩家面板属性
 */

public class PlayerPanelAttr {

    private final IFormattableTextComponent lv;
    private final IFormattableTextComponent exp;
    private final IFormattableTextComponent name;
    private final IFormattableTextComponent hp;
    private final IFormattableTextComponent atk;
    private final IFormattableTextComponent def;
    private final IFormattableTextComponent phy;
    private final IFormattableTextComponent agl;


    public PlayerPanelAttr(ClientPlayerEntity player) {
        //等级
        StoryPlayerData data = StoryPlayerDataManager.get(player.getCachedUniqueIdString());
        int XP = data.getXp();
        int LV = StoryPlayerDataManager.getLv(XP);
        this.lv = new TranslationTextComponent("Lv").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(LV)).mergeStyle(TextFormatting.GREEN));
        //经验
        String exp = XP + "/" + StoryPlayerDataManager.getLvNextXpPoint(LV);
        this.exp = new TranslationTextComponent("Exp").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(exp).mergeStyle(TextFormatting.GREEN));
        //名字
        this.name = new TranslationTextComponent(player.getDisplayName().getString()).mergeStyle(TextFormatting.BLUE);
        //HP
        String HP = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
        this.hp = new TranslationTextComponent("血量").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(HP).mergeStyle(TextFormatting.GREEN));
        //ATK
        Damage damage = new Damage();
        damage.setP(1.0F);
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!stack.isEmpty()) {
            if (stack.getItem() instanceof Weapon) {
                //story武器
                damage.setP(((Weapon) stack.getItem()).getAtk(stack));
            } else if (stack.getItem() instanceof TieredItem) {
                //非story武器
                Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
                double amount = ((AttributeModifier) attributeModifiers.get(Attributes.ATTACK_DAMAGE).toArray()[0]).getAmount();
                damage.setP((float) amount + 1.0F);
            }
        }
        int ATK = (int) DamageCountUtils.count(damage, new Defense());
        this.atk = new TranslationTextComponent("攻击").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(ATK)).mergeStyle(TextFormatting.GREEN));
        //DEF
        int DEF = player.getTotalArmorValue();
        this.def = new TranslationTextComponent("防御").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(DEF)).mergeStyle(TextFormatting.GREEN));
        //PHY
        int PHY = (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        this.phy = new TranslationTextComponent("魔抗").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(PHY)).mergeStyle(TextFormatting.GREEN));
        //AGL
        int AGL = 0;
        if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
            Weapon weapon = (Weapon) stack.getItem();
            AGL = weapon.getAgl(stack);
        }
        this.agl = new TranslationTextComponent("敏捷").mergeStyle(TextFormatting.LIGHT_PURPLE)
                .appendString(" ").appendSibling(new TranslationTextComponent(String.valueOf(AGL)).mergeStyle(TextFormatting.GREEN));
    }

    public IFormattableTextComponent getLv() {
        return lv;
    }

    public IFormattableTextComponent getExp() {
        return exp;
    }

    public IFormattableTextComponent getName() {
        return name;
    }

    public IFormattableTextComponent getHp() {
        return hp;
    }

    public IFormattableTextComponent getAtk() {
        return atk;
    }

    public IFormattableTextComponent getDef() {
        return def;
    }

    public IFormattableTextComponent getPhy() {
        return phy;
    }

    public IFormattableTextComponent getAgl() {
        return agl;
    }
}