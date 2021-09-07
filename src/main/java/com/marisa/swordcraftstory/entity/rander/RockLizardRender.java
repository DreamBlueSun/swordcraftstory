package com.marisa.swordcraftstory.entity.rander;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.entity.instance.mob.RockLizard;
import com.marisa.swordcraftstory.entity.model.RockLizardModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * @description:
 * @date: 2021/9/6 0006 2:20
 */

public class RockLizardRender extends EntityRenderer<RockLizard> {

    private EntityModel<RockLizard> rocklizard;

    public RockLizardRender(EntityRendererManager renderManager) {
        super(renderManager);
        rocklizard = new RockLizardModel();
    }

    @Override
    public ResourceLocation getEntityTexture(RockLizard entity) {
        return new ResourceLocation(Story.MOD_ID, "textures/entity/rock_lizard.png");
    }

    @Override
    public void render(RockLizard entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.rocklizard.getRenderType(this.getEntityTexture(entityIn)));
        this.rocklizard.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }
}
