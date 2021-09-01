package com.marisa.swordcraftstory.item.special;

import com.marisa.swordcraftstory.group.GroupRegistry;
import com.marisa.swordcraftstory.gui.SmitheryGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

/**
 * @description:
 * @date: 2021/9/2 0002 1:39
 */

public class GuiTest extends Item {
    public GuiTest() {
        super(new Properties().group(GroupRegistry.COMBAT_GROUP));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote) {
            DistExecutor.runWhenOn(Dist.CLIENT, () -> SmitheryGui::open);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
