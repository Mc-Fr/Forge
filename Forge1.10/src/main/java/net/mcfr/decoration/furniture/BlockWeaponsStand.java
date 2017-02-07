package net.mcfr.decoration.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.decoration.furniture.tile_entities.TileEntityShowcase;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWeaponsStand;
import net.mcfr.utils.FacingUtils;
import net.mcfr.utils.NameUtils;
import net.mcfr.utils.TileEntityUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Stand d'armes et outils.
 *
 * @author Mc-Fr
 */
public class BlockWeaponsStand extends BlockContainer {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  public BlockWeaponsStand() {
    super(Material.WOOD);
    String name = "weapons_stand_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(5);
    setHardness(2);
    setSoundType(SoundType.WOOD);
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
  public EnumBlockRenderType getRenderType(IBlockState state) {
    // TEMP
    return EnumBlockRenderType.MODEL; // EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, FacingUtils.getHorizontalFacing(placer));
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityWeaponsStand) {
        return interact(playerIn, (TileEntityWeaponsStand) te, getSlot(state.getValue(FACING), hitX, hitZ));
      }
    }

    return false;
  }

  /**
   * Retourne le slot avec lequel interagir.
   *
   * @param facing l'orientation du bloc
   * @param hitX la position en x du clic
   * @param hitZ la position en z du clic
   * @return le slot
   */
  private int getSlot(EnumFacing facing, float hitX, float hitZ) {
    switch (facing) {
      case NORTH:
        if (hitX < 0.5f)
          return 1;
        return 0;
      case EAST:
        if (hitZ < 0.5f)
          return 1;
        return 0;
      case SOUTH:
        if (hitX < 0.5f)
          return 0;
        return 1;
      case WEST:
        if (hitZ < 0.5f)
          return 0;
        return 1;
      case UP:
      case DOWN:
      default:
        throw new IllegalArgumentException("" + facing);
    }
  }

  /**
   * Met le bloc à jour.
   * 
   * @param player le joueur
   * @param te la tile entity
   * @param slot l'emplacement
   * @return true si l'interaction a été prise en compte
   */
  private boolean interact(EntityPlayer player, TileEntityWeaponsStand te, int slot) {
    if (te.hasItem(slot) && player.getHeldItemMainhand() == null) {
      player.inventory.setInventorySlotContents(player.inventory.currentItem, te.getItem(slot));
      te.setItem(null, slot);
      TileEntityUtils.sendTileEntityUpdate(te.getWorld(), te);

      return true;
    }
    else if (!te.hasItem(slot) && player.getHeldItemMainhand() != null && TileEntityShowcase.itemIsValid(player.getHeldItemMainhand())) {
      te.setItem(player.getHeldItemMainhand(), slot);
      player.getHeldItemMainhand().stackSize--;
      TileEntityUtils.sendTileEntityUpdate(te.getWorld(), te);

      return true;
    }

    return false;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity te = worldIn.getTileEntity(pos);

    if (te instanceof TileEntityWeaponsStand) {
      TileEntityWeaponsStand t = (TileEntityWeaponsStand) te;

      for (int i = 0; i < TileEntityWeaponsStand.INVENTORY_SIZE; i++) {
        ItemStack stack = t.getItem(i);

        if (stack != null) {
          EntityItem loot = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);

          loot.setPickupDelay(10);
          worldIn.spawnEntityInWorld(loot);
        }
      }
    }

    super.breakBlock(worldIn, pos, state);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.WEAPONS_STAND;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 3]);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWeaponsStand(getStateFromMeta(meta).getValue(FACING));
  }
}
