package net.mcfr.utils;

/**
 * Cette classe fournit des constantes correspondant aux types de tag NBT.
 * 
 * @author Mc-Fr
 */
public final class NBTUtils {
  /** Fin */
  public static final int TAG_END = 0;
  /** Octet */
  public static final int TAG_BYTE = 1;
  /** Entier court */
  public static final int TAG_SHORT = 2;
  /** Entier */
  public static final int TAG_INT = 3;
  /** Entier long */
  public static final int TAG_LONG = 4;
  /** Flottant */
  public static final int TAG_FLOAT = 5;
  /** Double */
  public static final int TAG_DOUBLE = 6;
  /** Tableau d'octets */
  public static final int TAG_BYTE_A = 7;
  /** Chaîne de caractères */
  public static final int TAG_STRING = 8;
  /** Liste */
  public static final int TAG_LIST = 9;
  /** Tag composé */
  public static final int TAG_COMPOUND = 10;
  /** Tableau d'entiers */
  public static final int TAG_INT_A = 11;

  private NBTUtils() {}
}
