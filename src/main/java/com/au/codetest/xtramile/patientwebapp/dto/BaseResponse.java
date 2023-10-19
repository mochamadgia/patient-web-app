package com.au.codetest.xtramile.patientwebapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BaseResponse<T> {
  boolean status;
  String message;
  T data;
}
