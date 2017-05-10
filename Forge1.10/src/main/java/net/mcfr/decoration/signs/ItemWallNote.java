package net.mcfr.decoration.signs;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tile_entities.TileEntityLargeSign;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditWallNoteMessage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Item de la note murale.
 *
 * @author Mc-Fr
 */
public class ItemWallNote extends McfrItem {
  public ItemWallNote() {
    super("wall_note", 16, CreativeTabs.DECORATIONS);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
      float hitY, float hitZ) {
    if (player.canPlayerEdit(pos, facing, stack)) {
      if (!world.isRemote) {
        if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
          return EnumActionResult.FAIL;
        }
        else if (McfrBlocks.WALL_NOTE.canPlaceBlockAt(world, pos.offset(facing))) {
          pos = pos.offset(facing);
          world.setBlockState(pos, McfrBlocks.WALL_NOTE.getDefaultState().withProperty(McfrBlockWallSign.FACING, facing), 11);
        }

        stack.stackSize--;
        TileEntity te = world.getTileEntity(pos);

        if (te != null && te instanceof TileEntityLargeSign) {
          McfrNetworkWrapper.getInstance().sendTo(new OpenEditWallNoteMessage(pos), (EntityPlayerMP) player);
        }
      }
      else
        world.playSound(player, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 2f, 0.75f);

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }
}
