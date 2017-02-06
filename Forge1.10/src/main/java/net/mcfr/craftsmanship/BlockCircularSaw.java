package net.mcfr.craftsmanship;

import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSaw;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public class BlockCircularSaw extends BlockRack<TileEntityCircularSaw> {
  public BlockCircularSaw() {
    super("circular_saw", TileEntityCircularSaw.class);
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.SAW;
  }
}
