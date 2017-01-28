package net.mcfr.decoration.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase.EnumRailDirection;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Classe servant de base aux cordes. Leur comportement est identique aux rails normaux.
 *
 * @author Mc-Fr
 */
public class BlockRope extends McfrBlock {
  public static final PropertyEnum<EnumRailDirection> SHAPE = PropertyEnum.create("shape", EnumRailDirection.class);

  private static final AxisAlignedBB FLAT_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.125, 1);

  public static boolean isRopeBlock(World worldIn, BlockPos pos) {
    return isRopeBlock(worldIn.getBlockState(pos));
  }

  public static boolean isRopeBlock(IBlockState state) {
    return state.getBlock() instanceof BlockRope;
  }

  public BlockRope() {
    super("rope_block", Material.CLOTH, SoundType.CLOTH, 0, 0.5f, null, -1, CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH));
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    EnumRailDirection dir = state.getBlock() == this ? state.getValue(SHAPE) : null;
    return dir != null && dir.isAscending() ? FULL_BLOCK_AABB : FLAT_AABB;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP);
  }

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    if (!worldIn.isRemote) {
      state = updateDir(worldIn, pos, state, true);
    }
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!worldIn.isRemote) {
      EnumRailDirection dir = state.getValue(SHAPE);
      boolean shouldRemove = false;

      if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
        shouldRemove = true;

      if (dir == EnumRailDirection.ASCENDING_EAST && !worldIn.getBlockState(pos.east()).isSideSolid(worldIn, pos.east(), EnumFacing.UP))
        shouldRemove = true;
      else if (dir == EnumRailDirection.ASCENDING_WEST && !worldIn.getBlockState(pos.west()).isSideSolid(worldIn, pos.west(), EnumFacing.UP))
        shouldRemove = true;
      else if (dir == EnumRailDirection.ASCENDING_NORTH && !worldIn.getBlockState(pos.north()).isSideSolid(worldIn, pos.north(), EnumFacing.UP))
        shouldRemove = true;
      else if (dir == EnumRailDirection.ASCENDING_SOUTH && !worldIn.getBlockState(pos.south()).isSideSolid(worldIn, pos.south(), EnumFacing.UP))
        shouldRemove = true;

      if (shouldRemove && !worldIn.isAirBlock(pos)) {
        dropBlockAsItem(worldIn, pos, state, 0);
        worldIn.setBlockToAir(pos);
      }
    }
  }

  protected IBlockState updateDir(World worldIn, BlockPos pos, IBlockState state, boolean b) {
    return worldIn.isRemote ? state : new Rope(worldIn, pos, state).place(false, true).getBlockState();
  }

  @Override
  public EnumPushReaction getMobilityFlag(IBlockState state) {
    return EnumPushReaction.NORMAL;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    super.breakBlock(worldIn, pos, state);

    if (state.getValue(SHAPE).isAscending()) {
      worldIn.notifyNeighborsOfStateChange(pos.up(), this);
    }
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(SHAPE, EnumRailDirection.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(SHAPE).getMetadata();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, SHAPE);
  }

  public EnumRailDirection getRopeDirection(IBlockAccess world, BlockPos pos, IBlockState state, @Nullable EntityMinecart cart) {
    return state.getValue(SHAPE);
  }

  /**
   * Rotate the block. For vanilla blocks this rotates around the axis passed in (generally, it
   * should be the "face" that was hit). Note: for mod blocks, this is up to the block and modder to
   * decide. It is not mandated that it be a rotation around the face, but could be a rotation to
   * orient *to* that face, or a visiting of possible rotations. The method should return true if
   * the rotation was successful though.
   *
   * @param world The world
   * @param pos Block position in world
   * @param axis The axis to rotate around
   * @return True if the rotation was successful, False if the rotation failed, or is not possible
   */
  @Override
  public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
    IBlockState state = world.getBlockState(pos);
    for (IProperty<?> prop : state.getProperties().keySet()) {
      if (prop.getName().equals("shape")) {
        world.setBlockState(pos, state.cycleProperty(prop));
        return true;
      }
    }
    return false;
  }

  @Override
  @SuppressWarnings("incomplete-switch")
  public IBlockState withRotation(IBlockState state, Rotation rot) {
    switch (rot) {
      case CLOCKWISE_180:
        switch ((EnumRailDirection) state.getValue(SHAPE)) {
          case ASCENDING_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_WEST);
          case ASCENDING_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_EAST);
          case ASCENDING_NORTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_SOUTH);
          case ASCENDING_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_NORTH);
          case SOUTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_WEST);
          case SOUTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_EAST);
          case NORTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_EAST);
          case NORTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_WEST);
        }
      case COUNTERCLOCKWISE_90:
        switch ((EnumRailDirection) state.getValue(SHAPE)) {
          case ASCENDING_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_NORTH);
          case ASCENDING_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_SOUTH);
          case ASCENDING_NORTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_WEST);
          case ASCENDING_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_EAST);
          case SOUTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_EAST);
          case SOUTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_EAST);
          case NORTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_WEST);
          case NORTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_WEST);
          case NORTH_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.EAST_WEST);
          case EAST_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH);
        }
      case CLOCKWISE_90:
        switch ((EnumRailDirection) state.getValue(SHAPE)) {
          case ASCENDING_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_SOUTH);
          case ASCENDING_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_NORTH);
          case ASCENDING_NORTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_EAST);
          case ASCENDING_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_WEST);
          case SOUTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_WEST);
          case SOUTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_WEST);
          case NORTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_EAST);
          case NORTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_EAST);
          case NORTH_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.EAST_WEST);
          case EAST_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_SOUTH);
        }
      default:
        return state;
    }
  }

  @Override
  @SuppressWarnings({"incomplete-switch", "deprecation"})
  public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    EnumRailDirection dir = (EnumRailDirection) state.getValue(SHAPE);

    switch (mirrorIn) {
      case LEFT_RIGHT:
        switch (dir) {
          case ASCENDING_NORTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_SOUTH);
          case ASCENDING_SOUTH:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_NORTH);
          case SOUTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_EAST);
          case SOUTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_WEST);
          case NORTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_WEST);
          case NORTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_EAST);
          default:
            return super.withMirror(state, mirrorIn);
        }
      case FRONT_BACK:
        switch (dir) {
          case ASCENDING_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_WEST);
          case ASCENDING_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.ASCENDING_EAST);
          case ASCENDING_NORTH:
          case ASCENDING_SOUTH:
          default:
            break;
          case SOUTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_WEST);
          case SOUTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.SOUTH_EAST);
          case NORTH_WEST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_EAST);
          case NORTH_EAST:
            return state.withProperty(SHAPE, EnumRailDirection.NORTH_WEST);
        }
    }
    return super.withMirror(state, mirrorIn);
  }

  public class Rope {
    private final World world;
    private final BlockPos pos;
    private IBlockState state;
    private final List<BlockPos> connectedRopes = Lists.newArrayList();

    public Rope(World worldIn, BlockPos pos, IBlockState state) {
      this.world = worldIn;
      this.pos = pos;
      this.state = state;
      updateConnectedRopes(((BlockRope) state.getBlock()).getRopeDirection(worldIn, pos, state, null));
    }

    public List<BlockPos> getConnectedRopes() {
      return this.connectedRopes;
    }

    private void updateConnectedRopes(EnumRailDirection dir) {
      this.connectedRopes.clear();

      switch (dir) {
        case NORTH_SOUTH:
          this.connectedRopes.add(this.pos.north());
          this.connectedRopes.add(this.pos.south());
          break;
        case EAST_WEST:
          this.connectedRopes.add(this.pos.west());
          this.connectedRopes.add(this.pos.east());
          break;
        case ASCENDING_EAST:
          this.connectedRopes.add(this.pos.west());
          this.connectedRopes.add(this.pos.east().up());
          break;
        case ASCENDING_WEST:
          this.connectedRopes.add(this.pos.west().up());
          this.connectedRopes.add(this.pos.east());
          break;
        case ASCENDING_NORTH:
          this.connectedRopes.add(this.pos.north().up());
          this.connectedRopes.add(this.pos.south());
          break;
        case ASCENDING_SOUTH:
          this.connectedRopes.add(this.pos.north());
          this.connectedRopes.add(this.pos.south().up());
          break;
        case SOUTH_EAST:
          this.connectedRopes.add(this.pos.east());
          this.connectedRopes.add(this.pos.south());
          break;
        case SOUTH_WEST:
          this.connectedRopes.add(this.pos.west());
          this.connectedRopes.add(this.pos.south());
          break;
        case NORTH_WEST:
          this.connectedRopes.add(this.pos.west());
          this.connectedRopes.add(this.pos.north());
          break;
        case NORTH_EAST:
          this.connectedRopes.add(this.pos.east());
          this.connectedRopes.add(this.pos.north());
      }
    }

    private void removeSoftConnections() {
      for (int i = 0; i < this.connectedRopes.size(); ++i) {
        Rope rope = findRopeAt(this.connectedRopes.get(i));

        if (rope != null && rope.isConnectedToRope(this)) {
          this.connectedRopes.set(i, rope.pos);
        }
        else {
          this.connectedRopes.remove(i--);
        }
      }
    }

    private boolean hasRopeAt(BlockPos pos) {
      return BlockRope.isRopeBlock(this.world, pos) || BlockRope.isRopeBlock(this.world, pos.up()) || BlockRope.isRopeBlock(this.world, pos.down());
    }

    private Rope findRopeAt(BlockPos pos) {
      IBlockState state = this.world.getBlockState(pos);

      if (BlockRope.isRopeBlock(state)) {
        return new Rope(this.world, pos, state);
      }
      else {
        BlockPos posUp = pos.up();
        state = this.world.getBlockState(posUp);

        if (BlockRope.isRopeBlock(state)) {
          return new Rope(this.world, posUp, state);
        }
        else {
          posUp = pos.down();
          state = this.world.getBlockState(posUp);
          return BlockRope.isRopeBlock(state) ? new Rope(this.world, posUp, state) : null;
        }
      }
    }

    private boolean isConnectedToRope(Rope rope) {
      return this.isConnectedTo(rope.pos);
    }

    private boolean isConnectedTo(BlockPos posIn) {
      for (int i = 0; i < this.connectedRopes.size(); ++i) {
        BlockPos blockpos = this.connectedRopes.get(i);

        if (blockpos.getX() == posIn.getX() && blockpos.getZ() == posIn.getZ()) {
          return true;
        }
      }

      return false;
    }

    protected int countAdjacentRopes() {
      int i = 0;

      for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
        if (hasRopeAt(this.pos.offset(enumfacing))) {
          ++i;
        }
      }

      return i;
    }

    private boolean canConnectTo(Rope rail) {
      return isConnectedToRope(rail) || this.connectedRopes.size() != 2;
    }

    private void connectTo(Rope rope) {
      this.connectedRopes.add(rope.pos);
      BlockPos north = this.pos.north();
      BlockPos south = this.pos.south();
      BlockPos west = this.pos.west();
      BlockPos east = this.pos.east();
      boolean connectedNorth = isConnectedTo(north);
      boolean connectedSouth = isConnectedTo(south);
      boolean connectedWest = isConnectedTo(west);
      boolean connectedEast = isConnectedTo(east);
      EnumRailDirection dir = null;

      if (connectedNorth || connectedSouth)
        dir = EnumRailDirection.NORTH_SOUTH;
      if (connectedWest || connectedEast)
        dir = EnumRailDirection.EAST_WEST;

      if (connectedSouth && connectedEast && !connectedNorth && !connectedWest)
        dir = EnumRailDirection.SOUTH_EAST;
      if (connectedSouth && connectedWest && !connectedNorth && !connectedEast)
        dir = EnumRailDirection.SOUTH_WEST;
      if (connectedNorth && connectedWest && !connectedSouth && !connectedEast)
        dir = EnumRailDirection.NORTH_WEST;
      if (connectedNorth && connectedEast && !connectedSouth && !connectedWest)
        dir = EnumRailDirection.NORTH_EAST;

      if (dir == EnumRailDirection.NORTH_SOUTH) {
        if (BlockRope.isRopeBlock(this.world, north.up()))
          dir = EnumRailDirection.ASCENDING_NORTH;
        if (BlockRope.isRopeBlock(this.world, south.up()))
          dir = EnumRailDirection.ASCENDING_SOUTH;
      }

      if (dir == EnumRailDirection.EAST_WEST) {
        if (BlockRope.isRopeBlock(this.world, east.up()))
          dir = EnumRailDirection.ASCENDING_EAST;
        if (BlockRope.isRopeBlock(this.world, west.up()))
          dir = EnumRailDirection.ASCENDING_WEST;
      }

      if (dir == null)
        dir = EnumRailDirection.NORTH_SOUTH;

      this.state = this.state.withProperty(BlockRope.SHAPE, dir);
      this.world.setBlockState(this.pos, this.state, 3);
    }

    private boolean hasNeighborRope(BlockPos pos) {
      Rope rail = findRopeAt(pos);

      if (rail == null) {
        return false;
      }
      else {
        rail.removeSoftConnections();
        return rail.canConnectTo(this);
      }
    }

    public Rope place(boolean b1, boolean b2) {
      BlockPos north = this.pos.north();
      BlockPos south = this.pos.south();
      BlockPos west = this.pos.west();
      BlockPos east = this.pos.east();
      boolean neighborNorth = hasNeighborRope(north);
      boolean neighborSouth = hasNeighborRope(south);
      boolean neighborWest = hasNeighborRope(west);
      boolean neighborEast = hasNeighborRope(east);
      EnumRailDirection dir = null;

      if ((neighborNorth || neighborSouth) && !neighborWest && !neighborEast)
        dir = EnumRailDirection.NORTH_SOUTH;
      if ((neighborWest || neighborEast) && !neighborNorth && !neighborSouth)
        dir = EnumRailDirection.EAST_WEST;

      if (neighborSouth && neighborEast && !neighborNorth && !neighborWest)
        dir = EnumRailDirection.SOUTH_EAST;
      if (neighborSouth && neighborWest && !neighborNorth && !neighborEast)
        dir = EnumRailDirection.SOUTH_WEST;
      if (neighborNorth && neighborWest && !neighborSouth && !neighborEast)
        dir = EnumRailDirection.NORTH_WEST;
      if (neighborNorth && neighborEast && !neighborSouth && !neighborWest)
        dir = EnumRailDirection.NORTH_EAST;

      if (dir == null) {
        if (neighborNorth || neighborSouth)
          dir = EnumRailDirection.NORTH_SOUTH;
        if (neighborWest || neighborEast)
          dir = EnumRailDirection.EAST_WEST;

        if (neighborNorth && neighborWest)
          dir = EnumRailDirection.NORTH_WEST;
        if (neighborEast && neighborNorth)
          dir = EnumRailDirection.NORTH_EAST;
        if (neighborWest && neighborSouth)
          dir = EnumRailDirection.SOUTH_WEST;
        if (neighborSouth && neighborEast)
          dir = EnumRailDirection.SOUTH_EAST;
      }

      if (dir == EnumRailDirection.NORTH_SOUTH) {
        if (BlockRope.isRopeBlock(this.world, north.up()))
          dir = EnumRailDirection.ASCENDING_NORTH;
        if (BlockRope.isRopeBlock(this.world, south.up()))
          dir = EnumRailDirection.ASCENDING_SOUTH;
      }

      if (dir == EnumRailDirection.EAST_WEST) {
        if (BlockRope.isRopeBlock(this.world, east.up()))
          dir = EnumRailDirection.ASCENDING_EAST;
        if (BlockRope.isRopeBlock(this.world, west.up()))
          dir = EnumRailDirection.ASCENDING_WEST;
      }

      if (dir == null)
        dir = EnumRailDirection.NORTH_SOUTH;

      updateConnectedRopes(dir);
      this.state = this.state.withProperty(BlockRope.SHAPE, dir);

      if (b2 || this.world.getBlockState(this.pos) != this.state) {
        this.world.setBlockState(this.pos, this.state, 3);

        for (int i = 0; i < this.connectedRopes.size(); ++i) {
          Rope rope = findRopeAt(this.connectedRopes.get(i));

          if (rope != null) {
            rope.removeSoftConnections();

            if (rope.canConnectTo(this)) {
              rope.connectTo(this);
            }
          }
        }
      }

      return this;
    }

    public IBlockState getBlockState() {
      return this.state;
    }
  }
}
