package com.marisa.swordcraftstory.item.combat;

import com.marisa.swordcraftstory.Story;
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
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Story.MOD_ID);

    //怪物等级
    public static final RegistryObject<Attribute> MOB_LV = ATTRIBUTES.register("mob_lv", () -> new RangedAttribute("attribute.name.story.mob_lv", 0.0D, 0.0D, 255.0D).setShouldWatch(true));

}
