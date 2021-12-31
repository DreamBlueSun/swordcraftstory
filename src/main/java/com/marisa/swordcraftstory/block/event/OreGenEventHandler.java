package com.marisa.swordcraftstory.block.event;

import com.marisa.swordcraftstory.block.BlockRegistry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

/**
 * 监听事件处理器
 */

public class OreGenEventHandler {

    /**
     * 单个矿脉最大矿物数
     */
    private static final int VEIN_SIZE = 12;

    private static PlacedFeature commonPlacedFeature(ConfiguredFeature<OreConfiguration, ?> configured, String id, int count, int minHeight, int maxHeight) {
        HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight));
        List<PlacementModifier> modifiers = List.of(CountPlacement.of(count), InSquarePlacement.spread(), placement, BiomeFilter.biome());
        return PlacementUtils.register(id, configured.placed(modifiers));
    }

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent event) {
        List<OreConfiguration.TargetBlockState> blockStateList = List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.STORY_ORE_BLOCK.defaultBlockState()),
                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.STORY_ORE_BLOCK.defaultBlockState()));
        ConfiguredFeature<OreConfiguration, ?> configured = FeatureUtils.register("story_ore", Feature.ORE.configured(new OreConfiguration(blockStateList, VEIN_SIZE)));
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, commonPlacedFeature(configured, "story_ore_upper", 90, 80, 384));
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, commonPlacedFeature(configured, "story_ore_middle", 20, -56, 56));
    }

}
