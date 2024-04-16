package com.cowdungpaints.services;

import com.cowdungpaints.entities.Order;
import com.cowdungpaints.entities.Payment;
import com.cowdungpaints.enums.PaymentMethod;
import com.cowdungpaints.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment processPayment(Order order, PaymentMethod method, double amount, String paymentDetails) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(method);
        payment.setAmount(amount);
        payment.setPaymentDetails(paymentDetails);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public <PaymentRequest> Payment processPayment(Order savedOrder, PaymentRequest paymentRequest) {
        return null;
    }

    public <PaymentRequest> Payment processPayment(PaymentRequest paymentRequest) {
        return null;
    }
}
