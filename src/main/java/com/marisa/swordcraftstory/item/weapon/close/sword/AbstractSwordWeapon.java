package com.marisa.swordcraftstory.item.weapon.close.sword;

import com.marisa.swordcraftstory.entity.EntityTypeRegistry;
import com.marisa.swordcraftstory.entity.projectile.instance.AirCutterProjectileEntity;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.item.ore.AbstractOre;
import com.marisa.swordcraftstory.item.weapon.WeaponType;
import com.marisa.swordcraftstory.item.weapon.close.AbstractMeleeWeapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 抽象剑类武器
 */

public abstract class AbstractSwordWeapon extends AbstractMeleeWeapon {

    public static final WeaponType TYPE = WeaponType.SWORD;

    public AbstractSwordWeapon(final AbstractOre ore) {
        super(ore.rank(), ore.atk(TYPE), ore.def(TYPE), ore.agl(TYPE), new IItemTier() {
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
                return Ingredient.fromItems(ore.asSword());
            }
        });
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (isBroken(itemstack) || getDurAndDamage(itemstack) < 13) {
            //损坏后或耐久不足将不能再使用特殊攻击
            return ActionResult.resultFail(itemstack);
        }
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if ((entityLiving instanceof PlayerEntity)) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            AirCutterProjectileEntity airCutter = new AirCutterProjectileEntity(EntityTypeRegistry.AIR_CUTTER.get(), worldIn);
            airCutter.setDamage(getAtk(stack) * 1.6D);
            airCutter.setShooter(playerIn);
            airCutter.setNoGravity(true);
            airCutter.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
            airCutter.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double) 0.1F, playerIn.getPosZ());
            airCutter.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.6F, 0.0F);
            worldIn.addEntity(airCutter);
            stack.damageItem(13, entityLiving, (entity) -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
        }
    }
}
