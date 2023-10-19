package com.au.codetest.xtramile.patientwebapp.repository;

import com.au.codetest.xtramile.patientwebapp.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  @Query(value="SELECT * FROM patient p WHERE lower(p.firstname || ' ' || p.lastname) LIKE '%'||lower(?1)||'%'",
      countQuery = "SELECT count(*) FROM patient p WHERE lower(p.firstname || ' ' || p.lastname) LIKE '%'||lower(?1)||'%' ",nativeQuery = true)
  Page<Patient> findByName(String name, Pageable pageable);
}
