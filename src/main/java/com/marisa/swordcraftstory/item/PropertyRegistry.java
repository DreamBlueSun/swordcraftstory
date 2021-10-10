package com.marisa.swordcraftstory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * 物品属性注册
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PropertyRegistry {

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        //注册拉弓重载模型
        event.enqueueWork(() -> {
            registerBowProperties(ItemRegistry.NOVICE_BOW.get());
            registerBowProperties(ItemRegistry.THIN_BOW.get());
            registerBowProperties(ItemRegistry.HARD_BOW.get());
            registerBowProperties(ItemRegistry.SOLEMNITY_BOW.get());
            registerBowProperties(ItemRegistry.IRON_BOW.get());
            registerBowProperties(ItemRegistry.SYNTHESIS_BOW.get());
            registerBowProperties(ItemRegistry.FEATHER_BOW.get());
            registerBowProperties(ItemRegistry.CUT_IRON_BOW.get());
            registerBowProperties(ItemRegistry.HUGER_BOW.get());
            registerBowProperties(ItemRegistry.SEVERE_PENALTY_BOW.get());
            registerBowProperties(ItemRegistry.RELICT_BOW.get());
            registerBowProperties(ItemRegistry.ELEGANCE_BOW.get());
            registerBowProperties(ItemRegistry.RIBBON_BOW.get());
            registerBowProperties(ItemRegistry.CARAPACE_FOSSIL_BOW.get());

            //幻化弓
            registerBowProperties(ItemRegistry.CONSTANT_BLOWERS_SNOW_BOW.get());
        });

        //注册幻化属性
        event.enqueueWork(() -> {
            //剑
            registerModelChangeProperties(ItemRegistry.NOVICE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.THIN_SWORD.get());
            registerModelChangeProperties(ItemRegistry.HARD_SWORD.get());
            registerModelChangeProperties(ItemRegistry.SOLEMNITY_SWORD.get());
            registerModelChangeProperties(ItemRegistry.IRON_SWORD.get());
            registerModelChangeProperties(ItemRegistry.SYNTHESIS_SWORD.get());
            registerModelChangeProperties(ItemRegistry.FEATHER_SWORD.get());
            registerModelChangeProperties(ItemRegistry.CUT_IRON_SWORD.get());
            registerModelChangeProperties(ItemRegistry.WIDE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.SEVERE_PENALTY_SWORD.get());
            registerModelChangeProperties(ItemRegistry.RELICT_SWORD.get());
            registerModelChangeProperties(ItemRegistry.ELEGANCE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.RIBBON_SWORD.get());
            registerModelChangeProperties(ItemRegistry.CARAPACE_FOSSIL_SWORD.get());

            //弓
            registerModelChangeProperties(ItemRegistry.NOVICE_BOW.get());
            registerModelChangeProperties(ItemRegistry.THIN_BOW.get());
            registerModelChangeProperties(ItemRegistry.HARD_BOW.get());
            registerModelChangeProperties(ItemRegistry.SOLEMNITY_BOW.get());
            registerModelChangeProperties(ItemRegistry.IRON_BOW.get());
            registerModelChangeProperties(ItemRegistry.SYNTHESIS_BOW.get());
            registerModelChangeProperties(ItemRegistry.FEATHER_BOW.get());
            registerModelChangeProperties(ItemRegistry.CUT_IRON_BOW.get());
            registerModelChangeProperties(ItemRegistry.HUGER_BOW.get());
            registerModelChangeProperties(ItemRegistry.SEVERE_PENALTY_BOW.get());
            registerModelChangeProperties(ItemRegistry.RELICT_BOW.get());
            registerModelChangeProperties(ItemRegistry.ELEGANCE_BOW.get());
            registerModelChangeProperties(ItemRegistry.RIBBON_BOW.get());
            registerModelChangeProperties(ItemRegistry.CARAPACE_FOSSIL_BOW.get());

            //斧
            registerModelChangeProperties(ItemRegistry.NOVICE_AXE.get());
            registerModelChangeProperties(ItemRegistry.THIN_AXE.get());
            registerModelChangeProperties(ItemRegistry.HARD_AXE.get());
            registerModelChangeProperties(ItemRegistry.SOLEMNITY_AXE.get());
            registerModelChangeProperties(ItemRegistry.IRON_AXE.get());
            registerModelChangeProperties(ItemRegistry.SYNTHESIS_AXE.get());
            registerModelChangeProperties(ItemRegistry.FEATHER_AXE.get());
            registerModelChangeProperties(ItemRegistry.CUT_IRON_AXE.get());
            registerModelChangeProperties(ItemRegistry.WIDE_AXE.get());
            registerModelChangeProperties(ItemRegistry.SEVERE_PENALTY_AXE.get());
            registerModelChangeProperties(ItemRegistry.RELICT_AXE.get());
            registerModelChangeProperties(ItemRegistry.ELEGANCE_AXE.get());
            registerModelChangeProperties(ItemRegistry.RIBBON_AXE.get());
            registerModelChangeProperties(ItemRegistry.CARAPACE_FOSSIL_AXE.get());
        });
    }

    private static void registerBowProperties(final Item bow) {
        ItemModelsProperties.registerProperty(bow, new ResourceLocation("pull"),
                (item, world, entity) -> {
                    if (entity == null) return 0.0F;
                    if (entity.getActiveItemStack() != item) return 0.0F;
                    return (item.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
                });
        ItemModelsProperties.registerProperty(bow, new ResourceLocation("pulling"),
                (item, world, entity) -> (entity != null && entity.isHandActive() && entity.getActiveItemStack() == item) ? 1.0F : 0.0F);
    }

    private static void registerModelChangeProperties(final Item weapon) {
        ItemModelsProperties.registerProperty(weapon, new ResourceLocation("model_change"),
                (stack, world, entity) -> stack.getOrCreateTag().getInt("story_combat_model_change"));
    }
}
