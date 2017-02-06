package net.mcfr.commons;

import net.minecraft.util.IStringSerializable;

/**
 * Interface permettant de repérer les énumérations de variantes de blocs/items.<br/>
 * La type générique est supposé restreindre l'utilisation de cette interface aux énumérations
 * seulement. Les énumérations implementant cette interface devraient fournir une méthode statique
 * {@code T byMetadata(int meta)}.
 * 
 * @author Mc-Fr
 */
public interface IEnumType<T extends Enum<T>> extends IStringSerializable {
  /**
   * @return le nom interne de la variante
   */
  @Override
  String getName();

  /**
   * @return le metadata de la variante
   */
  int getMetadata();

  /**
   * @return le nom interne de la variante
   * @see #getName()
   */
  @Override
  String toString();
}