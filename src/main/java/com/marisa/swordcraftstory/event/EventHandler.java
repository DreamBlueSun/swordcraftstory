package com.marisa.swordcraftstory.event;

import com.marisa.swordcraftstory.block.ore.OreGenerate;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class EventHandler {

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent event) {
        //生物群系加载时加入矿石生成
        OreGenerate.join(event);
    }

}
