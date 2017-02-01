package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tileEntities.TileEntityWoodenStool;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStool extends BlockWoodenChair {
  private boolean isTall;

  public BlockStool(EnumType type, boolean isTall) {
    super(type, (isTall ? "tall_" : "") + "stool");
    this.isTall = isTall;
  }

  public boolean isTall() {
    return this.isTall;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenStool(getStateFromMeta(meta).getValue(FACING), getType(), isTall());
  }
}
