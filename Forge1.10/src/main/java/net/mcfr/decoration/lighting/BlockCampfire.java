package net.mcfr.decoration.lighting;

import javax.annotation.Nullable;

import net.mcfr.McfrBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Feu de camp (éteint).
 *
 * @author Mc-Fr
 */
public class BlockCampfire extends BlockCampfireBase {
  public BlockCampfire() {
    super(false);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (heldItem == null) {
      heldItem = playerIn.getHeldItem(EnumHand.OFF_HAND);
    }

    if (heldItem != null) {
      if (heldItem.getItem() == Items.COAL) {
        replaceByLitFirecamp(worldIn, pos, BlockLitCampFire.MAX_STAGE);
        heldItem.stackSize--;

        return true;
      }
      else if (heldItem.getItem() == Items.FLINT_AND_STEEL) {
        replaceByLitFirecamp(worldIn, pos, BlockLitCampFire.MAX_STAGE);
        heldItem.damageItem(1, playerIn);

        return true;
      }
      else if (heldItem.getItem() == Items.STICK) {
        replaceByLitFirecamp(worldIn, pos, 0);
        heldItem.stackSize--;

        return true;
      }
    }

    return false;
  }

  /**
   * Remplace ce bloc par un feu de camp allumé.
   * 
   * @param world le monde
   * @param pos la position
   * @param meta le metadata
   */
  private void replaceByLitFirecamp(World world, BlockPos pos, int meta) {
    world.setBlockState(pos, McfrBlocks.LIT_CAMPFIRE.getDefaultState().withProperty(BlockLitCampFire.STAGE, meta));
  }
}
