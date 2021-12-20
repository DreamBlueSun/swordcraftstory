package com.marisa.swordcraftstory.item.weapon.close;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponCommonFunction;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * 抽象剑类武器
 */

public abstract class AbstractSwordWeapon extends SwordItem implements Weapon {

    public static final WeaponType TYPE = WeaponType.SWORD;

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

    /**
     * 正在进行特殊攻击
     */
    private boolean inSpecialAttack;

    /**
     * 武技ID
     */
    private String weaponSkillId;

    public AbstractSwordWeapon(final AbstractOre ore, final String weaponSkillId) {
        super(new Tier() {
            @Override
            public int getUses() {
                return ore.dur(TYPE);
            }

            @Override
            public float getSpeed() {
                return 10.0F;
            }

            @Override
            public float getAttackDamageBonus() {
                return -1.0F;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public int getEnchantmentValue() {
                return 26;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(ore.asSword());
            }
        }, ore.atk(TYPE), -2.4F, new Item.Properties().tab(StoryGroup.MAIN));
        this.rank = ore.rank();
        this.atk = ore.atk(TYPE);
        this.def = ore.def(TYPE);
        this.agl = ore.agl(TYPE);
        this.inSpecialAttack = false;
        this.weaponSkillId = weaponSkillId;
    }

    @Override
    public WeaponType type() {
        return TYPE;
    }

    @Override
    public ItemStack collapse(ItemStack stack) {
        ItemStack instance = ItemRegistry.SWORD_MOULD.get().getDefaultInstance();
        CombatPropertiesUtils.setAtk(instance, getAtk(stack) / 7);
        CombatPropertiesUtils.setDef(instance, getDef(stack) / 7);
        int agl = getAgl(stack);
        CombatPropertiesUtils.setAgl(instance, agl > 6 ? agl / 7 : 0);
        int dur = (getMaxDamage(stack) + getDurMax(stack)) / 7;
        CombatPropertiesUtils.setDurMax(instance, dur);
        CombatPropertiesUtils.setDur(instance, dur);
        CombatPropertiesUtils.copyEnchantmentTag(stack, instance);
        return instance;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return WeaponCommonFunction.damageItem(stack, amount, entity, random);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        WeaponInformationUtils.addInformation(this, stack, tooltip);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            return WeaponCommonFunction.attributeModifierBuild(stack, super.getDefaultAttributeModifiers(EquipmentSlot.MAINHAND));
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
        stack.getOrCreateTag().put("story_combat_broken", IntTag.valueOf(1));
    }

    @Override
    public boolean isBroken(ItemStack stack) {
        return stack.getTag() != null && stack.getTag().getBoolean("story_combat_broken");
    }

    @Override
    public boolean inSpecialAttackAndDoStop(ItemStack stack) {
        boolean in = this.inSpecialAttack;
        this.inSpecialAttack = false;
        return in;
    }

    @Override
    public void onSpecialAttack(ItemStack stack) {
        this.inSpecialAttack = true;
    }

    @Override
    public void incrWeaponSkill(String playerUUID) {
        StoryPlayerDataManager.get(playerUUID).toLearnWeaponSkill(this.weaponSkillId).save();
    }

    @Override
    public String getWeaponSkillId() {
        return this.weaponSkillId;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            return specialAttacks.getSpecialAttack().onItemRightClick(worldIn, playerIn, handIn);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            return specialAttacks.getSpecialAttack().getUseAction();
        }
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            return specialAttacks.getSpecialAttack().getUseDuration();
        }
        return 0;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            specialAttacks.getSpecialAttack().onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        }
    }
}
