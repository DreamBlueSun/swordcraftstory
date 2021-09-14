package com.marisa.swordcraftstory.entity.rander;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.entity.instance.Slime;
import com.marisa.swordcraftstory.entity.model.SlimeModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

/**
 * 史莱姆渲染
 */

public class SlimeRender extends EntityRenderer<Slime> {
    private EntityModel<Slime> slimeModel;

    public SlimeRender(EntityRendererManager renderManager) {
        super(renderManager);
        slimeModel = new SlimeModel();
    }

    @Override
    public ResourceLocation getEntityTexture(Slime entity) {
        return new ResourceLocation(Story.MOD_ID, "textures/entity/slime.png");
    }

    @Override
    public void render(Slime entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.slimeModel.getRenderType(this.getEntityTexture(entityIn)));
        this.slimeModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }
}
