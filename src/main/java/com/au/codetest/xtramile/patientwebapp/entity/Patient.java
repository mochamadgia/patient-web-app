package com.au.codetest.xtramile.patientwebapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENT")
@Getter
@Setter
public class Patient implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pid")
  private Long pid;
  @Column(name = "firstname")
  private String firstName;
  @Column(name = "lastname")
  private String lastName;
  @Column(name = "dob")
  private String dob;
  @Column(name = "gender")
  private String gender;
  @Column(name = "phoneNo")
  private String phoneNo;
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Address address;

}
