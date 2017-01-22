package net.mcfr.decoration.lighting;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.mojang.authlib.GameProfile;

import net.mcfr.McfrBlocks;
import net.mcfr.internal.McfrAbstractTest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class BlockCampfireTest extends McfrAbstractTest {
  
  private BlockPos pos;
  private EntityPlayer player;
  
  @Override
  @Before
  public void setUp() {
    super.setUp();
    this.pos = new BlockPos(0, 64, 0);
    this.player = new EntityPlayerMP(game.getServer(), game.worldServerForDimension(0), new GameProfile(new UUID(1, 1), "PlayerTest"),
        new PlayerInteractionManager(game.getServer().worldServerForDimension(0)));
  }
  
  @Test
  public void testCampfireWithFlintAndSteel() {
    ItemStack is = new ItemStack(Items.FLINT_AND_STEEL);
    assertTrue(McfrBlocks.CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(1, is.getItemDamage());
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(BlockLitCampFire.MAX_STAGE, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }

  @Test
  public void testCampfireWithCoal() {
    ItemStack is = new ItemStack(Items.COAL, 64);
    assertTrue(McfrBlocks.CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(BlockLitCampFire.MAX_STAGE, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }
  
  @Test
  public void testCampfireWithStick() {
    ItemStack is = new ItemStack(Items.STICK, 64);
    assertTrue(McfrBlocks.CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(0, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }
}
