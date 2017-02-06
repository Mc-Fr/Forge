package net.mcfr.decoration.signs.tile_entities;

import net.minecraft.tileentity.TileEntitySign;

/**
 * Cette classe correspond aux Tile Entities utilisées pas les panneaux Mc-Fr et la pierre tombale.
 * Elle est abstraite pour obliger la définition de classes filles pour chaque panneau afin d'avoir
 * un TESR différent par panneau.
 * 
 * @author Mc-Fr
 * @see net.minecraft.tileentity.TileEntitySign
 */
public abstract class TileEntityMcfrSign extends TileEntitySign {}
