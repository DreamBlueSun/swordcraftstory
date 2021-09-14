//package com.marisa.swordcraftstory.entity;
//
//import com.marisa.swordcraftstory.Story;
//import com.marisa.swordcraftstory.entity.instance.Slime;
//import com.marisa.swordcraftstory.entity.instance.mob.RockLizard;
//import net.minecraft.entity.EntityClassification;
//import net.minecraft.entity.EntityType;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//
///**
// * 实体类型注册
// */
//
//public class EntityTypeRegistry {
//
//    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Story.MOD_ID);
//
//    public static RegistryObject<EntityType<Slime>> SLIME = ENTITIES.register("slime", () ->
//            EntityType.Builder.create(Slime::new, EntityClassification.MISC).size(3, 0.5F).build("slime")
//    );
//    public static RegistryObject<EntityType<RockLizard>> ROCK_LIZARD = ENTITIES.register("rock_lizard", () ->
//            EntityType.Builder.create(RockLizard::new, EntityClassification.MONSTER).size(1.0F, 1.0F).build("rock_lizard")
//    );
//}
