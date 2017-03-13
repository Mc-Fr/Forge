package net.mcfr.decoration.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPebbles extends BlockFloorDecoration {
  public BlockPebbles() {
    super("pebbles_block", Material.WOOD, SoundType.GROUND, 0.5f, null);
  }
  
  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> drops = new ArrayList<>();
    drops.add(new ItemStack(Blocks.COBBLESTONE, 1));
    return drops;
  }
}
