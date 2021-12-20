package com.marisa.swordcraftstory.item;


import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
            registerBowProperties(ItemRegistry.FANTASY_BOW.get());
            registerBowProperties(ItemRegistry.GALIA_BOW.get());
            registerBowProperties(ItemRegistry.CUISINE_BOW.get());
            registerBowProperties(ItemRegistry.DOUBLE_SNAKE_BOW.get());
            registerBowProperties(ItemRegistry.SPRINT_BOW.get());
            registerBowProperties(ItemRegistry.XCEL_BOW.get());
            registerBowProperties(ItemRegistry.LEIJI_STOWE_BOW.get());
            registerBowProperties(ItemRegistry.DEW_FIREFLY_BOW.get());
            registerBowProperties(ItemRegistry.STEEL_TEETH_BOW.get());
            registerBowProperties(ItemRegistry.CATS_PAW_BOW.get());
            registerBowProperties(ItemRegistry.LUMINOSITY_BOW.get());

            //用作幻化的弓也注册
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
            registerModelChangeProperties(ItemRegistry.FANTASY_SWORD.get());
            registerModelChangeProperties(ItemRegistry.GALIA_SWORD.get());
            registerModelChangeProperties(ItemRegistry.CUISINE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.DOUBLE_SNAKE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.SPRINT_SWORD.get());
            registerModelChangeProperties(ItemRegistry.XCEL_SWORD.get());
            registerModelChangeProperties(ItemRegistry.LEIJI_STOWE_SWORD.get());
            registerModelChangeProperties(ItemRegistry.DEW_FIREFLY_SWORD.get());
            registerModelChangeProperties(ItemRegistry.STEEL_TEETH_SWORD.get());
            registerModelChangeProperties(ItemRegistry.CATS_PAW_SWORD.get());
            registerModelChangeProperties(ItemRegistry.LUMINOSITY_SWORD.get());

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
            registerModelChangeProperties(ItemRegistry.FANTASY_BOW.get());
            registerModelChangeProperties(ItemRegistry.GALIA_BOW.get());
            registerModelChangeProperties(ItemRegistry.CUISINE_BOW.get());
            registerModelChangeProperties(ItemRegistry.DOUBLE_SNAKE_BOW.get());
            registerModelChangeProperties(ItemRegistry.SPRINT_BOW.get());
            registerModelChangeProperties(ItemRegistry.XCEL_BOW.get());
            registerModelChangeProperties(ItemRegistry.LEIJI_STOWE_BOW.get());
            registerModelChangeProperties(ItemRegistry.DEW_FIREFLY_BOW.get());
            registerModelChangeProperties(ItemRegistry.STEEL_TEETH_BOW.get());
            registerModelChangeProperties(ItemRegistry.CATS_PAW_BOW.get());
            registerModelChangeProperties(ItemRegistry.LUMINOSITY_BOW.get());

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
            registerModelChangeProperties(ItemRegistry.FANTASY_AXE.get());
            registerModelChangeProperties(ItemRegistry.GALIA_AXE.get());
            registerModelChangeProperties(ItemRegistry.CUISINE_AXE.get());
            registerModelChangeProperties(ItemRegistry.DOUBLE_SNAKE_AXE.get());
            registerModelChangeProperties(ItemRegistry.SPRINT_AXE.get());
            registerModelChangeProperties(ItemRegistry.XCEL_AXE.get());
            registerModelChangeProperties(ItemRegistry.LEIJI_STOWE_AXE.get());
            registerModelChangeProperties(ItemRegistry.DEW_FIREFLY_AXE.get());
            registerModelChangeProperties(ItemRegistry.STEEL_TEETH_AXE.get());
            registerModelChangeProperties(ItemRegistry.CATS_PAW_AXE.get());
            registerModelChangeProperties(ItemRegistry.LUMINOSITY_AXE.get());
        });
    }

    private static void registerBowProperties(final Item bow) {
        ItemProperties.register(bow, new ResourceLocation("pull"),
                (item, world, entity, i) -> {
                    if (entity == null) return 0.0F;
                    if (entity.getUseItem() != item) return 0.0F;
                    return (item.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
                });
        ItemProperties.register(bow, new ResourceLocation("pulling"),
                (item, world, entity, i) -> (entity != null && entity.isUsingItem() && entity.getUseItem() == item) ? 1.0F : 0.0F);
    }

    private static void registerModelChangeProperties(final Item weapon) {
        ItemProperties.register(weapon, new ResourceLocation("model_change"),
                (stack, world, entity, i) -> stack.getOrCreateTag().getInt("story_combat_model_change"));
    }
}
