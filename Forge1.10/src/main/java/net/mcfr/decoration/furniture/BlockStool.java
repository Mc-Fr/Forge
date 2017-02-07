package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenStool;
import net.minecraft.block.BlockPlanks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Tabouret.
 *
 * @author Mc-Fr
 */
public class BlockStool extends BlockWoodenChair {
  /** Le tabouret est grand ou non */
  private boolean isTall;

  /**
   * Crée un tabouret.
   * 
   * @param type le type de bois
   * @param isTall le tabouret est grand ou non
   */
  public BlockStool(BlockPlanks.EnumType type, boolean isTall) {
    super(type, (isTall ? "tall_" : "") + "stool");
    this.isTall = isTall;
  }

  /**
   * @return true si le tabouret est grand
   */
  public boolean isTall() {
    return this.isTall;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenStool(getStateFromMeta(meta).getValue(FACING), getType(), isTall());
  }
}
