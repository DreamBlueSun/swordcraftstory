package com.marisa.swordcraftstory;

import com.marisa.swordcraftstory.bar.BarEventHandler;
import com.marisa.swordcraftstory.block.ore.OreGenEventHandler;
import com.marisa.swordcraftstory.event.CommonEventHandler;
import com.marisa.swordcraftstory.item.ItemRegistry;
import com.marisa.swordcraftstory.save.SaveEventHandler;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Story.MOD_ID)
public class Story {

    public static final String MOD_ID = "swordcraftstory";

    public static final int LV_MAX = 39;

    public static final Logger LOG = LogManager.getLogger();

    public Story() {

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        MinecraftForge.EVENT_BUS.register(new SaveEventHandler());
        MinecraftForge.EVENT_BUS.register(new OreGenEventHandler());
        MinecraftForge.EVENT_BUS.register(new BarEventHandler());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::attributeFix);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * 属性修改
     */
    private void attributeFix(FMLLoadCompleteEvent event) {
        for (Attribute attribute : ForgeRegistries.ATTRIBUTES) {
            if (attribute instanceof RangedAttribute rangedAttribute) {
                rangedAttribute.minValue = -65535.0F;
                rangedAttribute.maxValue = 65535.0F;
            }
        }
    }

}
