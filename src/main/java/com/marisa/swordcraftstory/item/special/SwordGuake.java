package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.entity.EntityTypeRegistry;
import com.marisa.swordcraftstory.entity.projectile.instance.AirCutterProjectileEntity;
import com.marisa.swordcraftstory.item.ore.IronOre;
import com.marisa.swordcraftstory.item.weapon.close.sword.AbstractSwordWeapon;
import com.marisa.swordcraftstory.util.CombatPropertiesUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 初心者匕首-挂科专属皮肤
 */

public class SwordGuake extends AbstractSwordWeapon {

    public SwordGuake() {
        super(new IronOre());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CombatPropertiesUtils.tipNovice(tooltip);
        tooltip.add(new TranslationTextComponent("挂科仙客没有抽到的心愿武器").mergeStyle(TextFormatting.DARK_PURPLE));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        AbstractArrowEntity abstractarrowentity = new AirCutterProjectileEntity(EntityTypeRegistry.AIR_CUTTER.get(), worldIn);
        abstractarrowentity.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double) 0.1F, playerIn.getPosZ());
        abstractarrowentity.setShooter(playerIn);
        abstractarrowentity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 1.0F);
        abstractarrowentity.setPierceLevel((byte) 127);
        abstractarrowentity.setNoGravity(true);
        abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
        worldIn.addEntity(abstractarrowentity);
//        AirCutterProjectileEntity abstractarrowentity = new AirCutterProjectileEntity(EntityTypeRegistry.AIR_CUTTER.get(), worldIn);
//        abstractarrowentity.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double) 0.1F, playerIn.getPosZ());
//        abstractarrowentity.setShooter(playerIn);
//        abstractarrowentity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 0.0F);
//        worldIn.addEntity(abstractarrowentity);
        return ActionResult.resultConsume(itemstack);
    }
}
