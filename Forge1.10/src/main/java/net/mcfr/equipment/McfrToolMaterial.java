package net.mcfr.equipment;

import net.minecraft.item.ItemStack;

public enum McfrToolMaterial {
  WOOD("wood", 0, 59, 2, 0, 15),
  STONE("stone", 1, 0, 0, 0, 0),
  IRON("iron", 2, 0, 0, 0, 0),
  STEEL("steel", 3, 0, 0, 0, 0),
  GOLD("gold", 1, 0, 0, 0, 0),
  GEROUN("geroun", 0, 0, 0, 0, 0),
  WOLF("wolf", 0, 0, 0, 0, 0),
  LAATKIIN("laatkiin", 0, 0, 0, 0, 0),
  ZOMBIE("zombie", 0, 0, 0, 0, 0);
  
  private final String name;
  /** The level of material this tool can harvest. */
  private final int harvestLevel;
  /** The number of uses this material allows. */
  private final int maxUses;
  /** The strength of this tool material against blocks which it is effective against. */
  private final float efficiencyOnProperMaterial;
  /** Damage versus entities. */
  private final float damageVsEntity;
  /** Defines the natural enchantability factor of the material. */
  private final int enchantability;
  
  private ItemStack repairMaterial;
  
  private McfrToolMaterial(String name, int harvestLevel, int maxUses, float efficiencyOnProperMaterial, float damageVsEntity, int enchantability) {
    this.name = name;
    this.harvestLevel = harvestLevel;
    this.maxUses = maxUses;
    this.efficiencyOnProperMaterial = efficiencyOnProperMaterial;
    this.damageVsEntity = damageVsEntity;
    this.enchantability = enchantability;
    this.repairMaterial = null;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getMetadata() {
    return ordinal();
  }
  
  public int getHarvestLevel() {
    return this.harvestLevel;
  }
  
  public int getMaxUses() {
    return this.maxUses;
  }
  
  public float getEfficiencyOnProperMaterial() {
    return this.efficiencyOnProperMaterial;
  }
  
  public float getDamageVsEntity() {
    return this.damageVsEntity;
  }
  
  public int getEnchantability() {
    return this.enchantability;
  }
  
  public ItemStack getRepairMaterial() {
    return this.repairMaterial;
  }
  
  public void setRepairItem(ItemStack stack) {
    if (getRepairMaterial() != null) throw new RuntimeException("Cannot change already set repair material");
    this.repairMaterial = stack;
  }
}
