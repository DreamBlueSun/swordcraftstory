package com.marisa.swordcraftstory.entity.mob.model;

import com.marisa.swordcraftstory.entity.mob.instance.mob.RockLizard;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * 岩蜥蜴模型
 */

public class RockLizardModel extends EntityModel<RockLizard> {

    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;

    public RockLizardModel() {
        textureWidth = 128;
        textureHeight = 128;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-8.0F, -26.0F, 3.0F);
        bb_main.addChild(cube_r1);
        setRotationAngle(cube_r1, -1.5708F, -1.1345F, -1.5708F);
        cube_r1.setTextureOffset(0, 84).addBox(-1.0F, -23.0F, 5.0F, 13.0F, 6.0F, 6.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-8.0F, -26.0F, 3.0F);
        bb_main.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -1.5708F, 3.1416F);
        cube_r2.setTextureOffset(0, 66).addBox(3.0F, -9.0F, 11.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
        cube_r2.setTextureOffset(0, 66).addBox(3.0F, -9.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
        cube_r2.setTextureOffset(0, 66).addBox(-9.0F, -9.0F, 11.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
        cube_r2.setTextureOffset(0, 66).addBox(-9.0F, -9.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
        cube_r2.setTextureOffset(0, 1).addBox(-11.0F, -27.0F, 0.0F, 20.0F, 18.0F, 16.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-8.0F, -26.0F, 3.0F);
        bb_main.addChild(cube_r3);
        setRotationAngle(cube_r3, 1.5708F, -1.4835F, 1.5708F);
        cube_r3.setTextureOffset(0, 41).addBox(-21.0F, -17.0F, 7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r3.setTextureOffset(0, 37).addBox(-19.0F, -25.0F, 2.0F, 10.0F, 14.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(RockLizard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
