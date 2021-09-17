package com.marisa.swordcraftstory.gui.container;

import com.marisa.swordcraftstory.Story;
import com.marisa.swordcraftstory.gui.container.IInt.BlockPosIInt;
import com.marisa.swordcraftstory.gui.container.IInt.WeaponEdgePointIInt;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 容器类型注册
 */

public class ContainerTypeRegistry {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Story.MOD_ID);

    public static final RegistryObject<ContainerType<WeaponMakeContainer>> WEAPON_MAKE_CONTAINER =
            CONTAINERS.register("weapon_make_container", () ->
                    IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                            new WeaponMakeContainer(windowId, inv, data.readBlockPos(), new BlockPosIInt())));

    public static final RegistryObject<ContainerType<WeaponIntensifyContainer>> WEAPON_INTENSIFY_CONTAINER =
            CONTAINERS.register("weapon_intensify_container", () ->
                    IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                            new WeaponIntensifyContainer(windowId, inv, data.readBlockPos(), new BlockPosIInt())));

    public static final RegistryObject<ContainerType<WeaponEdgeContainer>> WEAPON_EDGE_CONTAINER =
            CONTAINERS.register("weapon_edge_container", () ->
                    IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                            new WeaponEdgeContainer(windowId, inv, data.readBlockPos(), new WeaponEdgePointIInt())));

    public static final RegistryObject<ContainerType<WeaponCollapseContainer>> WEAPON_COLLAPSE_CONTAINER =
            CONTAINERS.register("weapon_collapse_container", () ->
                    IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) ->
                            new WeaponCollapseContainer(windowId, inv, data.readBlockPos(), new BlockPosIInt())));
}
