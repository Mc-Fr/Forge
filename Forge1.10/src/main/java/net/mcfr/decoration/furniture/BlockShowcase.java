package net.mcfr.decoration.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.decoration.furniture.tileEntities.TileEntityShowcase;
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

public class BlockShowcase extends BlockContainer {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  
  public BlockShowcase() {
    super(Material.WOOD);
    String name = "showcase_block";
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
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }
  
  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, FacingUtils.getHorizontalFacing(placer));
  }
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);
      
      if (te instanceof TileEntityShowcase) {
        TileEntityShowcase t = (TileEntityShowcase) te;
        
        if (t.hasItem() && playerIn.getHeldItemMainhand() == null) {
          playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, t.getItem());
          t.setItem(null);
          TileEntityUtils.sendTileEntityUpdate(te.getWorld(), t);
          
          return true;
        }
        else if (!t.hasItem() && playerIn.getHeldItemMainhand() != null && TileEntityShowcase.itemIsValid(playerIn.getHeldItemMainhand())) {
          t.setItem(playerIn.getHeldItemMainhand());
          playerIn.getHeldItemMainhand().stackSize--;
          TileEntityUtils.sendTileEntityUpdate(te.getWorld(), t);
          
          return true;
        }
      }
    }
    
    return false;
  }
  
  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity te = worldIn.getTileEntity(pos);
    
    if (te instanceof TileEntityShowcase) {
      ItemStack stack = ((TileEntityShowcase) te).getItem();
      
      if (stack != null) {
        EntityItem loot = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        
        loot.setPickupDelay(10);
        worldIn.spawnEntityInWorld(loot);
      }
    }
    
    super.breakBlock(worldIn, pos, state);
  }
  
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.SHOWCASE;
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
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 7]);
  }
  
  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityShowcase();
  }
}
