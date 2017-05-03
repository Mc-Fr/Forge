package net.mcfr.decoration.furniture;

import net.mcfr.decoration.container_blocks.McfrBlockContainer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityTableWithFoot;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

/**
 * Table avec un seul pied.
 *
 * @author Mc-Fr
 */
public class BlockTableWithFoot extends McfrBlockContainer<TileEntityTableWithFoot> {
  public BlockTableWithFoot() {
    super("foot_table", Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", TileEntityTableWithFoot.class, CreativeTabs.DECORATIONS);
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
  public TileEntityTableWithFoot createNewTileEntity(World world, int meta) {
    return new TileEntityTableWithFoot();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.FOOT_TABLE;
  }
}
