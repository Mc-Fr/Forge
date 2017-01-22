package net.mcfr.entities.mobs.gender;

import java.util.Random;

public enum Genders {
  MALE(0),
  FEMALE(1),
  RANDOM(2);
  
  private int value;
  
  private Genders(int value) {
    this.value = value;
  }
  
  public int getInt() {
    return this.value;
  }
  
  public static Genders getByInt(int value) {
    if (value == 0) {
      return MALE;
    } else if (value == 1) {
      return FEMALE;
    } else {
      return RANDOM;
    }
  }
  
  public static Genders getRandomGender() {
    return getByInt(new Random().nextInt(2));
  }
}
