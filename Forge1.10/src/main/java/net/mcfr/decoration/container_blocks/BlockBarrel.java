package net.mcfr.decoration.container_blocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrBlocks;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBarrel;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBarrelSaver;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Classe représentant un tonneau. Chaque tonneau non vide peut vieillir en fonction de son
 * l'environnement et a un nombre d'utilisations limité. L'age est indiqué par la propriété AGE
 * allant de 0 à 7 et le nombre d'utilisations est stocké dans la tile entity associée.<br/>
 * Le metadata de l'item associé indique l'age du tonneau et sa durabilité indique le nombre
 * d'utilisations.
 *
 * @author Mc-Fr
 */
public abstract class BlockBarrel extends BlockContainer {
  public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
  public static final int MAX_DURABILITY = 7;

  /** Le gestionnaire des tile entities */
  public static final TileEntityBarrelSaver TILE_ENTITY_SAVER = new TileEntityBarrelSaver();

  public BlockBarrel(String name) {
    super(Material.WOOD);
    setRegistryName(name + "_barrel_block");
    setUnlocalizedName(NameUtils.getUnlocalizedName(name + "_barrel"));
    setHardness(2);
    setResistance(5);
    setSoundType(SoundType.WOOD);
    setHarvestLevel("axe", 0);
    setTickRandomly(true);
    setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, AGE);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(AGE);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(AGE, meta & 7);
  }

  @Override
  public TileEntityBarrel createNewTileEntity(World worldIn, int meta) {
    return new TileEntityBarrel();
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  /**
   * @return l'item correspondant
   */
  public abstract Item getItemDropped();

  /**
   * @return le récipient vide
   */
  public abstract Item getEmptyContainer();

  /**
   * @return le récipient plein
   */
  public abstract Item getFullContainer();

  /**
   * @return la profondeur minimale
   */
  public int getMinDepth() {
    return 15;
  }

  /**
   * @return la luminosité optimale
   */
  public int getOptimalLight() {
    return 8;
  }

  /**
   * @return la température optimale
   */
  public float getOptimalTemperature() {
    return 0.8f;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(getItemDropped(), 1, world.getBlockState(pos).getValue(AGE));
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> stacks = new ArrayList<>();
    ItemStack stack = new ItemStack(getItemDropped(), 1, state.getValue(AGE));
    TileEntityBarrel te = TILE_ENTITY_SAVER.get((World) world, pos);

    if (te != null) {
      NBTTagCompound display = new NBTTagCompound();
      NBTTagList lore = new NBTTagList();

      lore.appendTag(new NBTTagString(String.format("§r§7Durabilité : %d/%d", te.getDurability(), MAX_DURABILITY)));
      display.setTag("Lore", lore);
      stack.setTagInfo("display", display);
      stack.setTagInfo("durability", new NBTTagInt(te.getDurability()));

      stacks.add(stack);
      TILE_ENTITY_SAVER.remove((World) world, pos);
    }

    return stacks;
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    TileEntityBarrel tileEntity = createNewTileEntity(worldIn, 0);
    NBTTagCompound compound = stack.getTagCompound();

    tileEntity.setDurability(compound != null ? compound.getInteger("durability") : MAX_DURABILITY);
    tileEntity.validate();

    worldIn.setBlockState(pos, state.withProperty(AGE, stack.getMetadata()));
    worldIn.setTileEntity(pos, tileEntity);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    TileEntityBarrel tileEntity = (TileEntityBarrel) worldIn.getTileEntity(pos);
    ItemStack stack = playerIn.inventory.getCurrentItem();

    if (stack != null) {
      if (stack.getItem() == Items.ENDER_EYE && stack.stackSize == 29) {
        useCheat(worldIn, pos);
      }
      else if (getEmptyContainer() != null && getFullContainer() != null && stack.getItem() == getEmptyContainer() && tileEntity.getDurability() > 0) {
        useEmptyContainer(worldIn, pos, playerIn, stack, tileEntity);
      }
    }

    return true;
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockSlab)
      return;

    boolean updated = false;
    TileEntityBarrel tileEntity = (TileEntityBarrel) worldIn.getTileEntity(pos);

    if (tileEntity == null)
      return;

    long now = new Date().getTime();
    int minute = (int) Math.floor((now - tileEntity.getCreationDate()) / 60 * 1000);
    int age = state.getValue(AGE);

    // Calcul du malus de vieillissement
    int light = worldIn.getLight(pos.up());
    float temperature = worldIn.getBiomeGenForCoords(pos).getTemperature();
    int depth = worldIn.getHeight(pos).getY() - 1 - pos.getY();

    float optimalTemp = getOptimalTemperature(); // -1 à 2
    int optimalLight = getOptimalLight();
    int minDepth = getMinDepth();
    float maxMalus = 6f;

    // S'il y a de la neige à côté on diminue la température de 0.1 / neige
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        for (int k = -1; k <= 1; k++) {
          Material mat = worldIn.getBlockState(pos.add(i, j, k)).getMaterial();

          if (mat == Material.SNOW) {
            temperature -= 0.1;
          }
          if (mat == Material.FIRE || mat == Material.LAVA) {
            temperature += i == 0 && k == 0 && j == -1 ? 0.2 : 0.1;
            if (mat == Material.LAVA) {
              temperature += 0.1;
            }
          }
        }
      }
    }

    float malus = Math.min((float) Math.max(minDepth - depth, 0) / 10 + (float) Math.abs(light - optimalLight) / 10 + Math.abs((optimalTemp - temperature) * 2), maxMalus);

    if (light == 15 || depth <= 1) {
      malus = maxMalus;
    }

    int duration = 120;
    int random = rand.nextInt(100);

    switch (age) {
      case 0:
        if (minute >= duration) {
          if (random <= 40 - malus * 2) {
            age = 1;
            updated = true;
          }
          else {
            tileEntity.setCreationDate(new Date().getTime());
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
          }
        }
        break;
      case 2:
        if (minute >= duration) {
          if (random <= 25 - malus * 2) {
            age = 3;
            updated = true;
          }
          else {
            tileEntity.setCreationDate(new Date().getTime());
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
          }
        }
        break;
      case 4:
        if (minute >= duration * 2) {
          if (random <= 10 - malus * 2) {
            final Random rand2 = new Random();
            if (rand2.nextInt(100) < 10 && malus <= 3) {
              age = 6;
            }
            else {
              age = 5;
            }
            updated = true;
          }
          else {
            tileEntity.setCreationDate(new Date().getTime());
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
          }
        }
        break;
      case 6:
        if (minute >= duration * 4) {
          if (random <= 5) {
            age = 7;
            updated = true;
          }
          else {
            tileEntity.setCreationDate(new Date().getTime());
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
          }
        }
        break;
    }

    if (updated) {
      worldIn.setBlockState(pos, state.withProperty(AGE, age));
      tileEntity.validate();
      worldIn.setTileEntity(pos, tileEntity);
    }
  }

  /**
   * Permet de faire vieillir le tonneau avec 29 Perles du Néant.
   */
  private void useCheat(World worldIn, BlockPos pos) {
    TileEntityBarrel tileEntity = (TileEntityBarrel) worldIn.getTileEntity(pos);
    int age = worldIn.getBlockState(pos).getValue(AGE) + 1;

    if (age <= 7) {
      worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(AGE, age));
      tileEntity.validate();
      worldIn.setTileEntity(pos, tileEntity);
    }
  }

  /**
   * Utilise un récipient vide sur ce tonneau.
   */
  private void useEmptyContainer(World worldIn, BlockPos pos, EntityPlayer playerIn, ItemStack itemstack, TileEntityBarrel tileEntity) {
    InventoryPlayer inv = playerIn.inventory;
    int slot = itemstack.stackSize == 1 ? inv.currentItem : inv.getFirstEmptyStack();

    inv.setInventorySlotContents(slot, new ItemStack(getFullContainer(), 1, worldIn.getBlockState(pos).getValue(AGE)));
    if (itemstack.stackSize != 1)
      itemstack.stackSize--;
    tileEntity.setDurability(tileEntity.getDurability() - 1);
    tileEntity.validate();

    if (tileEntity.getDurability() == 0) {
      worldIn.setBlockState(pos, McfrBlocks.EMPTY_BARREL.getDefaultState());
      worldIn.removeTileEntity(pos);
    }
    else {
      worldIn.setTileEntity(pos, tileEntity);
    }
  }
}
