package com.marisa.swordcraftstory.item.weapon.close;

import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.helper.Weapon;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponCommonFunction;
import com.marisa.swordcraftstory.item.weapon.helper.WeaponType;
import com.marisa.swordcraftstory.save.StoryPlayerData;
import com.marisa.swordcraftstory.save.StoryPlayerDataManager;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttackHelper;
import com.marisa.swordcraftstory.skill.attack.helper.SpecialAttacks;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.WeaponInformationUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * 斧头抽象类
 */

public abstract class AbstractAxeWeapon extends AxeItem implements Weapon {

    public static final WeaponType TYPE = WeaponType.AXE;

    /**
     * 武器基础暴击率
     */
    public static final int CRITICAL_NUM = 150;

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

    public AbstractAxeWeapon(final AbstractOre ore, final String weaponSkillId) {
        super(new IItemTier() {
            @Override
            public int getMaxUses() {
                return ore.dur(TYPE);
            }

            @Override
            public float getEfficiency() {
                return 10.0F;
            }

            @Override
            public float getAttackDamage() {
                return -1.0F;
            }

            @Override
            public int getHarvestLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 26;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return Ingredient.fromItems(ore.asAxe());
            }
        }, ore.atk(TYPE), -3.0F, new Item.Properties().group(StoryGroup.COMBAT_GROUP));
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
        ItemStack instance = ItemRegistry.AXE_MOULD.get().getDefaultInstance();
        CombatPropertiesUtils.setAtk(instance, getAtk(stack) / 6);
        CombatPropertiesUtils.setDef(instance, getDef(stack) / 6);
        int agl = getAgl(stack);
        CombatPropertiesUtils.setAgl(instance, agl > 5 ? agl / 6 : 0);
        int dur = (getMaxDamage(stack) + getDurMax(stack)) / 6;
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
        StoryPlayerData storyPlayerData = StoryPlayerDataManager.get(playerUUID);
        storyPlayerData.toLearnWeaponSkill(this.weaponSkillId);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            return specialAttacks.getSpecialAttack().onItemRightClick(worldIn, playerIn, handIn);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            return specialAttacks.getSpecialAttack().getUseAction();
        }
        return UseAction.NONE;
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
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        SpecialAttacks specialAttacks = SpecialAttackHelper.get(stack);
        if (specialAttacks != null) {
            specialAttacks.getSpecialAttack().onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        }

    }
}
