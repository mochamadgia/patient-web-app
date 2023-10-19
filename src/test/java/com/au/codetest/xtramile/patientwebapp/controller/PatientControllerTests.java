package com.au.codetest.xtramile.patientwebapp.controller;


import com.au.codetest.xtramile.patientwebapp.dto.AddressDto;
import com.au.codetest.xtramile.patientwebapp.dto.PatientDto;
import com.au.codetest.xtramile.patientwebapp.entity.Address;
import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import com.au.codetest.xtramile.patientwebapp.repository.PatientRepository;
import com.au.codetest.xtramile.patientwebapp.service.PatientService;
import com.au.codetest.xtramile.patientwebapp.service.impl.PatientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(PatientController.class)
public class PatientControllerTests {

  private static final String END_POINT_PATH = "/api/patient";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  PatientService service;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private WebApplicationContext webApplicationContext;

  private PatientDto patientDto = new PatientDto();
  private Patient patient = new Patient();

  @BeforeEach
  public void setup() {
    //Init MockMvc Object and build
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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

    this.patient = new Patient();
    this.patient.setFirstName("John");
    this.patient.setLastName("Doe");
    this.patient.setGender("M");
    this.patient.setDob("10-10-1990");
    this.patient.setPhoneNo("0123456789");
    Address addr = new Address();
    addr.setAddress("Bumi Lake Side");
    addr.setSuburb("Bogor");
    addr.setPostCode("16114");
    addr.setState("West Java");
    this.patient.setAddress(addr);
  }

  @Test
  public void givenBodyReqUpdatePatientShouldReturn201() throws Exception {

    String requestBody = objectMapper.writeValueAsString(this.patientDto);

    mockMvc.perform(MockMvcRequestBuilders.put(END_POINT_PATH).contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void givenPidDeletePatientShouldReturn204() throws Exception {

    int pid = 100;
    mockMvc.perform(
            MockMvcRequestBuilders.delete(END_POINT_PATH + "/" + pid).contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void givenReqParamGetAllPatientsShouldReturn200() throws Exception {
    int page = 0;
    int size = 10;
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.set("page", String.valueOf(page));
    params.set("size", String.valueOf(size));
    mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH).contentType("application/json")
            .queryParams(params))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void givenReqParamSearchPatientsShouldReturn200() throws Exception {
    int page = 0;
    int size = 10;
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.set("page", String.valueOf(page));
    params.set("size", String.valueOf(size));
    params.set("pid", "");
    params.set("patientName", "John Doe");
    mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH).contentType("application/json")
            .queryParams(params))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
  }
}
