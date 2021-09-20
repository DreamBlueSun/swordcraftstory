package com.marisa.swordcraftstory.block.ore;

import com.marisa.swordcraftstory.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

/**
 * 矿石生成
 */
public class OreGenerate {

    public static void join(BiomeLoadingEvent event) {
        generate(event.getGeneration(),
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                BlockRegistry.STORY_ORE_BLOCK.get().getDefaultState(),
                8, 8, 0, 64, 64);
    }

    /**
     * 生成方法
     *
     * @param settingsBuilder 生成器
     * @param ruleTest        规则
     * @param veinSize        矿脉最大矿物数
     * @param bottomOffset    生成最小y值
     * @param topOffset       不明，设为0
     * @param maximum         生成最大y值
     * @param countPerChunk   区块最大矿脉数
     */
    public static void generate(BiomeGenerationSettingsBuilder settingsBuilder, RuleTest ruleTest, BlockState state,
                                int veinSize, int bottomOffset, int topOffset, int maximum, int countPerChunk) {
        settingsBuilder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(ruleTest, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(bottomOffset, topOffset, maximum)))
                        .square().count(countPerChunk));
    }

}
