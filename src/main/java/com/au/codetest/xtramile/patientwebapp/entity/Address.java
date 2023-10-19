package com.au.codetest.xtramile.patientwebapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @SequenceGenerator(name = "addressSeqId", sequenceName = "address_address_id_seq")
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "address")
  private String address;
  @Column(name = "suburb")
  private String suburb;
  @Column(name = "state")
  private String state;
  @Column(name = "postcode")
  private String postCode;

}
