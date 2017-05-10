package net.mcfr.decoration.signs;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.decoration.signs.tile_entities.TileEntityTombstone;
import net.minecraft.block.Block;
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
import net.minecraft.world.World;

/**
 * Pierre tombale.
 *
 * @author Mc-Fr
 */
public class BlockTombstone extends McfrBlockStandingSign {
  public BlockTombstone() {
    super("tombstone_block", Material.ROCK, SoundType.STONE, 2, "pickaxe");
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return getBoundingBox(state, worldIn, pos);
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!worldIn.getBlockState(pos.down()).getMaterial().isSolid()) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityTombstone) {
        TileEntityTombstone t = (TileEntityTombstone) te;
        int firstLineIndex = 0;
        int lastLineIndex = 14;

        // On supprime les lignes vides en début et en fin d'épitaphe.
        while (firstLineIndex < 15 && t.getText()[firstLineIndex].getUnformattedText().equals("§r"))
          firstLineIndex++;
        while (lastLineIndex > 0 && t.getText()[lastLineIndex].getUnformattedText().equals("§r"))
          lastLineIndex--;

        for (int i = firstLineIndex; i <= lastLineIndex; i++)
          playerIn.addChatComponentMessage(t.getText()[i]);

        return true;
      }
    }

    return false;
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
