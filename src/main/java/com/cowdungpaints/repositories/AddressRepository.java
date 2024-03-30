package com.cowdungpaints.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowdungpaints.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
