//package com.marisa.swordcraftstory.entity;
//
//import com.marisa.swordcraftstory.Story;
//import com.marisa.swordcraftstory.entity.projectile.instance.AirCutterProjectileEntity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
///**
// * 实体类型注册
// */
//
//public class EntityTypeRegistry {
//
//    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Story.MOD_ID);
//
//    public static RegistryObject<EntityType<AirCutterProjectileEntity>> AIR_CUTTER = ENTITIES.register("air_cutter", () ->
//            EntityType.Builder.of(AirCutterProjectileEntity::new, MobCategory.MISC).sized(1.0F, 2.0F).build("air_cutter")
//    );
//}
