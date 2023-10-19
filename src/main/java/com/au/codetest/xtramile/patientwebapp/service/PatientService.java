package com.au.codetest.xtramile.patientwebapp.service;

import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
  Page<Patient> getPatients(Pageable pageable);
  Patient insert(PatientDto patientDto);
  void update(PatientDto patientDto) throws Exception;
  void delete(Long pid) throws Exception;
  Page<Patient> searchByPid(long pid);
  Page<Patient> searchByName(String name, Pageable pageable);
}
