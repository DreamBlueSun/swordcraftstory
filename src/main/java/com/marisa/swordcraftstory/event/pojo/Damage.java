package com.marisa.swordcraftstory.event.pojo;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import com.marisa.swordcraftstory.smith.util.EnchantHelper;
import com.marisa.swordcraftstory.smith.util.SmithHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 * 伤害
 */

public class Damage {

    private final float amount;
    private final DamageSource source;
    private final LivingEntity target;
    private final float lvWeight;

    /**
     * 普通伤害
     */
    private float p;

    /**
     * 魔法伤害
     */
    private float m;

    /**
     * 真实伤害
     */
    private float r;

    public Damage(float amount, DamageSource source, LivingEntity target) {
        this.amount = amount;
        this.source = source;
        this.target = target;
        if (target instanceof Player player) {
            this.lvWeight = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
        } else if (target instanceof Mob) {
            this.lvWeight = MobAttributesUtils.getMobLv((ServerLevel) target.level, target.getStringUUID()) * 0.3F;
        } else {
            this.lvWeight = 0.0F;
        }
        this.p = 0;
        this.m = 0;
        this.r = 0;
        init();
    }

    public final static double ARROW_BASE_DAMAGE = 7.0D;
//    public final static double TRIDENT_BASE_DAMAGE = 9.0D;

