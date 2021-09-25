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
}
