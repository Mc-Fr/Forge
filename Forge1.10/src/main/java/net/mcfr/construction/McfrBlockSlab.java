package net.mcfr.construction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

/**
 * Classe de base des dalles du mod.
 * 
 * @author Mc-Fr
 *
 * @param <T> le type des variantes
 */
public abstract class McfrBlockSlab<T extends Enum<T> & IStringSerializable> extends BlockSlab implements IBlockWithVariants {
  /** La classe des variantes. */
  private final Class<T> clazz;

  /**
   * Crée une nouvelle dalle.
   * 
   * @param name le nom (sans le suffixe '_slab')
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel la niveau de récolte
   * @param clazz la classe des variantes
   */
  public McfrBlockSlab(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, Class<T> clazz) {
    this(name, null, material, sound, hardness, resistance, tool, harvestLevel, clazz);
  }

  /**
   * Crée une nouvelle dalle.
   * 
   * @param name le nom (sans le suffixe '_slab')
   * @param material le matériau
   * @param suffix le suffixe (ex: '2' comme dans 'stone_slab2')
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel la niveau de récolte
   * @param clazz la classe des variantes
   */
  public McfrBlockSlab(String name, String suffix, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, Class<T> clazz) {
    super(material);
    this.clazz = clazz;
    name = (isDouble() ? "double_" : "") + name + "_slab" + (suffix != null ? suffix : "");
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
    setSoundType(sound);
    if (!isDouble())
      setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    IBlockState state = this.blockState.getBaseState();
    if (!isDouble())
      state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
    setDefaultState(state);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    if (isDouble())
      return getDefaultState();
    return getDefaultState().withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    if (isDouble())
      return 0;
    return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
  }

  @Override
  public final void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < this.clazz.getEnumConstants().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public final Comparable<?> getTypeForItem(ItemStack stack) {
    try {
      Method valuesMethod = this.clazz.getMethod("values");
      @SuppressWarnings("unchecked")
      T[] values = (T[]) valuesMethod.invoke(null);

      return values[(stack.getMetadata() & 7) % values.length];
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
