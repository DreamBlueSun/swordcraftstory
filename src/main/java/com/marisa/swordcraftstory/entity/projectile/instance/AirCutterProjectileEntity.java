package com.marisa.swordcraftstory.entity.projectile.instance;


import com.marisa.swordcraftstory.item.weapon.Weapon;
import com.marisa.swordcraftstory.item.weapon.ranged.AbstractRangedWeapon;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


/**
 * 真空刃实体类
 */

public class AirCutterProjectileEntity extends AbstractArrowEntity {

    private int ticksInGround;

    public AirCutterProjectileEntity(EntityType<? extends AirCutterProjectileEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(damageIn);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticksInGround;
        if (this.ticksInGround >= 16) {
            this.remove();
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        this.ticksInGround = 0;
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        //设置伤害源
        Entity shooter = this.getShooter();
        DamageSource damagesource;
        if (shooter instanceof PlayerEntity) {
            damagesource = DamageSource.causePlayerDamage((PlayerEntity) shooter);
            ((LivingEntity) shooter).setLastAttackedEntity(entity);
        } else {
            damagesource = DamageSource.DRYOUT;
        }
        if (entity.attackEntityFrom(damagesource, (float) getDamage())) {
            //增加tec
            if (shooter instanceof PlayerEntity) {
                ItemStack stack = ((PlayerEntity) shooter).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
                if (!(stack.getItem() instanceof AbstractRangedWeapon)) {
                    ((Weapon) stack.getItem()).incrTec(stack);
                }
            }
            //执行伤害成功后执行特效
            this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        }
    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        BlockState blockstate = this.world.getBlockState(result.getPos());
        blockstate.onProjectileCollision(this.world, blockstate, result, this);
    }

    @Override
    protected ItemStack getArrowStack() {
        return Items.ARROW.getDefaultInstance();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
