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

public abstract class McfrBlockSlab<T extends Enum<T> & IStringSerializable> extends BlockSlab implements IBlockWithVariants {
  private final Class<T> clazz;

  public McfrBlockSlab(String materialName, Material materialIn, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, Class<T> clazz) {
    this(materialName, null, materialIn, sound, hardness, resistance, tool, harvestLevel, clazz);
  }

  public McfrBlockSlab(String materialName, String suffix, Material materialIn, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, Class<T> clazz) {
    super(materialIn);
    this.clazz = clazz;
    materialName = (isDouble() ? "double_" : "") + materialName + "_slab" + (suffix != null ? suffix : "");
    setRegistryName(materialName);
    setUnlocalizedName(NameUtils.getUnlocalizedName(materialName));
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
    Exception ex = null;

    try {
      Method valuesMethod = this.clazz.getMethod("values");
      @SuppressWarnings("unchecked")
      T[] values = (T[]) valuesMethod.invoke(null);

      return values[(stack.getMetadata() & 7) % values.length];
    }
    catch (NoSuchMethodException e) {
      ex = e;
    }
    catch (SecurityException e) {
      ex = e;
    }
    catch (IllegalAccessException e) {
      ex = e;
    }
    catch (IllegalArgumentException e) {
      ex = e;
    }
    catch (InvocationTargetException e) {
      ex = e;
    }

    throw new RuntimeException(ex);
  }
}
