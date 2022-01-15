package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.event.pojo.Damage;
import com.marisa.swordcraftstory.event.util.LivingHurtUtils;
import com.marisa.swordcraftstory.event.util.PlayerAttackEntityUtils;
import com.marisa.swordcraftstory.item.reply.ReplyItem;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.ItemUnbreakablePack;
import com.marisa.swordcraftstory.net.pack.QualityIdentificationPack;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.smith.util.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
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
                if (owner instanceof ServerPlayer player && StoryUtils.isRangedWeapon(player.getMainHandItem().getItem())) {
                    ItemStack stack = player.getMainHandItem();
                    float atk = (float) SmithHelper.getDamageAtk(stack);
                    //重新计算力量附魔：基础物理伤害+(0.5+0.5*lv)，再+(4%*lv)，最高20%
                    int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                    if (j > 0) {
                        atk += (0.5D * j + 0.5D);
                        atk *= 1.0D + (Math.min(j, 5) * 0.04D);
                    }
                    //暴击
                    if (SmithHelper.isCri(stack)) {
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
        ItemStack stack = event.getItemStack();
        if (StoryUtils.isWeapon(stack.getItem())) {
            List<Component> toolTip = event.getToolTip();
            String atk_s = "4.0";
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
                if (StoryUtils.isMeleeWeapon(stack.getItem())) {
                    if (i instanceof TextComponent && i.getSiblings().size() > 0) {
                        for (Component j : i.getSiblings()) {
                            if (j instanceof TranslatableComponent c && c.getKey().equals("attribute.modifier.equals.0")) {
                                remTip.add(i);
                                if (c.getArgs().length > 1) {
                                    String key = ((TranslatableComponent) c.getArgs()[1]).getKey();
                                    if ("attribute.name.generic.attack_speed".equals(key)) {
                                        atk_s = String.valueOf(c.getArgs()[0]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            toolTip.removeAll(remTip);
            int index = 0;
            index = addTips1(index, toolTip, stack, event.getPlayer());
            toolTip.add(++index, new TranslatableComponent("攻击").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithHelper.getDamageAtk(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            if (StoryUtils.isMeleeWeapon(stack.getItem())) {
                toolTip.add(++index, new TranslatableComponent("攻速").withStyle(ChatFormatting.YELLOW).append("     ")
                        .append(new TranslatableComponent(atk_s).withStyle(ChatFormatting.LIGHT_PURPLE)));
            }
            toolTip.add(++index, new TranslatableComponent("敏捷").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(SmithHelper.getSmithAgl(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            float critical = (float) SmithHelper.getCri(stack) / 10;
            toolTip.add(++index, new TranslatableComponent("暴击").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(critical + "%").withStyle(ChatFormatting.LIGHT_PURPLE)));
            addTips2(index, toolTip, stack);
        } else if (stack.getItem() instanceof ArmorItem) {
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
                        if (c.getArgs().length > 1) {
                            String key = ((TranslatableComponent) c.getArgs()[1]).getKey();
                            switch (key) {
                                case "attribute.name.generic.armor" -> {
                                    armor = (int) Double.parseDouble(String.valueOf(c.getArgs()[0]));
                                    remTip.add(i);
                                }
                                case "attribute.name.generic.armor_toughness" -> {
                                    toughness = (int) Double.parseDouble(String.valueOf(c.getArgs()[0]));
                                    remTip.add(i);
                                }
                                case "attribute.name.generic.knockback_resistance" -> {
                                    resistance = String.valueOf(c.getArgs()[0]);
                                    remTip.add(i);
                                }
                            }
                        }
                    }
                }
            }
            toolTip.removeAll(remTip);
            int index = 0;
            index = addTips1(index, toolTip, stack, event.getPlayer());
            toolTip.add(++index, new TranslatableComponent("护甲").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(armor + SmithHelper.getSmithDef(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("韧性").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(toughness + SmithHelper.getSmithPhy(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
            toolTip.add(++index, new TranslatableComponent("抵抗").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(resistance).withStyle(ChatFormatting.LIGHT_PURPLE)));
            addTips2(index, toolTip, stack);
        } else if (stack.getItem() instanceof ShieldItem && stack.isDamageableItem()) {
            Networking.ITEM_UNBREAKABLE.sendToServer(new ItemUnbreakablePack(stack));
        }
    }

    private int addTips1(int index, List<Component> toolTip, ItemStack stack, Player player) {
        //品质
        EQuality quality = quality(stack, player);
        toolTip.add(++index, new TranslatableComponent(quality.getName()).withStyle(quality.getChatFormatting()));
        //等阶
        toolTip.add(++index, new TranslatableComponent("等阶").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(String.valueOf(RankHelper.getRank(stack))).withStyle(ChatFormatting.LIGHT_PURPLE)));
        toolTip.add(++index, new TextComponent(""));
        return index;
    }

    private void addTips2(int index, List<Component> toolTip, ItemStack stack) {
        toolTip.add(++index, new TranslatableComponent("熟练").withStyle(ChatFormatting.YELLOW).append("     ")
                .append(new TranslatableComponent(EdgeHelper.getTec(stack) + " / 255").withStyle(ChatFormatting.GREEN)));
        toolTip.add(++index, new TextComponent(""));
        //强化字段显示
        int[] strengthenIds = StrengthenHelper.getStrengthenIds(stack);
        if (strengthenIds != null) {
            MutableComponent component = new TranslatableComponent("强化").withStyle(ChatFormatting.AQUA).append("     ");
            for (int id : strengthenIds) {
                component.append(new TranslatableComponent("[").withStyle(ChatFormatting.RED))
                        .append(new TranslatableComponent(EStrengthen.getById(id).getName()).withStyle(ChatFormatting.AQUA))
                        .append(new TranslatableComponent("]").withStyle(ChatFormatting.RED));
            }
            toolTip.add(++index, component);
            toolTip.add(++index, new TextComponent(""));
        }
    }

    /**
     * 鉴定品质
     */
    private EQuality quality(ItemStack stack, Player player) {
        //品质
        EQuality quality = QualityHelper.getQuality(stack);
        if (quality == EQuality.UNKNOWN && player != null) {
            //鉴定品质
            Inventory inventory = player.getInventory();
            int slot = -1;
            for (int i = 0; i < inventory.items.size(); ++i) {
                if (!inventory.items.get(i).isEmpty() && inventory.items.get(i) == stack) {
                    slot = i;
                    break;
                }
            }
            if (slot >= 0 && Minecraft.getInstance().player != null) {
                Networking.QUALITY_IDENTIFICATION.sendToServer(new QualityIdentificationPack(stack));
            }
        }
        return quality;
    }

    private static final UUID UUID_MOVEMENT_SPEED = UUID.randomUUID();

    @SubscribeEvent
    public void itemAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        //判断主手或盔甲槽位
        EquipmentSlot slot;
        if (stack.getItem() instanceof ArmorItem armor) {
            slot = armor.getSlot();
        } else {
            slot = EquipmentSlot.MAINHAND;
        }
        if (slot != event.getSlotType()) {
            return;
        }
        //鉴定属性
        EQuality quality = QualityHelper.getQuality(stack);
        if (quality != EQuality.UNKNOWN) {
            quality.getAttr(stack.getItem()).addAttributeModifier(event);
        }
        //武器敏捷属性
        if (StoryUtils.isWeapon(stack.getItem())) {
            int agl = SmithHelper.getSmithAgl(stack);
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
        }
    }

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        //拦截实体tick
        if (event.getEntity() instanceof ServerPlayer player) {
            int value = (int) player.getAttributeValue(Attributes.MAX_HEALTH);
            if (player.getHealth() > value) {
                player.setHealth(value);
            }
        }
    }

    @SubscribeEvent
    public void itemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        //拦截玩家制作物品进行鉴定品质
        ItemStack stack = event.getCrafting();
        if (StoryUtils.isModItem(stack.getItem()) && QualityHelper.getQuality(stack) == EQuality.UNKNOWN) {
            QualityHelper.setQuality(stack, EQuality.randomOne(false));
        } else if (stack.getItem() instanceof ShieldItem && stack.isDamageableItem()) {
            stack.getOrCreateTag().putInt("HideFlags", 4);
            stack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
    }

    @SubscribeEvent
    public void playerDestroyItem(PlayerInteractEvent.EntityInteract event) {
        //拦截玩家与实体交互实现给生物使用回复道具
        if (event.getPlayer() instanceof ServerPlayer player && event.getTarget() instanceof LivingEntity living && living.getHealth() < living.getMaxHealth()) {
            ItemStack stack = event.getItemStack();
            if (stack.getItem() instanceof ReplyItem reply && (living instanceof AgeableMob || living instanceof OwnableEntity)) {
                ItemStack copy = stack.copy();
                copy.setCount(1);
                reply.finishUsingItem(copy, player.level, living);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                event.setCanceled(true);
            }
        }
    }

}
