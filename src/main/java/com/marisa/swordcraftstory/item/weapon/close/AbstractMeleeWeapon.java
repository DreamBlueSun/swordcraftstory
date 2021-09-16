package com.marisa.swordcraftstory.item.weapon.close;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.marisa.swordcraftstory.group.StoryGroup;
import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import com.marisa.swordcraftstory.util.StoryUUID;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * 近战武器抽象类
 */

public abstract class AbstractMeleeWeapon extends SwordItem implements Weapon {

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

    public AbstractMeleeWeapon(final int rank, final int atk, final int def, final int agl, final IItemTier tier) {
        super(tier, atk, 0.0F, new Item.Properties().group(StoryGroup.COMBAT_GROUP));
        this.rank = rank;
        this.atk = atk;
        this.def = def;
        this.agl = agl;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        //已损坏时
        if (isBroken(stack)) {
            //取消损伤
            return 0;
        }
        if (stack.isDamageable()) {
            //计算耐久附魔
            if (amount > 0) {
                int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
                int j = 0;
                for (int k = 0; i > 0 && k < amount; ++k) {
                    if (UnbreakingEnchantment.negateDamage(stack, i, entity.getRNG())) {
                        ++j;
                    }
                }
                amount -= j;
                if (amount <= 0) {
                    return 0;
                }
            }
            if (amount == 0) {
                return 0;
            }
            //优先消耗dur
            CompoundNBT tag = stack.getOrCreateTag();
            int dur = tag.getInt("story_combat_dur");
            if (dur > 0) {
                if (dur < amount) {
                    stack.setTagInfo("story_combat_dur", IntNBT.valueOf(0));
                    amount -= dur;
                } else {
                    dur -= amount;
                    stack.setTagInfo("story_combat_dur", IntNBT.valueOf(dur));
                    amount = 0;
                }
            }
            //后续消耗damage
            if (amount > 0) {
                int l = stack.getDamage() + amount;
                //物品要损坏时
                if (l >= stack.getMaxDamage()) {
                    //防止被损坏
                    l = 0;
                    //标记为已损坏
                    setBroken(stack);
                }
                stack.setDamage(l);
                //耐久条显示变化
                if (entity instanceof ServerPlayerEntity) {
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger((ServerPlayerEntity) entity, stack, l);
                }
            }
        }
        return 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CombatPropertiesUtils.addInformation(this, stack, tooltip);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(final EquipmentSlotType equipmentSlot, final ItemStack stack) {
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getAttributeModifiers(EquipmentSlotType.MAINHAND));
            builder.put(Attributes.ARMOR, new AttributeModifier(StoryUUID.ARMOR, "Armor armor modifier", getDef(stack), AttributeModifier.Operation.ADDITION));
            if (getAgl(stack) != 0) {
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(StoryUUID.MOVEMENT_SPEED, "Armor speed modifier", (Weapon.AGL_SPEED_BASE_NUM * getAgl(stack)), AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
            return builder.build();
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
            return Weapon.CRITICAL_BASE_NUM;
        }
        return Weapon.CRITICAL_BASE_NUM + (CombatPropertiesUtils.getTec(stack) / 5);
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
    public int getDur(ItemStack stack) {
        return CombatPropertiesUtils.getDur(stack);
    }

    @Override
    public int getDurMax(ItemStack stack) {
        return CombatPropertiesUtils.getDurMax(stack);
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
