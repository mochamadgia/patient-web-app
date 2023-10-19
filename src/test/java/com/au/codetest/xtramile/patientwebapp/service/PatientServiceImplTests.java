package com.au.codetest.xtramile.patientwebapp.service;

import com.au.codetest.xtramile.patientwebapp.dto.AddressDto;
import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import com.au.codetest.xtramile.patientwebapp.repository.PatientRepository;
import com.au.codetest.xtramile.patientwebapp.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTests {

  @InjectMocks
  PatientServiceImpl service;
  @Mock
  PatientRepository repository;
  private ModelMapper modelMapper = new ModelMapper();
  private PatientDto patientDto;
  @BeforeEach
  public void setup(){
    MockitoAnnotations.openMocks(this);
    service = new PatientServiceImpl(repository);
    this.patientDto = new PatientDto();
    patientDto.setFirstName("John");
    patientDto.setLastName("Doe");
    patientDto.setGender("M");
    patientDto.setDob("10-10-1990");
    patientDto.setPhoneNo("0123456789");
    AddressDto address = new AddressDto();
    address.setAddress("Bumi Lake Side");
    address.setSuburb("Bogor");
    address.setPostCode("16114");
    address.setState("West Java");
    patientDto.setAddress(address);
  }
  @Test
  public void givenDtoThenSaveShouldReturnDto(){
    this.patientDto = new PatientDto();
    patientDto.setFirstName("John");
    patientDto.setLastName("Doe");
    patientDto.setGender("M");
    patientDto.setDob("10-10-1990");
    patientDto.setPhoneNo("0123456789");
    AddressDto address = new AddressDto();
    address.setAddress("Bumi Lake Side");
    address.setSuburb("Bogor");
    address.setPostCode("16114");
    address.setState("West Java");
    patientDto.setAddress(address);
    Patient patient = modelMapper.map(patientDto, Patient.class);
    patient.setPid(Long.valueOf(1L));
    lenient().when(repository.save(any(Patient.class))).thenReturn(patient);
    Patient actual = service.insert(patientDto);

    //assert
    assertThat(actual).usingRecursiveComparison().isEqualTo(patient);
    verify(repository, times(1)).save(any(Patient.class));
  }
}
