package com.au.codetest.xtramile.patientwebapp.enums;

import java.util.Random;

public enum Gender {
  MALE(1, "MALE"),
  FEMALE(2, "FEMALE");
  private static final Random PRNG = new Random();
  public final int value;
  public final String name;

  Gender(int value, String name) {
    this.value = value;
    this.name = name;
  }


  public static String randomGender() {
    Gender[] genders = values();
    return genders[PRNG.nextInt(genders.length)].name();
  }
}
