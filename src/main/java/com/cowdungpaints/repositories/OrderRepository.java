package com.cowdungpaints.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowdungpaints.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
	
}
