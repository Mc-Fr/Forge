package net.mcfr.decoration.signs;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tileEntities.TileEntityTombstone;
import net.mcfr.utils.FacingUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTombstone extends McfrItem {
  public ItemTombstone() {
    super("tombstone", 16, CreativeTabs.DECORATIONS);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (playerIn.canPlayerEdit(pos, facing, stack)) {
      if (!worldIn.isRemote && facing == EnumFacing.UP && McfrBlocks.TOMBSTONE.canPlaceBlockAt(worldIn, pos.offset(facing))) {
        pos = pos.offset(facing);
        int rotation = FacingUtils.getSignRotation(playerIn);
        worldIn.setBlockState(pos, McfrBlocks.TOMBSTONE.getDefaultState().withProperty(BlockTombstone.ROTATION, rotation), 11);

        stack.stackSize--;
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te instanceof TileEntityTombstone) {
          // TODO
          // McfrNetworkWrapper.getInstance().sendTo(new OpenEditTombstoneMessage(pos),
          // (EntityPlayerMP) playerIn);
        }
      }

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }
}
