package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tileEntities.TileEntityArmChair;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmChair extends BlockChair {
  private final String type;

  public BlockArmChair(String type, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(type + "_armchair", material, sound, hardness, resistance, tool, harvestLevel);
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityArmChair(getStateFromMeta(meta).getValue(FACING), getType());
  }
}
