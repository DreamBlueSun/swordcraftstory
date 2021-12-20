package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.event.util.PlayerAttackEntityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void playerAttackEntity(AttackEntityEvent event) {
        event.setCanceled(true);
        //修改玩家近战攻击效果
        if (!event.getPlayer().level.isClientSide()) {
            PlayerAttackEntityUtils.attack(event.getPlayer(), event.getTarget());
        }
    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event) {
        //拦截伤害计算
//        int armorValue = event.getEntityLiving().getArmorValue();
//        event.setCanceled(true);
    }

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof SwordItem) {
            List<Component> toolTip = event.getToolTip();
            List<Component> remTip = new ArrayList<>();
            String atk = "";
            String atk_s = "";
            for (Component i : toolTip) {
                if (i instanceof TextComponent t && t.getSiblings().size() == 0 && StringUtil.isNullOrEmpty(t.getText())) {
                    remTip.add(i);
                    continue;
                }
                if (i instanceof TranslatableComponent c) {
                    if (c.getKey().equals("item.modifiers.mainhand")) {
                        remTip.add(i);
                    }
                } else if (i instanceof TextComponent && i.getSiblings().size() > 0) {
                    for (Component j : i.getSiblings()) {
                        if (j instanceof TranslatableComponent c && c.getKey().equals("attribute.modifier.equals.0")) {
                            remTip.add(i);
                            if (c.getArgs().length > 1) {
                                String key = ((TranslatableComponent) c.getArgs()[1]).getKey();
                                if ("attribute.name.generic.attack_damage".equals(key)) {
                                    atk = String.valueOf(c.getArgs()[0]);
                                } else if ("attribute.name.generic.attack_speed".equals(key)) {
                                    atk_s = String.valueOf(c.getArgs()[0]);
                                }
                            }
                        }
                    }
                }
            }
            toolTip.removeAll(remTip);
            //属性
            toolTip.add(1, new TranslatableComponent("稀有度").withStyle(ChatFormatting.YELLOW)
                    .append("     ").append(new TranslatableComponent("1").withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(2, new TextComponent(""));
            toolTip.add(3, new TranslatableComponent("攻击速度").withStyle(ChatFormatting.YELLOW)
                    .append("   ").append(new TranslatableComponent(atk_s).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(4, new TranslatableComponent("攻击力").withStyle(ChatFormatting.YELLOW)
                    .append("     ").append(new TranslatableComponent(atk).withStyle(ChatFormatting.LIGHT_PURPLE)));
//            toolTip.add(5, new TranslatableComponent("敏捷值").withStyle(ChatFormatting.YELLOW)
//                    .append("     ").append(new TranslatableComponent("0").withStyle(ChatFormatting.LIGHT_PURPLE)));
//            toolTip.add(6, new TranslatableComponent("暴击率").withStyle(ChatFormatting.YELLOW)
//                    .append("     ").append(new TranslatableComponent("5.0%").withStyle(ChatFormatting.LIGHT_PURPLE)));
//            toolTip.add(7, new TranslatableComponent("熟练度").withStyle(ChatFormatting.YELLOW)
//                    .append("     ").append(new TranslatableComponent("0/255").withStyle(ChatFormatting.GREEN)));
        }
    }

    @SubscribeEvent
    public void itemAttributeModifier(ItemAttributeModifierEvent event) {
    }

}
