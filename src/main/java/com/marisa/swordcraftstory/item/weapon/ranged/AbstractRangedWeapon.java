package com.marisa.swordcraftstory.item.weapon.ranged;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.skill.effect.helper.Effects;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.item.weapon.WeaponCommonFunction;
import com.marisa.swordcraftstory.skill.effect.helper.EffectHelper;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import com.marisa.swordcraftstory.util.damage.LivingHurtUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * 远程武器抽象类
 */

public abstract class AbstractRangedWeapon extends BowItem implements Weapon {

    /**
     * 武器基础暴击率
     */
    public static final int CRITICAL_NUM = 50;

    /**
     * 稀有度级别
     */
    private final int rank;

    /**
     * 攻击力
     */
    private final int atk;

    /**
     * 物理防御力
     */
    private final int def;

    /**
     * 敏捷值（每点增加0.1%移速）
     */
    private final int agl;

    public AbstractRangedWeapon(final int rank, final int atk, final int def, final int agl, final int dur) {
        super(new Item.Properties().maxDamage(dur).group(StoryGroup.COMBAT_GROUP));
        this.rank = rank;
        this.atk = atk;
        this.def = def;
        this.agl = agl;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (isBroken(itemstack)) {
            //损坏后将不能再使用正常攻击
            return ActionResult.resultFail(itemstack);
        }

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, true);
        if (ret != null) return ret;

        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            //声明新的箭矢
            ItemStack itemstack = new ItemStack(Items.ARROW);
            //拉弓时长不足取消发射
            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;
            //执行
            float f = getArrowVelocity(i);
            if (!((double) f < 0.1D)) {
                if (!worldIn.isRemote) {
                    ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                    AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                    abstractarrowentity = customArrow(abstractarrowentity);
                    abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                    //Story攻击力
                    int atk = getAtk(stack);
                    //是否暴击
                    if (LivingHurtUtils.isCri(playerentity)) {
                        atk *= 2;
                        abstractarrowentity.setIsCritical(true);
                        if (!playerentity.world.isRemote) {
                            playerentity.world.playSound(null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, playerentity.getSoundCategory(), 1.0F, 1.0F);
                        }
                    }
                    //设置伤害
                    abstractarrowentity.setDamage(atk);
                    //力量
                    int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                    if (j > 0) {
                        float fOffset = j > 4 ? 1.1F : 1.0F;
                        int f1 = 0;
                        for (int k = 0; k < j + 1; k++) {
                            f1 += k;
                        }
                        abstractarrowentity.setDamage((abstractarrowentity.getDamage() + f1) * fOffset);
                    }
                    //冲击
                    int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                    if (k > 0) {
                        abstractarrowentity.setKnockbackStrength(k);
                    }
                    //火矢
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                        abstractarrowentity.setFire(100);
                    }
                    //效果
                    Effects effect = EffectHelper.get(stack);
                    if (effect != null) {
                        effect.getEffect().arrowEntityAdd(abstractarrowentity);
                    }
                    //损伤玩家该武器
                    stack.damageItem(1, playerentity, (player) -> player.sendBreakAnimation(playerentity.getActiveHand()));
                    //箭矢无法被捡起
                    abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
                    //箭矢实体加入世界
                    worldIn.addEntity(abstractarrowentity);
                }
                //声音
                worldIn.playSound(null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            }
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return WeaponCommonFunction.damageItem(stack, amount, entity, random);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        WeaponInformationUtils.addInformation(this, stack, tooltip);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(final EquipmentSlotType equipmentSlot, final ItemStack stack) {
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            return WeaponCommonFunction.attributeModifierBuild(stack, super.getAttributeModifiers(EquipmentSlotType.MAINHAND));
        }
        return super.getAttributeModifiers(equipmentSlot, stack);
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public int getAtk(ItemStack stack) {
        if (isBroken(stack)) {
            return 1;
        }
        return this.atk + CombatPropertiesUtils.getAtk(stack);
    }

    @Override
    public int getDef(ItemStack stack) {
        if (isBroken(stack)) {
            return 0;
        }
        return this.def + CombatPropertiesUtils.getDef(stack);
    }

    @Override
    public int getCri(ItemStack stack) {
        if (isBroken(stack)) {
            return 0;
        }
        return CRITICAL_NUM + ((Math.min(CombatPropertiesUtils.getTec(stack), 250)) / 5);
    }

    @Override
    public int getAgl(ItemStack stack) {
        int agl = this.agl + CombatPropertiesUtils.getAgl(stack);
        if (isBroken(stack) && agl > 0) {
            return 0;
        }
        return agl;
    }

    @Override
    public int getDurAndDamage(ItemStack stack) {
        return getDur(stack) + getMaxDamage(stack) - getDamage(stack);
    }

    @Override
    public int getDur(ItemStack stack) {
        return CombatPropertiesUtils.getDur(stack);
    }

    @Override
    public int getDurMax(ItemStack stack) {
        return CombatPropertiesUtils.getDurMax(stack);
    }

    @Override
    public int getDurMaxAfterEffect(ItemStack stack) {
        return CombatPropertiesUtils.getDurMaxAfterEffect(stack);
    }

    @Override
    public int getTec(ItemStack stack) {
        return CombatPropertiesUtils.getTec(stack);
    }

    @Override
    public void incrTec(ItemStack stack) {
        if (!isBroken(stack)) {
            CombatPropertiesUtils.incrTec(stack);
        }
    }

    @Override
    public void setBroken(ItemStack stack) {
        stack.setTagInfo("story_combat_broken", IntNBT.valueOf(1));
    }

    @Override
    public boolean isBroken(ItemStack stack) {
        return stack.getTag() != null && stack.getTag().getBoolean("story_combat_broken");
    }
}
