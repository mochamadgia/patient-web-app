package com.au.codetest.xtramile.patientwebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {

  private Long pid;
  private String firstName;
  private String lastName;
  private String dob;
  private String gender;
  private String phoneNo;
  private AddressDto address;
}
