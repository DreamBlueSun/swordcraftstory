package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.event.pojo.Damage;
import com.marisa.swordcraftstory.event.util.LivingHurtUtils;
import com.marisa.swordcraftstory.event.util.PlayerAttackEntityUtils;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.ItemUnbreakablePack;
import com.marisa.swordcraftstory.net.pack.QualityIdentificationPack;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.smith.util.Quality;
import com.marisa.swordcraftstory.smith.util.SmithNbtUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
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
        if (!event.getWorld().isClientSide()) {
            if (event.getEntity() instanceof SpectralArrow) {
                //光灵箭
                return;
            }
            if (event.getEntity() instanceof Arrow arrow) {
                if (arrow.getPickupItem().getItem() == Items.TIPPED_ARROW) {
                    //药水箭
                    return;
                }
                Entity owner = arrow.getOwner();
                if (owner == null) {
                    //发射器、投掷器、找不到发射者的箭矢：伤害为基础固定值
                    arrow.setBaseDamage(Damage.ARROW_BASE_DAMAGE);
                    return;
                }
                if (owner instanceof ServerPlayer player && SmithNbtUtils.isRangedWeapon(player.getMainHandItem().getItem())) {
                    ItemStack stack = player.getMainHandItem();
                    float atk = (float) SmithNbtUtils.getAtk(stack);
                    //重新计算力量附魔：基础物理伤害+(0.5+0.5*lv)，再+(4%*lv)，最高20%
                    int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                    if (j > 0) {
                        atk += (0.5D * j + 0.5D);
                        atk *= 1.0D + (Math.min(j, 5) * 0.04D);
                    }
                    //暴击
                    if (SmithNbtUtils.isCri(stack)) {
                        atk *= 1.25D;
                        arrow.setCritArrow(true);
                        player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                    }
                    arrow.setBaseDamage(atk);
                } else if (owner instanceof Mob mob) {
                    //mob射箭
                    int lv = MobAttributesUtils.getMobLv((ServerLevel) mob.level, mob.getStringUUID());
                    arrow.setBaseDamage(Damage.ARROW_BASE_DAMAGE * (lv + 1));
                } else {
                    arrow.setBaseDamage(Damage.ARROW_BASE_DAMAGE);
                }
            }
        }
    }

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (SmithNbtUtils.isWeapon(itemStack.getItem())) {
            List<Component> toolTip = event.getToolTip();
            int atk = 0;
            String atk_s = "1.0";
            List<Component> remTip = new ArrayList<>();
            for (Component i : toolTip) {
                if (i instanceof TextComponent t && t.getSiblings().size() == 0 && StringUtil.isNullOrEmpty(t.getText())) {
                    remTip.add(i);
                    continue;
                }
                if (i instanceof TranslatableComponent c && c.getKey().contains("item.modifiers.")) {
                    remTip.add(i);
                    continue;
                }
                if (SmithNbtUtils.isMeleeWeapon(itemStack.getItem())) {
                    if (i instanceof TextComponent && i.getSiblings().size() > 0) {
                        for (Component j : i.getSiblings()) {
                            if (j instanceof TranslatableComponent c && c.getKey().equals("attribute.modifier.equals.0")) {
                                remTip.add(i);
                                if (c.getArgs().length > 1) {
                                    String key = ((TranslatableComponent) c.getArgs()[1]).getKey();
                                    switch (key) {
                                        case "attribute.name.generic.attack_damage" -> atk = (int) Double.parseDouble(String.valueOf(c.getArgs()[0]));
                                        case "attribute.name.generic.attack_speed" -> atk_s = String.valueOf(c.getArgs()[0]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            toolTip.removeAll(remTip);
            //鉴定品质
            Quality quality = quality(event);
            int index = 0;
            toolTip.add(++index, new TranslatableComponent(quality.getName()).withStyle(quality.getChatFormatting()));
            //属性
            toolTip.add(++index, new TranslatableComponent("等阶").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getRank(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TextComponent(""));
            toolTip.add(++index, new TranslatableComponent("攻击").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(atk + SmithNbtUtils.getAtk(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            if (SmithNbtUtils.isMeleeWeapon(itemStack.getItem())) {
                toolTip.add(++index, new TranslatableComponent("攻速").withStyle(ChatFormatting.YELLOW).append("     ")
                        .append(new TranslatableComponent(atk_s).withStyle(ChatFormatting.LIGHT_PURPLE)));
            }
            toolTip.add(++index, new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getAgl(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            float critical = (float) SmithNbtUtils.getCri(itemStack) / 10;
            toolTip.add(++index, new TranslatableComponent("暴击").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(critical + "%").withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("熟练").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(SmithNbtUtils.getTec(itemStack) + " / 255").withStyle(ChatFormatting.GREEN)));
            toolTip.add(++index, new TextComponent(""));
        } else if (itemStack.getItem() instanceof ArmorItem) {
            List<Component> toolTip = event.getToolTip();
            int armor = 0;
            int toughness = 0;
            String resistance = "0";
            List<Component> remTip = new ArrayList<>();
            for (Component i : toolTip) {
                if (i instanceof TextComponent t && t.getSiblings().size() == 0 && StringUtil.isNullOrEmpty(t.getText())) {
                    remTip.add(i);
                    continue;
                }
                if (i instanceof TranslatableComponent c) {
                    if (c.getKey().contains("item.modifiers.")) {
                        remTip.add(i);
                    } else if (c.getKey().equals("attribute.modifier.plus.0")) {
                        remTip.add(i);
                        if (c.getArgs().length > 1) {
                            String key = ((TranslatableComponent) c.getArgs()[1]).getKey();
                            switch (key) {
                                case "attribute.name.generic.armor" -> armor = (int) Double.parseDouble(String.valueOf(c.getArgs()[0]));
                                case "attribute.name.generic.armor_toughness" -> toughness = (int) Double.parseDouble(String.valueOf(c.getArgs()[0]));
                                case "attribute.name.generic.knockback_resistance" -> resistance = String.valueOf(c.getArgs()[0]);
                            }
                        }
                    }
                }
            }
            toolTip.removeAll(remTip);
            //鉴定品质
            Quality quality = quality(event);
            int index = 0;
            toolTip.add(++index, new TranslatableComponent(quality.getName()).withStyle(quality.getChatFormatting()));
            //属性
            toolTip.add(++index, new TranslatableComponent("等阶").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithNbtUtils.getRank(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TextComponent(""));
            toolTip.add(++index, new TranslatableComponent("护甲").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(armor + SmithNbtUtils.getDef(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("韧性").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(toughness + SmithNbtUtils.getPhy(itemStack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("抵抗").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(resistance).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("熟练").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(SmithNbtUtils.getTec(itemStack) + " / 255").withStyle(ChatFormatting.GREEN)));
            toolTip.add(++index, new TextComponent(""));
        } else if (itemStack.getItem() instanceof ShieldItem && itemStack.isDamageableItem()) {
            Networking.ITEM_UNBREAKABLE.sendToServer(new ItemUnbreakablePack(itemStack));
        }
    }

    /**
     * 鉴定品质
     */
    private Quality quality(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        //品质
        Quality quality = SmithNbtUtils.QualityUtils.getQuality(itemStack);
        if (SmithNbtUtils.getRank(itemStack) > 0 && quality == Quality.UNKNOWN && event.getPlayer() != null) {
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
        return quality;
    }

    private static final UUID UUID_MOVEMENT_SPEED = UUID.randomUUID();
    private static final UUID UUID_ATTACK_SPEED = UUID.randomUUID();

    @SubscribeEvent
    public void itemAttributeModifier(ItemAttributeModifierEvent event) {
        if (event.getSlotType() != EquipmentSlot.MAINHAND) {
            return;
        }
        ItemStack itemStack = event.getItemStack();
        if (SmithNbtUtils.isWeapon(itemStack.getItem())) {
            //攻击速度、移动速度
            int agl = SmithNbtUtils.getAgl(itemStack);
            if (agl != 0) {
                final double baseSpeed = 0.01D;
                final double offset = 50.0D;
                double v;
                if (agl > offset) {
                    v = baseSpeed * offset + (baseSpeed * (agl - offset)) / 4;
                } else if (agl < -offset) {
                    v = baseSpeed * -offset + (baseSpeed * (agl + offset)) / 4;
                } else {
                    v = baseSpeed * agl;
                }
                event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID_MOVEMENT_SPEED, "main hand modifier", v, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            if (SmithNbtUtils.isMeleeWeapon(itemStack.getItem())) {
                double atkS = SmithNbtUtils.getAtkS(itemStack);
                if (atkS != 0) {
                    event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(UUID_ATTACK_SPEED, "main hand modifier", atkS, AttributeModifier.Operation.MULTIPLY_TOTAL));
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
