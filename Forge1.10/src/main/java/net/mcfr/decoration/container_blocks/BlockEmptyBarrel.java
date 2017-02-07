package net.mcfr.decoration.container_blocks;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Tonneau vide.
 *
 * @author Mc-Fr
 */
public class BlockEmptyBarrel extends McfrBlock {
  public BlockEmptyBarrel() {
    super("empty_barrel_block", Material.WOOD, SoundType.WOOD, 2f, 5f, "axe", 0, null);
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrItems.EMPTY_BARREL);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.EMPTY_BARREL;
  }
}
