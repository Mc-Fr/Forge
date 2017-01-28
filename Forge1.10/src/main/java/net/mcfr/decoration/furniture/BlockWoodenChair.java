package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tileEntities.TileEntityWoodenChair;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWoodenChair extends BlockChair {
  private final BlockPlanks.EnumType type;

  public BlockWoodenChair(BlockPlanks.EnumType type, String seatType) {
    super(type.getName() + "_" + seatType, Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", 0);
    this.type = type;
  }

  public BlockPlanks.EnumType getType() {
    return this.type;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenChair(getStateFromMeta(meta).getValue(FACING), getType());
  }
}
