package net.mcfr.equipment;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGrapnel extends McfrItem {
  public ItemGrapnel() {
    super("grapnel", 8, CreativeTabs.TOOLS);
    setHasSubtypes(true);
  }

  // TODO utiliser onItemRigthClick
  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    // TEMP
    // --stack.stackSize;
    // worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
    // SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (itemRand.nextFloat() *
    // 0.4f + 0.8f));
    //
    // if (!worldIn.isRemote) {
    // EntityGrapnel eg = new EntityGrapnel(worldIn, playerIn,
    // EnumType.byMetadata(stack.getMetadata()));
    // eg.setVelocity(eg.motionX * 0.1, eg.motionY * 0.1, eg.motionZ * 0.1);
    // worldIn.spawnEntityInWorld(eg);
    // }

    return EnumActionResult.FAIL;
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumType.values().length; i++) {
      subItems.add(new ItemStack(itemIn, 1, i));
    }
  }

  public static enum EnumType implements IEnumType<EnumType> {
    ROPE_SHORT("rope_short", 4, true),
    ROPE_MEDIUM("rope_medium", 8, true),
    ROPE_LONG("rope_long", 12, true),
    CHAIN_SHORT("chain_short", 4, false),
    CHAIN_MEDIUM("chain_medium", 8, false),
    CHAIN_LONG("chain_long", 12, false);

    private final String name;
    private final int length;
    private final boolean rope;

    private EnumType(String name, int length, boolean rope) {
      this.name = name;
      this.length = length;
      this.rope = rope;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public int getLength() {
      return this.length;
    }

    public boolean isRope() {
      return this.rope;
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
