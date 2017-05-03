package net.mcfr.entities.mobs.gender;

import java.util.Random;

/**
 * Genres :
 * <ul>
 * <li>mâle</li>
 * <li>femelle</li>
 * <li>aléatoire</li>
 * </ul>
 *
 * @author Mc-Fr
 */
public enum Genders {
  MALE,
  FEMALE,
  RANDOM;

  public static Genders getByInt(int value) {
    switch (value) {
      case 0:
        return MALE;
      case 1:
        return FEMALE;
      default:
        return RANDOM;
    }
  }

  public static Genders getRandomGender() {
    return getByInt(new Random().nextInt(2));
  }
}
