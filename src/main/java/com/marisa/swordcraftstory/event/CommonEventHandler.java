package com.marisa.swordcraftstory.event;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event) {
        //拦截伤害计算
//        int armorValue = event.getEntityLiving().getArmorValue();
//        event.setCanceled(true);
    }

}
