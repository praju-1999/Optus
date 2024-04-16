package com.cowdungpaints.repositories;

import com.cowdungpaints.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
