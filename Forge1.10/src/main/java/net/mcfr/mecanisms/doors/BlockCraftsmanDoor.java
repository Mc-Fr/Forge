package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemDoor;

/**
 * Classe de base pour les portes artisant.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanDoor extends McfrBlockDoor {
  private BlockPlanks.EnumType type;
  
  public BlockCraftsmanDoor(BlockPlanks.EnumType type) {
    super("craftsman_" + type.getName(), Material.WOOD, SoundType.WOOD, 6, 0, "axe", 0);
    this.type = type;
  }
  
  @Override
  protected ItemDoor getItemToDrop() {
    switch (this.type) {
    case ACACIA:
      return McfrItems.CRAFTSMAN_ACACIA_DOOR;
    case BIRCH:
      return McfrItems.CRAFTSMAN_BIRCH_DOOR;
    case DARK_OAK:
      return McfrItems.CRAFTSMAN_DARK_OAK_DOOR;
    case JUNGLE:
      return McfrItems.CRAFTSMAN_JUNGLE_DOOR;
    case SPRUCE:
      return McfrItems.CRAFTSMAN_SPRUCE_DOOR;
    default:
      return McfrItems.CRAFTSMAN_OAK_DOOR;
    }
  }
}
