package com.au.codetest.xtramile.patientwebapp.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class BadRequestException extends RuntimeException {


  private Map<String, String> param;

  public BadRequestException(String message){
    super(message);
  }

  public BadRequestException(String message, Map<String, String> param){
    super(message);
    this.param = param;
  }
  private static final long serialVersionUID = 4397432601296821728L;
  public String getParamToString() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(param);
  }
}
