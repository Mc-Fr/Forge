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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * L'item des panneaux.
 *
 * @author Mc-Fr
 */
public class McfrItemSign extends McfrItem {
  private McfrBlockStandingSign standingSign;
  private McfrBlockWallSign wallSign;
  private McfrBlockSuspendedSign suspendedSign;
  private Class<? extends TileEntityMcfrSign> teClass;
  private boolean canBreakLeavesFaster;

  /**
   * Crée un item de panneau.
   * 
   * @param name le nom
   * @param standingSign le panneau droit
   * @param wallSign le panneau mural
   * @param suspendedSign le panneau suspendu
   * @param teClass la classe de la tile entity
   * @param canBreakLeavesFaster si true, permet de détruire les feuilles plus vite
   */
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
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
      float hitY, float hitZ) {
    if (playerIn.canPlayerEdit(pos, facing, stack)) {
      if (!worldIn.isRemote) {
        if (facing == EnumFacing.UP && this.standingSign.canPlaceBlockAt(worldIn, pos.up())) {
          pos = pos.up();
          int rotation = FacingUtils.getSignRotation(playerIn);
          worldIn.setBlockState(pos, this.standingSign.getDefaultState().withProperty(McfrBlockStandingSign.ROTATION, rotation), 11);
        }
        else if (facing == EnumFacing.DOWN && this.suspendedSign != null && this.suspendedSign.canPlaceBlockAt(worldIn, pos.down())) {
          pos = pos.down();
          int rotation = FacingUtils.getSignRotation(playerIn);
          worldIn.setBlockState(pos, this.suspendedSign.getDefaultState().withProperty(McfrBlockSuspendedSign.ROTATION, rotation), 11);
        }
        else if (facing.getAxis() != Axis.Y && this.wallSign.canPlaceBlockAt(worldIn, pos.offset(facing))) {
          pos = pos.offset(facing);
          worldIn.setBlockState(pos, this.wallSign.getDefaultState().withProperty(McfrBlockWallSign.FACING, facing), 11);
        }

        stack.stackSize--;
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te.getClass() == this.teClass && !ItemBlock.setTileEntityNBT(worldIn, playerIn, pos, stack)) {
          ((TileEntityMcfrSign) te).setPlayer(playerIn);
          McfrNetworkWrapper.getInstance().sendTo(new OpenEditMcfrSignMessage(pos, SignType.fromClass(this.teClass)), (EntityPlayerMP) playerIn);
        }
      }

      return EnumActionResult.SUCCESS;
    }

    return EnumActionResult.FAIL;
  }

  @Override
  public float getStrVsBlock(ItemStack stack, IBlockState state) {
    return this.canBreakLeavesFaster && state.getMaterial() == Material.LEAVES ? 30.0F : super.getStrVsBlock(stack, state);
  }
}
