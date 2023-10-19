package com.au.codetest.xtramile.patientwebapp.controller;

import com.au.codetest.xtramile.patientwebapp.service.DummyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {
  private final DummyService service;

  public DummyController(DummyService service) {
    this.service = service;
  }

  /**
   * this end opint is used for generating fake data using Java Faker and just for testing purpose only
   * @throws Exception
   */
  @PostMapping()
  public ResponseEntity<String> generateDummyData() throws Exception {
    service.generateDummyData();
    return ResponseEntity.ok("OK");
  }
}
