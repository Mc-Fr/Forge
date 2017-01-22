package net.mcfr.decoration.containerBlocks;

import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityCrate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockCrate extends McfrBlockContainer<TileEntityCrate> {
  public BlockCrate() {
    super("crate", Material.WOOD, SoundType.WOOD, 2, 5, "axe", TileEntityCrate.class);
  }

  @Override
  public TileEntityCrate createNewTileEntity(World worldIn, int meta) {
    return new TileEntityCrate();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.CRATE;
  }
}
