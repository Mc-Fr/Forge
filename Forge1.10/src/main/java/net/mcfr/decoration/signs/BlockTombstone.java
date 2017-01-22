package net.mcfr.decoration.signs;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.decoration.signs.tileEntities.TileEntityTombstone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

// Pas besoin d'afficher le texte.
public class BlockTombstone extends McfrBlockStandingSign {
  public BlockTombstone() {
    super(Material.ROCK, "tombstone_block");
    setSoundType(SoundType.STONE);
  }
  
  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return getBoundingBox(state, worldIn, pos);
  }
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    TileEntity tileEntity = worldIn.getTileEntity(pos);
    
    if (tileEntity instanceof TileEntityTombstone) {
      for (ITextComponent component : ((TileEntityTombstone) tileEntity).signText) {
        if (!"".equals(component.getUnformattedText())) {
          playerIn.addChatMessage(new TextComponentString(TextFormatting.GRAY + component.getFormattedText()));
        }
      }
    }
    
    return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
  }
  
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.TOMBSTONE;
  }
  
  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.TOMBSTONE);
  }
  
  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityTombstone();
  }
}
