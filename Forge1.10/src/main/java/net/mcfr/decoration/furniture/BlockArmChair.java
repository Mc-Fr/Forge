package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tile_entities.TileEntityArmChair;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

/**
 * Fauteuil.
 *
 * @author Mc-Fr
 */
public class BlockArmChair extends BlockChair {
  /** Le type de fauteuil */
  private final String type;

  /**
   * Crée un nouveau fauteuil.
   * 
   * @param type le type
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public BlockArmChair(String type, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(type + "_armchair", material, sound, hardness, resistance, tool, harvestLevel);
    this.type = type;
  }

  /**
   * @return le type de fauteuil
   */
  public String getType() {
    return this.type;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityArmChair(getStateFromMeta(meta).getValue(FACING), getType());
  }
}
