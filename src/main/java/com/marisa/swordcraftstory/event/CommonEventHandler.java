package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.event.util.LivingHurtUtils;
import com.marisa.swordcraftstory.event.util.PlayerAttackEntityUtils;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.QualityIdentificationPack;
import com.marisa.swordcraftstory.smith.util.Quality;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event) {
        //拦截伤害计算
        event.setCanceled(true);
        if (!event.getEntityLiving().level.isClientSide()) {
            LivingHurtUtils.hurt(event);
        }
    }

    @SubscribeEvent
    public void playerAttackEntity(AttackEntityEvent event) {
        //修改玩家近战攻击效果
        if (!event.getPlayer().level.isClientSide()) {
            event.setCanceled(true);
            PlayerAttackEntityUtils.attack(event.getPlayer(), event.getTarget());
        }
    }

    @SubscribeEvent
    public void arrowShoot(EntityJoinWorldEvent event) {
        //修改弓箭实体生成属性
        if (!event.getWorld().isClientSide() && event.getEntity() instanceof AbstractArrow arrow) {
            if (arrow.getOwner() instanceof ServerPlayer player && player.getMainHandItem().getItem() instanceof ProjectileWeaponItem) {
                float atk = (float) SmithNbtUtils.getAtk(player.getMainHandItem());
                //重新计算力量附魔：基础物理伤害+(0.5+0.5*lv)，再+(4%*lv)
                int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem());
                if (j > 0) {
                    atk += (0.5F * j + 0.5F);
                    atk = atk * (1.0F + j * 0.04F);
                }
                //暴击
                if (50 > new Random().nextInt(1000)) {
                    atk *= 1.25F;
                    arrow.setCritArrow(true);
                    player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                }
                arrow.setBaseDamage(atk);
            }
            //TODO mob射箭、药水箭、发射器
        }
    }

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof DiggerItem || itemStack.getItem() instanceof ProjectileWeaponItem) {
            List<Component> toolTip = event.getToolTip();
            int atk = 0;
            String atk_s = "1.0";
            if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof DiggerItem) {
                List<Component> remTip = new ArrayList<>();
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
                                        atk = Integer.parseInt(String.valueOf(c.getArgs()[0]));
                                    } else if ("attribute.name.generic.attack_speed".equals(key)) {
                                        atk_s = String.valueOf(c.getArgs()[0]);
                                    }
                                }
                            }
                        }
                    }
                }
                toolTip.removeAll(remTip);
            }
            //品质
            Quality quality = SmithNbtUtils.getQuality(itemStack);
            if (quality == Quality.UNKNOWN && event.getPlayer() != null) {
                //鉴定品质
                Inventory inventory = event.getPlayer().getInventory();
                int slot = -1;
                for (int i = 0; i < inventory.items.size(); ++i) {
                    if (!inventory.items.get(i).isEmpty() && inventory.items.get(i) == itemStack) {
                        slot = i;
                        break;
                    }
                }
                if (slot >= 0 && Minecraft.getInstance().player != null) {
                    Networking.QUALITY_IDENTIFICATION.sendToServer(new QualityIdentificationPack(itemStack));
                }
            }
            int index = 0;
            toolTip.add(++index, new TranslatableComponent(quality.getName()).withStyle(quality.getChatFormatting()));
            toolTip.add(++index, new TextComponent(""));
            //属性
            toolTip.add(++index, new TranslatableComponent("等阶").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent("1").withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("攻击").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(atk + SmithNbtUtils.getAtk(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof DiggerItem) {
                toolTip.add(++index, new TranslatableComponent("攻速").withStyle(ChatFormatting.YELLOW).append("     ")
                        .append(new TranslatableComponent(atk_s).withStyle(ChatFormatting.LIGHT_PURPLE)));
            }
            toolTip.add(++index, new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getAgl(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
//            toolTip.add(++index, new TranslatableComponent("暴击").withStyle(ChatFormatting.YELLOW).append("     ")
//                    .append(new TranslatableComponent("5.0%").withStyle(ChatFormatting.LIGHT_PURPLE)));
//            toolTip.add(++index, new TranslatableComponent("熟练").withStyle(ChatFormatting.YELLOW).append("     ")
//                    .append(new TranslatableComponent("0/255").withStyle(ChatFormatting.GREEN)));
//            toolTip.add(++index, new TextComponent(""));
        }
    }

    @SubscribeEvent
    public void itemAttributeModifier(ItemAttributeModifierEvent event) {
        if (event.getSlotType() != EquipmentSlot.MAINHAND) {
            return;
        }
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof DiggerItem || itemStack.getItem() instanceof ProjectileWeaponItem) {
            //攻击速度、移动速度
            double v = 0;
            int agl = SmithNbtUtils.getAgl(itemStack);
            if (agl != 0) {
                final double baseSpeed = 0.01D;
                final double offset = 50.0D;
                if (agl > offset) {
                    v = baseSpeed * offset + (baseSpeed * (agl - offset)) / 4;
                } else if (agl < -offset) {
                    v = baseSpeed * -offset + (baseSpeed * (agl + offset)) / 4;
                } else {
                    v = baseSpeed * agl;
                }
                event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "main hand modifier", v, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof DiggerItem) {
                double atkS = v + SmithNbtUtils.getAtkS(itemStack);
                if (atkS != 0) {
                    event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "main hand modifier", atkS, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            }
        }
    }


    @SubscribeEvent
    public void itemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        //拦截玩家制作物品
    }

    @SubscribeEvent
    public void itemPickupEvent(PlayerEvent.ItemPickupEvent event) {
        //拦截玩家拾取物品
    }

    @SubscribeEvent
    public void itemSmeltedEvent(PlayerEvent.ItemSmeltedEvent event) {
        //拦截玩家熔炼物品
    }

    @SubscribeEvent
    public void playerDestroyItem(PlayerDestroyItemEvent event) {
        //拦截玩家损坏物品
    }

}
