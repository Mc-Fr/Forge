package net.mcfr.decoration.signs;

import net.mcfr.commons.McfrItem;
import net.mcfr.decoration.signs.tileEntities.TileEntityMcfrSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class McfrItemSign extends McfrItem {
  private McfrBlockStandingSign standingSign;
  private McfrBlockWallSign wallSign;
  private McfrBlockSuspendedSign suspendedSign;
  private Class<? extends TileEntityMcfrSign> teClass;

  public McfrItemSign(String name, McfrBlockStandingSign standingSign, McfrBlockWallSign wallSign, McfrBlockSuspendedSign suspendedSign, Class<? extends TileEntityMcfrSign> teClass) {
    // TEMP null CreativeTab
    super(name, 64, null/* CreativeTabs.DECORATIONS */);
    this.standingSign = standingSign;
    this.wallSign = wallSign;
    this.suspendedSign = suspendedSign;
    this.teClass = teClass;
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (playerIn.canPlayerEdit(pos, facing, stack)) {
      if (!worldIn.isRemote) {
        if (facing == EnumFacing.UP && this.standingSign.canPlaceBlockAt(worldIn, pos.up())) {
          pos = pos.up();
          int i = getRotation(playerIn);
          worldIn.setBlockState(pos, this.standingSign.getDefaultState().withProperty(McfrBlockStandingSign.ROTATION, Integer.valueOf(i)), 11);
        }
        else if (facing == EnumFacing.DOWN && this.suspendedSign.canPlaceBlockAt(worldIn, pos.down())) {
          pos = pos.down();
          int i = getRotation(playerIn);
          worldIn.setBlockState(pos, this.suspendedSign.getDefaultState().withProperty(McfrBlockSuspendedSign.ROTATION, Integer.valueOf(i)), 11);
        }
        else if (this.wallSign.canPlaceBlockAt(worldIn, pos.offset(facing))) {
          pos = pos.offset(facing);
          worldIn.setBlockState(pos, this.wallSign.getDefaultState().withProperty(McfrBlockWallSign.FACING, facing), 11);
        }

        stack.stackSize--;
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te.getClass() == this.teClass && !ItemBlock.setTileEntityNBT(worldIn, playerIn, pos, stack)) {
          // ((TileEntityMcfrSign) te).setPlayer(playerIn);
          playerIn.openEditSign((TileEntityMcfrSign) te);
          // McfrNetworkWrapper.getInstance().sendTo(new OpenEditMcfrSignMessage(pos),
          // (EntityPlayerMP) playerIn);
        }
      }

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }

  private int getRotation(EntityPlayer playerIn) {
    return MathHelper.floor_double((playerIn.rotationYaw + 180) * 16 / 360 + 0.5) & 15;
  }
}
