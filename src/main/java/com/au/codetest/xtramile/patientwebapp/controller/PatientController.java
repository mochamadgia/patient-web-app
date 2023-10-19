package com.au.codetest.xtramile.patientwebapp.controller;

import com.au.codetest.xtramile.patientwebapp.dto.BaseResponse;
import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import com.au.codetest.xtramile.patientwebapp.service.PatientService;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

  private final PatientService service;
  private ModelMapper modelMapper = new ModelMapper();

  public PatientController(PatientService service) {
    this.service = service;
  }

  @GetMapping()
  public ResponseEntity<BaseResponse<Page<Patient>>> getPatients(@RequestParam("page") int page,
      @RequestParam("size") int size) {
    Page<Patient> paged = service.getPatients(PageRequest.of(page, size));
    return ResponseEntity.ok(
        BaseResponse.<Page<Patient>>builder().status(true).message("OK").data(paged).build());
  }

  @PostMapping()
  public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
    patientDto = modelMapper.map(service.insert(patientDto), PatientDto.class);
    return ResponseEntity.ok(patientDto);
  }

  @PutMapping()
  public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto)
      throws Exception {
    service.update(patientDto);
    return ResponseEntity.accepted().build();
  }

  @DeleteMapping("/{pid}")
  public ResponseEntity deletePatient(@PathVariable(value = "pid") long pid) throws Exception {
    service.delete(pid);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<BaseResponse<Page<Patient>>> searchPatient(
      @RequestParam(value = "pid") String pid,
      @RequestParam(value = "patientName") String patientName,
      @RequestParam(value = "page") int page,
      @RequestParam(value = "size") int size) {
    if (!pid.isEmpty() && patientName.isEmpty()) {
      try {
        long pidAsLong = Long.valueOf(pid);
        return ResponseEntity.ok(BaseResponse.<Page<Patient>>builder().status(true).message("OK")
            .data(service.searchByPid(pidAsLong)).build());
      } catch (Exception e) {
        return ResponseEntity.internalServerError()
            .body(BaseResponse.<Page<Patient>>builder().status(false).message(
                    HttpStatus.INTERNAL_SERVER_ERROR.name())
                .data(new PageImpl<>(new ArrayList<>(), PageRequest.of(1, 10), 0)).build());
      }
    }else{
      return ResponseEntity.ok(BaseResponse.<Page<Patient>>builder().status(true).message("OK")
          .data(service.searchByName(patientName, PageRequest.of(page, size))).build());
    }
  }
}
