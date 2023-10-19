package com.au.codetest.xtramile.patientwebapp.service.impl;

import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import com.au.codetest.xtramile.patientwebapp.exception.BadRequestException;
import com.au.codetest.xtramile.patientwebapp.exception.PatientCreationException;
import com.au.codetest.xtramile.patientwebapp.repository.PatientRepository;
import com.au.codetest.xtramile.patientwebapp.service.PatientService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  public PatientServiceImpl(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  @Override
  public Page<Patient> getPatients(Pageable pageable) {
    return patientRepository.findAll(pageable);
  }

  @Override
  public Patient insert(PatientDto patientDto) {
    try {
      Patient patient = modelMapper.map(patientDto, Patient.class);
      return patientRepository.save(patient);
    } catch (Exception e) {
      log.warn("Error accured : {}", e.getMessage());
      throw new PatientCreationException("Failed to insert a record", patientDto);
    }
  }

  @Override
  public void update(PatientDto patientDto) throws Exception {
    try {
      Optional<Patient> optPatient = patientRepository.findById(patientDto.getPid());
      if (optPatient.isPresent()) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        patient.setPid(optPatient.get().getPid());
        patientRepository.save(patient);
      } else {
        throw new BadRequestException("pid with " + patientDto.getPid() + "is not found",
            buildBadRequestParam(String.valueOf(patientDto.getPid())));
      }
    } catch (Exception e) {
      throw new Exception();
    }

  }

  @Override
  public void delete(Long pid) throws Exception {
    try {
      Patient patient = patientRepository.findById(pid).orElseThrow(() ->
      new BadRequestException("pid with " + pid + "is not found",
          buildBadRequestParam(String.valueOf(pid))));
      patientRepository.delete(patient);
    } catch (Exception e) {
      throw new Exception();
    }
  }

  @Override
  public Page<Patient> searchByPid(long pid) {
    List<Patient> patients = new ArrayList<>();
    try {
      Patient patient = patientRepository.findById(pid).orElseThrow(Exception::new);
      patients = Collections.singletonList(patient);
    }catch (Exception e){
      log.warn("Patient is not found");
    }
    return new PageImpl<>(patients, PageRequest.of(0, 10), patients.size());
  }

  @Override
  public Page<Patient> searchByName(String name, Pageable pageable) {
    return patientRepository.findByName(name, pageable);
  }


  private Map<String, String> buildBadRequestParam(String value) {
    Map<String, String> param = new HashMap<>();
    param.put("pid", String.valueOf(value));
    return param;
  }
}
