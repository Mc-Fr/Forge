package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenChair;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Chaise en bois.
 *
 * @author Mc-Fr
 */
public class BlockWoodenChair extends BlockChair {
  /** Le type de bois */
  private final BlockPlanks.EnumType type;

  /**
   * Crée une chaise en bois.
   * 
   * @param type le type de bois
   * @param seatType le type de meuble (chaise, tabouret...)
   */
  public BlockWoodenChair(BlockPlanks.EnumType type, String seatType) {
    super(type.getName() + "_" + seatType, Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", 0);
    this.type = type;
  }

  /**
   * @return le type de bois
   */
  public BlockPlanks.EnumType getType() {
    return this.type;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenChair(getStateFromMeta(meta).getValue(FACING), getType());
  }
}
