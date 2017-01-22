package net.mcfr;

import static org.junit.Assert.*;

import org.junit.Test;

import net.mcfr.internal.McfrAbstractTest;
import net.minecraft.util.math.BlockPos;

/**
 * Tests d'intégration du mod <i>Mc-Fr Blocs et Items</i>.
 *
 * @author Mc-Fr
 */
public class McfrTests extends McfrAbstractTest {

  @Test
  public void testServerInstanceNotNull() {
    assertNotNull(game);
  }

  @Test
  public void testBlockState() {
    BlockPos pos = new BlockPos(0, 0, 0);

    this.world.setBlockState(pos, McfrBlocks.CHANGER.getDefaultState());
    assertEquals(McfrBlocks.CHANGER, this.world.getBlockState(pos).getBlock());
  }
  
}
