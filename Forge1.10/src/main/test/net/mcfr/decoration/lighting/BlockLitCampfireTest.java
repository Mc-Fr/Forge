package net.mcfr.decoration.lighting;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.mojang.authlib.GameProfile;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.mcfr.internal.McfrAbstractTest;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class BlockLitCampfireTest extends McfrAbstractTest {
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
  public void testLitCampfireStage0WithStick() {
    ItemStack is = new ItemStack(Items.STICK, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(0));
    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }
  
  @Test
  public void testLitCampfireStage1WithStick() {
    ItemStack is = new ItemStack(Items.STICK, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(1));
    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(BlockLitCampFire.MAX_STAGE, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }
  
  @Test
  public void testLitCampfireWith64Sticks() {
    ItemStack is = new ItemStack(Items.STICK, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(60, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(4, getItemStackFromInventory(this.player.inventory.mainInventory, Blocks.TORCH).stackSize);
  }
  
  @Test
  public void testLitCampfireWith2Sticks() {
    ItemStack is = new ItemStack(Items.STICK, 2);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(0, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(2, getItemStackFromInventory(this.player.inventory.mainInventory, Blocks.TORCH).stackSize);
  }

  @Test
  public void testLitCampfireWithPorkchop() {
    ItemStack is = new ItemStack(Items.PORKCHOP, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_PORKCHOP).stackSize);
  }

  @Test
  public void testLitCampfireWithBeef() {
    ItemStack is = new ItemStack(Items.BEEF, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_BEEF).stackSize);
  }

  @Test
  public void testLitCampfireWithChicken() {
    ItemStack is = new ItemStack(Items.CHICKEN, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_CHICKEN).stackSize);
  }

  @Test
  public void testLitCampfireWithFish() {
    ItemStack is = new ItemStack(Items.FISH, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    ItemStack result = getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_FISH);
    assertEquals(1, result.stackSize);
    assertEquals(0, result.getMetadata());
  }

  @Test
  public void testLitCampfireWithSalmon() {
    ItemStack is = new ItemStack(Items.FISH, 64, 1);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    ItemStack result = getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_FISH);
    assertEquals(1, result.stackSize);
    assertEquals(1, result.getMetadata());
  }

  @Test
  public void testLitCampfireWithSardine() {
    ItemStack is = new ItemStack(McfrItems.RAW_SARDINE, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, McfrItems.COOKED_SARDINE).stackSize);
  }
  
  @Test
  public void testLitCampfireWithSwordfish() {
    ItemStack is = new ItemStack(McfrItems.RAW_SWORDFISH, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, McfrItems.COOKED_SWORDFISH).stackSize);
  }

  @Test
  public void testLitCampfireWithPotato() {
    ItemStack is = new ItemStack(Items.POTATO, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.BAKED_POTATO).stackSize);
  }

  @Test
  public void testLitCampfireWithRabbit() {
    ItemStack is = new ItemStack(Items.RABBIT, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_RABBIT).stackSize);
  }

  @Test
  public void testLitCampfireWithMutton() {
    ItemStack is = new ItemStack(Items.MUTTON, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(63, is.stackSize);
    assertEquals(McfrBlocks.CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(1, getItemStackFromInventory(this.player.inventory.mainInventory, Items.COOKED_MUTTON).stackSize);
  }
  
  @Test
  public void testLitCampfireWithNoCookableItem() {
    ItemStack is = new ItemStack(Items.FEATHER, 64);
    this.world.setBlockState(this.pos, McfrBlocks.LIT_CAMPFIRE.getStateFromMeta(BlockLitCampFire.MAX_STAGE));

    assertTrue(McfrBlocks.LIT_CAMPFIRE.onBlockActivated(this.world, this.pos, this.world.getBlockState(this.pos), this.player, EnumHand.MAIN_HAND, is,
        EnumFacing.UP, 0, 0, 0));
    assertEquals(64, is.stackSize);
    assertEquals(McfrBlocks.LIT_CAMPFIRE, this.world.getBlockState(this.pos).getBlock());
    assertEquals(BlockLitCampFire.MAX_STAGE, this.world.getBlockState(this.pos).getProperties().get(BlockLitCampFire.STAGE));
  }
  
  private ItemStack getItemStackFromInventory(ItemStack[] inventory, Block block) {
    return getItemStackFromInventory(inventory, Item.getItemFromBlock(block));
  }
  
  private ItemStack getItemStackFromInventory(ItemStack[] inventory, Item item) {
    for (ItemStack isTmp : inventory) {
      if (isTmp.getItem().equals(item))
        return isTmp;
    }

    return null;
  }
}
