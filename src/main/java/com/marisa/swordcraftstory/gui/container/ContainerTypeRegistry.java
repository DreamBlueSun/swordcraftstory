package com.marisa.swordcraftstory.gui.container;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.IntensifyEdgePointInt;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @description:
 * @date: 2021/9/7 0007 21:41
 */

public class ContainerTypeRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Story.MOD_ID);

    public static final RegistryObject<ContainerType<IntensifyEdgeContainer>> INTENSIFY_EDGE_CONTAINER =
            CONTAINERS.register("intensify_edge_container", () ->
                    IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                            new IntensifyEdgeContainer(windowId, inv, data.readBlockPos(), new IntensifyEdgePointInt())));
}
