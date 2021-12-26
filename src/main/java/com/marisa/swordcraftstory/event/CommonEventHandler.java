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
import net.minecraft.stats.Stats;
import net.minecraft.util.StringUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.gameevent.GameEvent;
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
import java.util.UUID;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event) {
        //拦截伤害计算
        event.setCanceled(true);
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        LivingEntity livingEntity = event.getEntityLiving();
        if (livingEntity instanceof Player player) {
            amount = LivingHurtUtils.getDamageAfterArmorAbsorb(player, source, amount);
            amount = LivingHurtUtils.getDamageAfterMagicAbsorb(player, source, amount);
            float f2 = Math.max(amount - player.getAbsorptionAmount(), 0.0F);
            player.setAbsorptionAmount(player.getAbsorptionAmount() - (amount - f2));
            float f = amount - f2;
            if (f > 0.0F && f < 3.4028235E37F) {
                player.awardStat(Stats.DAMAGE_ABSORBED, Math.round(f * 10.0F));
            }
            if (f2 != 0.0F) {
                player.causeFoodExhaustion(source.getFoodExhaustion());
                float f1 = player.getHealth();
                player.getCombatTracker().recordDamage(source, f1, f2);
                player.setHealth(f1 - f2);
                if (f2 < 3.4028235E37F) {
                    player.awardStat(Stats.DAMAGE_TAKEN, Math.round(f2 * 10.0F));
                }
            }
        } else {
            amount = LivingHurtUtils.getDamageAfterArmorAbsorb(livingEntity, source, amount);
            amount = LivingHurtUtils.getDamageAfterMagicAbsorb(livingEntity, source, amount);
            float f2 = Math.max(amount - livingEntity.getAbsorptionAmount(), 0.0F);
            livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() - (amount - f2));
            float f = amount - f2;
            if (f > 0.0F && f < 3.4028235E37F && source.getEntity() instanceof ServerPlayer) {
                ((ServerPlayer) source.getEntity()).awardStat(Stats.CUSTOM.get(Stats.DAMAGE_DEALT_ABSORBED), Math.round(f * 10.0F));
            }
            if (f2 != 0.0F) {
                float f1 = livingEntity.getHealth();
                livingEntity.getCombatTracker().recordDamage(source, f1, f2);
                livingEntity.setHealth(f1 - f2);
                livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() - f2);
                livingEntity.gameEvent(GameEvent.ENTITY_DAMAGED, source.getEntity());
            }
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
                arrow.setBaseDamage(SmithNbtUtils.getAtk(player.getMainHandItem()));
            }
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
