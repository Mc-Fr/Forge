package net.mcfr.decoration.misc.blood_stains;

import java.util.List;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Item des taches de sang.
 *
 * @author Mc-Fr
 */
public class ItemBlood extends McfrItem {
  public ItemBlood() {
    super("blood_stains", CreativeTabs.DECORATIONS);
    setHasSubtypes(true);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX,
      float hitY, float hitZ) {
    EnumType type = EnumType.byMetadata(stack.getMetadata());
    Block block = type.getBlock();
    int meta = type.getBlockMetadata();

    if (facing.getAxis() != EnumFacing.Axis.Y && type != EnumType.TRAIL_CORNER) {
      world.setBlockState(pos.offset(facing),
          block.getDefaultState().withProperty(BlockBloodWallsAndFloor.FACING, facing).withProperty(BlockBloodWallsAndFloor.ON_WALL, true)
              .withProperty(BlockBloodWallsAndFloor.VARIANT, BlockBloodWallsAndFloor.EnumType.byMetadata(meta)));
    }
    else if (facing == EnumFacing.UP) {
      IBlockState state = block.getDefaultState().withProperty(BlockBloodWallsAndFloor.FACING, FacingUtils.getHorizontalFacing(player).getOpposite());
      if (type != EnumType.TRAIL_CORNER)
        state = state.withProperty(BlockBloodWallsAndFloor.ON_WALL, false).withProperty(BlockBloodWallsAndFloor.VARIANT,
            BlockBloodWallsAndFloor.EnumType.byMetadata(meta));
      world.setBlockState(pos.up(), state);
    }
    else {
      return EnumActionResult.FAIL;
    }
    world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1, 0.75f, false);

    return EnumActionResult.SUCCESS;
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return super.getUnlocalizedName(stack) + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumType.values().length; i++) {
      subItems.add(new ItemStack(itemIn, 1, i));
    }
  }

  public static enum EnumType implements IEnumType<EnumType> {
    SPLAT_0("splat_0", McfrBlocks.BLOOD1, 0),
    SPLAT_1("splat_1", McfrBlocks.BLOOD1, 1),
    SPLAT_2("splat_2", McfrBlocks.BLOOD2, 0),
    SPLAT_3("splat_3", McfrBlocks.BLOOD2, 1),
    SPLAT_4("splat_4", McfrBlocks.BLOOD3, 0),
    SPLAT_5("splat_5", McfrBlocks.BLOOD3, 1),
    SPLAT_6("splat_6", McfrBlocks.BLOOD4, 0),
    TRAIL_0("trail_0", McfrBlocks.BLOOD4, 1),
    TRAIL_1("trail_1", McfrBlocks.BLOOD5, 0),
    TRAIL_2("trail_2", McfrBlocks.BLOOD5, 1),
    TRAIL_CORNER("trail_corner", McfrBlocks.BLOOD_CORNER);

    private final String name;
    private final Block block;
    private final int blockMeta;

    private EnumType(String name, Block block) {
      this(name, block, -1);
    }

    private EnumType(String name, Block block, int blockMeta) {
      this.name = name;
      this.block = block;
      this.blockMeta = blockMeta;
    }

    @Override
    public String getName() {
      return this.name;
    }

    public Block getBlock() {
      return this.block;
    }

    public int getBlockMetadata() {
      return this.blockMeta;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }

    public static EnumType fromBlockNameAndVariant(String name, BlockBloodWallsAndFloor.EnumType variant) {
      switch (name.substring(name.lastIndexOf('_') + 1)) {
        case "1":
          if (variant == BlockBloodWallsAndFloor.EnumType.SPLAT_0)
            return SPLAT_0;
          return SPLAT_1;
        case "2":
          if (variant == BlockBloodWallsAndFloor.EnumType.SPLAT_0)
            return SPLAT_2;
          return SPLAT_3;
        case "3":
          if (variant == BlockBloodWallsAndFloor.EnumType.SPLAT_0)
            return SPLAT_4;
          return SPLAT_5;
        case "4":
          if (variant == BlockBloodWallsAndFloor.EnumType.SPLAT_0)
            return SPLAT_6;
          return TRAIL_0;
        case "5":
          if (variant == BlockBloodWallsAndFloor.EnumType.SPLAT_0)
            return TRAIL_1;
          return TRAIL_2;
        default:
          return null;
      }
    }
  }
}
