package net.mcfr.decoration.signs;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrMain;
import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWallNote extends McfrItem {
  public ItemWallNote() {
    super("wall_note", 16, CreativeTabs.DECORATIONS);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (playerIn.canPlayerEdit(pos, facing, stack)) {
      if (!worldIn.isRemote) {
        if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
          return EnumActionResult.FAIL;
        }
        else if (McfrBlocks.WALL_NOTE.canPlaceBlockAt(worldIn, pos.offset(facing))) {
          pos = pos.offset(facing);
          worldIn.setBlockState(pos, McfrBlocks.WALL_NOTE.getDefaultState().withProperty(McfrBlockWallSign.FACING, facing), 11);
        }

        stack.stackSize--;
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te instanceof TileEntityWallNote) {
          playerIn.openGui(McfrMain.instance, CustomGuiScreens.WALL_NOTE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
      }

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }
}
