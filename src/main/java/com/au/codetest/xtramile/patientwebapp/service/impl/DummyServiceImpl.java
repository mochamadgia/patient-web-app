package com.au.codetest.xtramile.patientwebapp.service.impl;

import com.au.codetest.xtramile.patientwebapp.entity.Address;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import com.au.codetest.xtramile.patientwebapp.enums.Gender;
import com.au.codetest.xtramile.patientwebapp.repository.AddressRepository;
import com.au.codetest.xtramile.patientwebapp.repository.PatientRepository;
import com.au.codetest.xtramile.patientwebapp.service.DummyService;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DummyServiceImpl implements DummyService {
  private Faker faker;
  private final PatientRepository repository;
  private final AddressRepository addressRepository;

  public DummyServiceImpl(PatientRepository repository, AddressRepository addressRepository) {
    this.repository = repository;
    this.addressRepository = addressRepository;
  }

  @PostConstruct
  private void fill(){
    this.faker = new Faker(new Locale("en_AU"));
  }
  @Override
  public void generateDummyData() throws Exception {
    try {
      for (int i = 0; i < 100; i++) {
        Patient patient = new Patient();
        patient.setDob(getDob());
        patient.setGender(Gender.randomGender());
        patient.setFirstName(faker.name().firstName());
        patient.setLastName(faker.name().lastName());
        patient.setPhoneNo(faker.phoneNumber().cellPhone());
        patient.setAddress(buildAddress());
        repository.saveAndFlush(patient);

      }
    } catch (Exception e){
      throw new Exception();
    }

  }
  private String getDob(){
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    return sdf.format(faker.date().birthday());
  }
  private Address buildAddress(){
    Address address = new Address();
    address.setAddress(faker.address().fullAddress());
    address.setSuburb(faker.address().city());
    address.setState(faker.address().state());
    address.setPostCode(faker.address().zipCode());
    return address;
  }
}
