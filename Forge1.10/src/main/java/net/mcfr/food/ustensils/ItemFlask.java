package net.mcfr.food.ustensils;

import net.mcfr.commons.IEnumType;
import net.mcfr.food.ItemDrink;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Fiole en verre.
 *
 * @author Mc-Fr
 */
public class ItemFlask extends ItemDrink<ItemFlask.EnumType> {
  /**
   * Crée une fiole.
   * 
   * @param nutrition la quantité de faim restituée
   * @param saturation le taux de saturation
   */
  public ItemFlask(int nutrition, float saturation) {
    super("flask", Items.GLASS_BOTTLE, nutrition, saturation, EnumType.class);
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

  /**
   * Le type de contenu de la fiole :
   * <ul>
   * <li>lait</li>
   * <li>cacao</li>
   * </ul>
   *
   * @author Mc-Fr
   */
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
