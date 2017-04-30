package net.mcfr.decoration.signs;

import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tile_entities.TileEntityMcfrSign;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditMcfrSignMessage;
import net.mcfr.network.OpenEditMcfrSignMessage.SignType;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class McfrItemSign extends McfrItem {
  private McfrBlockStandingSign standingSign;
  private McfrBlockWallSign wallSign;
  private McfrBlockSuspendedSign suspendedSign;
  private Class<? extends TileEntityMcfrSign> teClass;
  private boolean canBreakLeavesFaster;

  public McfrItemSign(String name, McfrBlockStandingSign standingSign, McfrBlockWallSign wallSign, McfrBlockSuspendedSign suspendedSign,
      Class<? extends TileEntityMcfrSign> teClass, boolean canBreakLeavesFaster) {
    super(name, 64, CreativeTabs.DECORATIONS);
    this.standingSign = standingSign;
    this.wallSign = wallSign;
    this.suspendedSign = suspendedSign;
    this.teClass = teClass;
    this.canBreakLeavesFaster = canBreakLeavesFaster;
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
      float hitY, float hitZ) {
    if (player.canPlayerEdit(pos, facing, stack)) {
      if (!world.isRemote) {
        if (facing == EnumFacing.UP && this.standingSign.canPlaceBlockAt(world, pos.up())) {
          pos = pos.up();
          int rotation = FacingUtils.getSignRotation(player);
          world.setBlockState(pos, this.standingSign.getDefaultState().withProperty(McfrBlockStandingSign.ROTATION, rotation), 11);
        }
        else if (facing == EnumFacing.DOWN && this.suspendedSign != null && this.suspendedSign.canPlaceBlockAt(world, pos.down())) {
          pos = pos.down();
          int rotation = FacingUtils.getSignRotation(player);
          world.setBlockState(pos, this.suspendedSign.getDefaultState().withProperty(McfrBlockSuspendedSign.ROTATION, rotation), 11);
        }
        else if (facing.getAxis() != Axis.Y && this.wallSign.canPlaceBlockAt(world, pos.offset(facing))) {
          pos = pos.offset(facing);
          world.setBlockState(pos, this.wallSign.getDefaultState().withProperty(McfrBlockWallSign.FACING, facing), 11);
        }

        stack.stackSize--;
        TileEntity te = world.getTileEntity(pos);

        if (te != null && te.getClass() == this.teClass && !ItemBlock.setTileEntityNBT(world, player, pos, stack)) {
          ((TileEntityMcfrSign) te).setPlayer(player);
          McfrNetworkWrapper.getInstance().sendTo(new OpenEditMcfrSignMessage(pos, SignType.fromClass(this.teClass)), (EntityPlayerMP) player);
        }
      }
      else if (!(this.standingSign instanceof BlockStandingOrpSign))
        world.playSound(player, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 2f, 0.75f);

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }

  @Override
  public float getStrVsBlock(ItemStack stack, IBlockState state) {
    return this.canBreakLeavesFaster && state.getMaterial() == Material.LEAVES ? 30.0F : super.getStrVsBlock(stack, state);
  }
}
