package com.cowdungpaints.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowdungpaints.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
