package com.au.codetest.xtramile.patientwebapp.handler;

import com.au.codetest.xtramile.patientwebapp.dto.BaseResponse;
import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.exception.BadRequestException;
import com.au.codetest.xtramile.patientwebapp.exception.PatientCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = BadRequestException.class)
  @ResponseBody
  public ResponseEntity<BaseResponse<String>> exception(BadRequestException exception)
      throws JsonProcessingException {
    return new ResponseEntity<>(BaseResponse.<String>builder().status(false).message(
        exception.getMessage()).data(exception.getParamToString()).build(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = PatientCreationException.class)
  @ResponseBody
  public ResponseEntity<BaseResponse<PatientDto>> exception(PatientCreationException exception) {
    return new ResponseEntity<>(BaseResponse.<PatientDto>builder().status(false).message(
        exception.getMessage()).data(exception.getData()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseEntity<BaseResponse> exception() {
    return new ResponseEntity<>(BaseResponse.builder().status(false).message(
        "SERVER ERROR OCCURRED").data(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
