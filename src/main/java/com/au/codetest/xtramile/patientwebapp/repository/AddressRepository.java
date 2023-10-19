package com.au.codetest.xtramile.patientwebapp.repository;

import com.au.codetest.xtramile.patientwebapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
