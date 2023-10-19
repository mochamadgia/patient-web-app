package com.au.codetest.xtramile.patientwebapp.exception;

import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import lombok.Getter;

@Getter
public class PatientCreationException extends RuntimeException {
  private PatientDto data;
  public PatientCreationException(String message, PatientDto data){
    super(message);
    this.data = data;
  }

  private static final long serialVersionUID = 4397432601296821728L;
}
