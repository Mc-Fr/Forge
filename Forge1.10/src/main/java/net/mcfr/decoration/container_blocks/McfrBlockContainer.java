package net.mcfr.decoration.container_blocks;

import net.mcfr.McfrMain;
import net.mcfr.guis.CustomGuiScreens;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Classe de base des conteneurs du mod.
 * 
 * @author Mc-Fr
 *
 * @param <T> le type de la tile entity
 */
public abstract class McfrBlockContainer<T extends TileEntity & IInventory> extends BlockContainer {
  /** Classe de la tile entity */
  private final Class<T> teClass;

  /**
   * Crée un nouveau conteneur.
   * 
   * @param name le nom
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param teClass la classe de la tile entity
   */
  public McfrBlockContainer(String name, Material material, SoundType sound, float hardness, float resistance, String tool, Class<T> teClass) {
    super(material);
    this.teClass = teClass;
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    setSoundType(sound);
    setHarvestLevel(tool, 0);
    setCreativeTab(CreativeTabs.DECORATIONS);
  }

  /**
   * @return la classe de la tile entity
   */
  public Class<T> getTileEntityClass() {
    return this.teClass;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public abstract T createNewTileEntity(World worldIn, int meta);

  /**
   * @return l'interface à afficher
   */
  public abstract CustomGuiScreens getGui();

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te != null && te.getClass() == this.teClass) {
        playerIn.openGui(McfrMain.instance, getGui().ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
    }

    return true;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity te = worldIn.getTileEntity(pos);

    if (te != null && te.getClass() == this.teClass) {
      InventoryHelper.dropInventoryItems(worldIn, pos, (T) te);
    }

    super.breakBlock(worldIn, pos, state);
  }
}
