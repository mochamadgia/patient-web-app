package com.au.codetest.xtramile.patientwebapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

  private Long id;
  private String address;
  private String suburb;
  private String state;
  private String postCode;

}
