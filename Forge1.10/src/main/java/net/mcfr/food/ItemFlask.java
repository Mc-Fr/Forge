package net.mcfr.food;

import net.mcfr.commons.IEnumType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFlask extends ItemDrink<ItemFlask.EnumType> {
  public ItemFlask(Item emptyContainer, int nutrition, float saturation) {
    super("flask", emptyContainer, nutrition, saturation, EnumType.class);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    if (entityLiving instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) entityLiving;

      switch (EnumType.byMetadata(stack.getMetadata())) {
        case COCOA:
          player.getFoodStats().addStats(4, 0.3f);
        case MILK:
          player.clearActivePotions();
          break;
      }
    }

    return super.onItemUseFinish(stack, worldIn, entityLiving);
  }

  public static enum EnumType implements IEnumType<EnumType> {
    MILK("milk"),
    COCOA("cocoa");

    private final String name;

    private EnumType(String name) {
      this.name = name;
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

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
