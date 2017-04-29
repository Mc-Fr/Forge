package net.mcfr.farming;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Bloc du bas du chanvre.
 *
 * @author Mc-Fr
 */
public class BlockHempBase extends BlockBushBase {
  public BlockHempBase() {
    super("hemp");
  }

  @Override
  public BlockBushTop getTopBlock() {
    return McfrBlocks.HEMP_TOP;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return null;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrItems.HEMP);
  }
}
