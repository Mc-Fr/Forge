package net.mcfr.decoration.signs;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallNote extends McfrBlock implements ITileEntityProvider {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  public BlockWallNote() {
    super("wall_note_block", Material.WOOD, SoundType.WOOD, 1, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    switch (state.getValue(FACING)) {
      case NORTH:
        return new AxisAlignedBB(0, 0, 0.875f, 1, 1, 1);
      case SOUTH:
        return new AxisAlignedBB(0, 0, 0, 1, 1, 0.125f);
      case WEST:
        return new AxisAlignedBB(0.875f, 0, 0, 1, 1, 1);
      case EAST:
        return new AxisAlignedBB(0, 0, 0, 0.125f, 1, 1);
      default:
        return FULL_BLOCK_AABB;
    }
  }

  @Override
  @SuppressWarnings("deprecation")
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    EnumFacing enumfacing = state.getValue(FACING);

    if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid()) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }

    super.neighborChanged(state, worldIn, pos, blockIn);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    EnumFacing enumfacing = EnumFacing.getFront(meta);

    if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
      enumfacing = EnumFacing.NORTH;
    }

    return getDefaultState().withProperty(FACING, enumfacing);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getIndex();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityWallNote) {
        TileEntityWallNote t = (TileEntityWallNote) te;
        int firstLineIndex = 0;
        int lastLineIndex = 14;

        // On supprime les lignes vides en début et en fin de note.
        while (t.getText()[firstLineIndex].getUnformattedText().equals("§r"))
          firstLineIndex++;
        while (t.getText()[lastLineIndex].getUnformattedText().equals("§r"))
          lastLineIndex--;

        for (int i = firstLineIndex; i <= lastLineIndex; i++)
          playerIn.addChatComponentMessage(t.getText()[i]);

        return true;
      }
    }

    return false;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWallNote();
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.WALL_NOTE;
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.WALL_NOTE);
  }
}