    /**
     * 伤害属性初始化
     */
    private void init() {
        switch (this.source.getMsgId()) {
            case "player":
                this.p = this.amount;
                break;
            case "arrow":
                try {
                    if (this.amount > 0.0F) {
                        AbstractArrow abstractArrow = (AbstractArrow) this.source.getDirectEntity();
                        if (abstractArrow instanceof SpectralArrow) {
                            //光灵箭：伤害固定值0
                            abstractArrow.setBaseDamage(0.0D);
                            return;
                        } else if (abstractArrow instanceof Arrow arrow && arrow.getPickupItem().getItem() == Items.TIPPED_ARROW) {
                            //药水箭：伤害固定值0
                            abstractArrow.setBaseDamage(0.0D);
                            return;
                        }
                        this.p = abstractArrow != null ? (float) abstractArrow.getBaseDamage() : (float) ARROW_BASE_DAMAGE;
                    } else {
                        this.p = amount;
                    }
                } catch (Exception e) {
                    Story.LOG.error("ARROW伤害异常：" + this.source, e);
                }
                break;
            case "trident":
                try {
                    this.p = amount;
                    if (this.amount > 0.0F && this.source.getDirectEntity() instanceof ThrownTrident trident) {
                        ItemStack stack = trident.tridentItem;
                        double baseDamage = SmithHelper.getItemAtk(stack.getItem());
                        if (this.source.getEntity() instanceof ServerPlayer player) {
                            //玩家投掷
                            if (SmithHelper.isBroken(stack)) {
                                this.p = 1.0F;
                                return;
                            }
                            float f = (float) SmithHelper.getDamageAtk(stack);
                            //暴击
                            if (SmithHelper.isCri(stack)) {
                                f *= 1.25F;
                                player.level.playSound(null, this.target.getX(), this.target.getY(), this.target.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                                player.crit(target);
                            }
                            //附魔伤害
                            MobType mobType = this.target.getMobType();
                            float f1 = EnchantmentHelper.getDamageBonus(stack, mobType);
                            f += f1;
                            //额外计算伤害附魔：额外再+(4%*lv)，最高20%
                            if (f1 > 0) {
                                f *= (1.0F + (Mth.clamp(EnchantHelper.getItemEnchantmentLevelByMobType(mobType, stack), 0, 5) * 0.04F));
                            }
                            this.p = f;
                        } else if (this.source.getEntity() instanceof Mob mob) {
                            //mob投掷
                            int lv = MobAttributesUtils.getMobLv((ServerLevel) mob.level, mob.getStringUUID());
                            this.p = (float) (baseDamage * (lv + 1));
                        } else {
                            this.p = (float) baseDamage;
                        }
                    }
                } catch (Exception e) {
                    Story.LOG.error("TRIDENT伤害异常：" + this.source, e);
                }
                break;
            case "mob":
                try {
                    if (this.amount > 0.0F && this.source.getEntity() != null && this.source.getEntity() instanceof Mob mob) {
                        float baseDamage = 1.0F;
                        try {
                            baseDamage = (float) Math.max(mob.getAttributeValue(Attributes.ATTACK_DAMAGE), 1.0D);
                        } catch (Exception e) {
                            Story.LOG.error("MOB伤害-Attributes.ATTACK_DAMAGE-异常：" + this.source, e);
                        }
                        switch (mob.level.getDifficulty()) {
                            case EASY -> baseDamage *= 0.7F;
                            case HARD -> baseDamage *= 1.5F;
                        }
                        int lv = MobAttributesUtils.getMobLv((ServerLevel) mob.level, mob.getStringUUID());
                        this.p = Mth.clamp(baseDamage, 1.0F, 16.0F) * (1 + lv * 0.8F);
                    } else {
                        this.p = this.amount;
                    }
                } catch (Exception e) {
                    Story.LOG.error("MOB伤害异常：" + this.source, e);
                }
                break;
            case "inFire":
            case "onFire":
                this.m = fixedDamageUp(4.5F, 0.5F);
                break;
            case "fireball":
                this.m = fixedDamageUp(20.0F, 2.0F);
                break;
            case "lightningBolt":
                this.p = 64.0F;
                break;
            case "magic":
                //魔法
            case "indirectMagic":
                //间接有来源魔法
            case "dragonBreath":
                //无来源魔法
                float clamp = Mth.clamp(this.amount, 2.0F, 12.0F);
                this.m = fixedDamageUp(clamp * 2.5F, Math.max(clamp * 0.3F, 1.0F));
                break;
            case "wither":
                //凋零
                this.r = fixedDamageUp(6.0F, 1.0F);
                break;
            case "lava":
                //岩浆
                this.m = fixedDamageUp(6.0F, 1.0F);
                this.r = fixedDamageUp(6.5F, 0.5F);
                break;
            case "explosion.player":
                //爆炸
                this.p = fixedDamageUp(20.0F, 2.0F);
                this.m = fixedDamageUp(16.0F, 1.0F);
                this.r = fixedDamageUp(40.0F, 4.0F);
                break;
            case "outOfWorld":
                this.r = 9999.0F;
                break;
            case "fall":
                //摔落
            case "flyIntoWall":
                //飞翔撞墙
            case "generic":
                //属性？？？
            case "anvil":
                //铁砧
            case "fallingBlock":
                //方块掉落
                this.r = this.amount;
                break;
            case "starve":
                //饥饿
            case "drown":
                //溺水
            case "dryout":
                //干涸
            case "inWall":
                //墙内
            case "cramming":
                //拥挤
                this.r = fixedDamageUp(12.0F, 2.0F);
                break;
            case "sweetBerryBush":
                //浆果
                this.p = 2.0F;
                break;
            case "explosion":
                //特殊爆炸
            case "hotFloor":
                //熔岩块
            case "cactus":
                //仙人掌
            default:
                this.r = 2.0F;
                break;
        }
    }

    /**
     * 固定伤害等级偏移
     */
    private float fixedDamageUp(float fixed, float lvOffset) {
        return fixed + this.lvWeight * lvOffset;
    }

    public float getP() {
        return p;
    }

    public float getM() {
        return m;
    }

    public float getR() {
        return r;
    }

    public Damage addP(float amount) {
        this.p = Math.max(this.p + amount, 0.0F);
        return this;
    }

    public Damage addM(float amount) {
        this.m = Math.max(this.m + amount, 0.0F);
        return this;
    }

    public Damage addR(float amount) {
        this.r = Math.max(this.r + amount, 0.0F);
        return this;
    }

    private final static float APPLY_ARMOR_FIXED = 200.0F;

    public Damage applyDef(float def) {
        this.p *= 1.0F - def / (APPLY_ARMOR_FIXED + def);
        return this;
    }

    public Damage applyPhy(float phy) {
        this.m *= 1.0F - phy / (APPLY_ARMOR_FIXED + phy);
        return this;
    }

    public Damage applyRea(float rea) {
        this.r = Math.max(this.r - rea, 0.0F);
        return this;
    }

    public float totalAll() {
        return Math.max(this.p + this.m + this.r, 0.0F);
    }

}
