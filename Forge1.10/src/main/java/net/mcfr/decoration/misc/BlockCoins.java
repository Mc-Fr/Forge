package net.mcfr.decoration.misc;

import java.util.ArrayList;
import java.util.List;

import net.mcfr.McfrItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCoins extends BlockFloorDecoration {
  public BlockCoins() {
    super("coins_block", Material.WOOD, SoundType.METAL, 0.5f, null);
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> drops = new ArrayList<>();
    drops.add(new ItemStack(McfrItems.COIN, 1, 0));
    return drops;
  }
}
