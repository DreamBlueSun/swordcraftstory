//package com.marisa.swordcraftstory.util.obj;
//
//import com.google.common.collect.Multimap;
//import com.marisa.swordcraftstory.Story;
//import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
//import com.marisa.swordcraftstory.save.StoryPlayerData;
//import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
//import net.minecraft.client.entity.player.ClientPlayerEntity;
//import net.minecraft.entity.ai.attributes.Attribute;
//import net.minecraft.entity.ai.attributes.AttributeModifier;
//import net.minecraft.entity.ai.attributes.Attributes;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.text.IFormattableTextComponent;
//import net.minecraft.util.text.TextFormatting;
//import net.minecraft.util.text.TranslatableComponent;
//
//import java.util.Collection;
//
///**
// * 玩家面板属性
// */
//
//public class PlayerPanelAttr {
//
//    private final IFormattableTextComponent lv;
//    private final IFormattableTextComponent exp;
//    private final IFormattableTextComponent name;
//    private final IFormattableTextComponent hp;
//    private final IFormattableTextComponent atk;
//    private final IFormattableTextComponent def;
//    private final IFormattableTextComponent phy;
//    private final IFormattableTextComponent agl;
//
//
//    public PlayerPanelAttr(ClientPlayerEntity player) {
//        //等级
//        StoryPlayerData data = StoryPlayerDataManager.get(player.getStringUUID());
//        int XP = data.getXp();
//        int LV = StoryPlayerDataManager.getLv(XP);
//        this.lv = new TranslatableComponent("Lv").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(LV)).withStyle(ChatFormatting.GREEN));
//        //经验
//        String exp;
//        //限制最高等级
//        if (LV < Story.LV_MAX) {
//            exp = XP + "/" + StoryPlayerDataManager.getLvNextXpPoint(LV);
//        } else {
//            exp = XP + "/∞";
//        }
//        this.exp = new TranslatableComponent("Exp").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(exp).withStyle(ChatFormatting.GREEN));
//        //名字
//        this.name = new TranslatableComponent(player.getDisplayName().getString()).withStyle(ChatFormatting.BLUE);
//        //HP
//        String HP = (int) player.getHealth() + "/" + (int) player.getMaxHealth();
//        this.hp = new TranslatableComponent("血量").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(HP).withStyle(ChatFormatting.GREEN));
//        //ATK
//        int ATK = (int) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
//        ATK += StoryPlayerDataManager.get(player.getStringUUID()).getAtkStory();
//        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
//        if (!stack.isEmpty()) {
//            if (stack.getItem() instanceof Weapon) {
//                //story武器
//                ATK += ((Weapon) stack.getItem()).getAtk(stack);
//            } else {
//                //非story武器
//                Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
//                Collection<AttributeModifier> modifiers = attributeModifiers.get(Attributes.ATTACK_DAMAGE);
//                if (modifiers != null && modifiers.size() > 0) {
//                    ATK += (float) ((AttributeModifier) modifiers.toArray()[0]).getAmount();
//                }
//            }
//        }
//        this.atk = new TranslatableComponent("攻击").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(ATK)).withStyle(ChatFormatting.GREEN));
//        //DEF
//        int DEF = player.getTotalArmorValue();
//        this.def = new TranslatableComponent("防御").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(DEF)).withStyle(ChatFormatting.GREEN));
//        //PHY
//        int PHY = (int) player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
//        this.phy = new TranslatableComponent("魔抗").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(PHY)).withStyle(ChatFormatting.GREEN));
//        //AGL
//        int AGL = 0;
//        if (!stack.isEmpty() && stack.getItem() instanceof Weapon) {
//            Weapon weapon = (Weapon) stack.getItem();
//            AGL = weapon.getAgl(stack);
//        }
//        this.agl = new TranslatableComponent("敏捷").withStyle(ChatFormatting.LIGHT_PURPLE)
//                .append(" ").append(new TranslatableComponent(String.valueOf(AGL)).withStyle(ChatFormatting.GREEN));
//    }
//
//    public IFormattableTextComponent getLv() {
//        return lv;
//    }
//
//    public IFormattableTextComponent getExp() {
//        return exp;
//    }
//
//    public IFormattableTextComponent getName() {
//        return name;
//    }
//
//    public IFormattableTextComponent getHp() {
//        return hp;
//    }
//
//    public IFormattableTextComponent getAtk() {
//        return atk;
//    }
//
//    public IFormattableTextComponent getDef() {
//        return def;
//    }
//
//    public IFormattableTextComponent getPhy() {
//        return phy;
//    }
//
//    public IFormattableTextComponent getAgl() {
//        return agl;
//    }
//}
