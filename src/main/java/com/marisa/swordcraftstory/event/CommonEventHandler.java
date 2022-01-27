package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.block.craft.ManualLotteryMachineBlock;
import com.marisa.swordcraftstory.event.pojo.Damage;
import com.marisa.swordcraftstory.event.util.LivingHurtUtils;
import com.marisa.swordcraftstory.event.util.PlayerAttackEntityUtils;
import com.marisa.swordcraftstory.item.reply.ReplyItem;
import com.marisa.swordcraftstory.net.Networking;
import com.marisa.swordcraftstory.net.pack.ItemUnbreakablePack;
import com.marisa.swordcraftstory.net.pack.QualityIdentificationPack;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.smith.EQuality;
import com.marisa.swordcraftstory.smith.EStrengthen;
import com.marisa.swordcraftstory.smith.util.*;
import com.marisa.swordcraftstory.util.StoryUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;
import java.util.stream.Collectors;

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
        if (event.getWorld().isClientSide()) return;
        //光灵箭
        if (event.getEntity() instanceof SpectralArrow) return;
        if (event.getEntity() instanceof Arrow arrow) {
            //药水箭
            if (arrow.getPickupItem().getItem() == Items.TIPPED_ARROW) return;
            //发射器、投掷器、找不到发射者的箭矢：伤害为基础固定值
            Entity owner = arrow.getOwner();
            if (owner == null) {
                arrow.setBaseDamage(Damage.ARROW_BASE_DAMAGE);
                return;
            }
            //玩家射箭
            if (owner instanceof ServerPlayer player && StoryUtils.isRangedWeapon(player.getMainHandItem().getItem())) {
                ItemStack stack = player.getMainHandItem();
                if (SmithHelper.isBroken(stack)) {
                    arrow.setBaseDamage(1.0D);
                    return;
                }
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
                //消耗耐久
                if (!player.gameMode.isCreative()) {
                    SmithHelper.minusDur(stack);
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
            if (SmithHelper.isBroken(stack)) {
                toolTip.add(++index, new TranslatableComponent("已损坏").withStyle(ChatFormatting.DARK_RED));
                toolTip.add(++index, new TextComponent(""));
            }
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
            toolTip.add(++index, new TranslatableComponent("耐久").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(SmithHelper.getDur(stack) + " / " + SmithHelper.getDurMax(stack)).withStyle(ChatFormatting.GREEN)));
            addTips2(index, toolTip, stack);
        } else if (StoryUtils.isArmor(stack.getItem())) {
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
        int rank = MakeHelper.getMakeRank(stack);
        if (rank > 0) {
            toolTip.add(++index, new TranslatableComponent("等阶").withStyle(ChatFormatting.YELLOW).append("     ")
                    .append(new TranslatableComponent(String.valueOf(rank)).withStyle(ChatFormatting.LIGHT_PURPLE)));
        }
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
                double abs = Math.abs(agl);
                double v = agl < 0 ? -(abs / (abs + 100.0F)) : abs / (abs + 100.0F);
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
    public void livingDrops(LivingDropsEvent event) {
        if (!(event.getEntityLiving() instanceof Mob)) return;
        Collection<ItemEntity> drops = event.getDrops();
        if (drops == null || drops.size() == 0) return;
        List<ItemEntity> collect = drops.stream().filter(i -> StoryUtils.isModItem(i.getItem().getItem())).collect(Collectors.toList());
        for (ItemEntity itemEntity : collect) {
            ItemStack stack = itemEntity.getItem();
            if (StoryUtils.isModItem(stack.getItem()) && QualityHelper.getQuality(stack) == EQuality.UNKNOWN) {
                QualityHelper.setQuality(stack, EQuality.randomOne(true));
            }
        }
    }

    @SubscribeEvent
    public void livingDestroyBlock(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            ItemStack stack = player.getMainHandItem();
            if (stack.isEmpty() || player.gameMode.isCreative()) return;
            if (!StoryUtils.isWeapon(stack.getItem()) || SmithHelper.isBroken(stack)) return;
            SmithHelper.minusDur(stack);
            if (new Random().nextInt(4) == 0) return;
            Block block = event.getState().getBlock();
            if (stack.getItem() instanceof AxeItem && isLogBlock(block)) {
                EdgeHelper.incrTec(stack);
            } else if (stack.getItem() instanceof PickaxeItem && block instanceof OreBlock) {
                EdgeHelper.incrTec(stack);
            }
        }
    }

    @SubscribeEvent
    public void playerInteractBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getPlayer().getMainHandItem();
        if (!stack.isEmpty() && StoryUtils.isWeapon(stack.getItem()) && SmithHelper.isBroken(stack)) {
            event.setCanceled(true);
        }
    }

    private boolean isLogBlock(Block block) {
        ResourceLocation registryName = block.getRegistryName();
        return registryName != null && registryName.getPath().contains("_log");
    }

    @SubscribeEvent
    public void playerInteract(PlayerInteractEvent.EntityInteract event) {
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

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (!event.world.isClientSide() && event.phase.ordinal() == 0) {
            ManualLotteryMachineBlock.dropResult(event);
        }
    }

}
