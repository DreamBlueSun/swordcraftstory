package com.marisa.swordcraftstory.entity.projectile.rander;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.entity.projectile.instance.AirCutterProjectileEntity;
import com.marisa.swordcraftstory.entity.projectile.model.AirCutterModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 *
 */

public class AirCutterRender extends EntityRenderer<AirCutterProjectileEntity> {

    private EntityModel<AirCutterProjectileEntity> model;

    public AirCutterRender(EntityRendererManager renderManager) {
        super(renderManager);
        model = new AirCutterModel();
    }

    @Override
    public ResourceLocation getEntityTexture(AirCutterProjectileEntity entity) {
        return new ResourceLocation(Story.MOD_ID, "textures/entity/projectile/test.png");
    }

    @Override
    public void render(AirCutterProjectileEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }
}
