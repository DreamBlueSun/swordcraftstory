package com.marisa.swordcraftstory.item.combat;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description: 属性注册
 * @date: 2021/9/5 0005 7:39
 */

public class AttributesRegistry {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "swordcraftstory");

    //武器TEC
    public static final RegistryObject<Attribute> COMBAT_TEC = ATTRIBUTES.register("combat_tec", () -> new RangedAttribute("attribute.name.story.combat_tec", 0.0D, 0.0D, 255.0D).setShouldWatch(false));

}
