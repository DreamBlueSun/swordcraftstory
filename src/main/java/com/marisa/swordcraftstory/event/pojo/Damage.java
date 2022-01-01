package com.marisa.swordcraftstory.event.pojo;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.save.util.MobAttributesUtils;
import com.marisa.swordcraftstory.save.util.PlayerDataManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;

/**
 * 伤害
 */

public class Damage {

    private final float amount;
    private final DamageSource source;
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
        if (target instanceof Player player) {
            this.lvWeight = PlayerDataManager.getLv(PlayerDataManager.get(player.getStringUUID()).getXp());
        } else if (target instanceof Mob) {
            this.lvWeight = MobAttributesUtils.getMobLv((ServerLevel) target.level, target.getStringUUID()) * 0.5F;
        } else {
            this.lvWeight = 0.0F;
        }
        this.p = 0;
        this.m = 0;
        this.r = 0;
        init();
    }

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
                        AbstractArrow arrow = (AbstractArrow) this.source.getDirectEntity();
                        this.p = arrow != null ? (float) arrow.getBaseDamage() : 5.0F;
                    } else {
                        this.p = amount;
                    }
                } catch (Exception e) {
                    Story.LOG.error("ARROW伤害异常：" + this.source, e);
                }
                break;
            case "mob":
                try {
                    if (this.amount > 0.0F && this.source.getEntity() != null && this.source.getEntity() instanceof Mob mob) {
                        int lv = MobAttributesUtils.getMobLv((ServerLevel) mob.level, mob.getStringUUID());
                        this.p = Mth.clamp(this.amount, 2.0F, 20.0F) * (1 + lv * 0.6F);
                    } else {
                        this.p = this.amount;
                    }
                } catch (Exception e) {
                    Story.LOG.error("MOB伤害异常：" + this.source, e);
                }
                break;
            case "inFire":
            case "onFire":
                this.m = fixedDamageUp(2.0F, 0.5F);
                break;
            case "fireball":
                this.m = fixedDamageUp(10.0F, 2.0F);
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
                this.m = fixedDamageUp(clamp, Math.max(clamp * 0.3F, 1.0F));
                break;
            case "wither":
                //凋零
                this.r = fixedDamageUp(1.0F, 1.0F);
                break;
            case "lava":
                //岩浆
                this.m = fixedDamageUp(1.0F, 1.0F);
                this.r = fixedDamageUp(4.0F, 0.5F);
                break;
            case "explosion.player":
                //爆炸
                this.p = fixedDamageUp(12.0F, 2.0F);
                this.m = fixedDamageUp(8.0F, 1.0F);
                this.r = fixedDamageUp(20.0F, 4.0F);
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
                this.r = fixedDamageUp(2.0F, 2.0F);
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
                this.r = 1.0F;
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

    public float totalAll() {
        return Math.max(this.p + this.m + this.r, 0.0F);
    }

    public float totalPM() {
        return Math.max(this.p + this.m, 0.0F);
    }

}
