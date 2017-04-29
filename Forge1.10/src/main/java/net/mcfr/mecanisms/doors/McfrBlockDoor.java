package net.mcfr.mecanisms.doors;

import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Classe de base des portes.
 *
 * @author Mc-Fr
 */
public abstract class McfrBlockDoor extends BlockDoor {
  /**
   * Crée une porte.
   * 
   * @param name le nom
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockDoor(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(material);
    name = name + "_door_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
    setSoundType(sound);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(getItemToDrop());
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : getItemToDrop();
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(getItemToDrop());
  }

  /**
   * @return l'item à lacher lorsque la porte est détruite
   */
  protected abstract ItemDoor getItemToDrop();

  /**
   * @return l'item correspondant à cette porte
   */
  public McfrItemDoor getItem() {
    String name = getRegistryName().getResourcePath();
    return new McfrItemDoor(name.substring(0, name.indexOf("_block")), this);
  }
}
