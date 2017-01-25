package net.mcfr.decoration.signs;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockWallNote extends McfrBlockWallSign {
  public BlockWallNote() {
    super("wall_note_block", Material.WOOD, SoundType.WOOD, 0, null);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityWallNote) {
        playerIn.addChatComponentMessage(new TextComponentString(((TileEntityWallNote) te).getText()));
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
