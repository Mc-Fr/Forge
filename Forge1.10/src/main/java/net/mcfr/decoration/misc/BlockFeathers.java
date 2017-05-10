package net.mcfr.decoration.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Ce bloc représente des plumes au sol.
 *
 * @author Mc-Fr
 */
public class BlockFeathers extends BlockFloorDecoration {
  public BlockFeathers() {
    super("feathers_block", Material.WOOD, SoundType.CLOTH, 0.5f, null);
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> drops = new ArrayList<>();
    drops.add(new ItemStack(Items.FEATHER, 1));
    return drops;
  }
}
