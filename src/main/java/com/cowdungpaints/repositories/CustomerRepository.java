package com.cowdungpaints.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowdungpaints.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
